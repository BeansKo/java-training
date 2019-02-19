package org.iq80.leveldb;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.concurrent.CancellationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.ReadOptions;
import org.iq80.leveldb.Snapshot;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.iq80.leveldb.util.ByteUtil;

public class LocalStrage{

	static Options options = new Options()
			.maxOpenFiles(100)
			.createIfMissing(true)
			.cacheSize(0)
			.paranoidChecks(true);
	
	static File getPath(String keyspaces){
		File file;
		try {
			file = new File("STORAGE/" + java.net.URLEncoder.encode(keyspaces,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			file = new File("STORAGE/" + keyspaces);
		}
		file.mkdirs();
		return file;
	}
	
	static Map<String, DB> caches = new HashMap<String, DB>();
	
	static synchronized DB getKeyspaces(String keyspaces) {
		if(keyspaces == null){ keyspaces = "SYSTEM"; }
		DB db = caches.get(keyspaces);
		if(db != null){
			try {
				db.get("NULL".getBytes());
				return db;
			} catch (Exception e) {
				e.printStackTrace();
				caches.remove(keyspaces);
				db.close();
				try { Iq80DBFactory.factory.destroy(getPath(keyspaces), options); } catch (IOException e1) { }
				db = null;
			}
		}
		try {
			db = Iq80DBFactory.factory.open(getPath(keyspaces), options);
			caches.put(keyspaces, db);
			return db;
		} catch (Exception e) {
			throw new DBException("can not find database:" + keyspaces, e);
		}
	}
	
	static byte[] key(String key){
		return key.getBytes(Charset.forName("UTF-8"));
	}
	
	public static boolean containsKey(String keyspaces, String key) {
		DB db = getKeyspaces(keyspaces);
		return db.get(key(key)) != null;
	}

	public static Row get(String keyspaces, String key) {
		DB db = getKeyspaces(keyspaces);
		return Row.bytes(db.get(key(key)));
	}
	
	public static Map<String, Row> getAll(String keyspaces, Set<String> keys) {
		Map<String, Row> all = new HashMap<String, Row>();
		if(keys == null || keys.size() == 0){ return all; }
		DB db = getKeyspaces(keyspaces);
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			for(String key : keys){
				Row row = Row.bytes(db.get(key(key), readOptions));
				all.put(key, row);
			}
		}
		return all;
	}

	public static void forEach(String keyspaces, Consumer<Row> consumer) {
		if(consumer == null){ return; }
		DB db = getKeyspaces(keyspaces);
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			try(DBIterator iterator = db.iterator(readOptions)){
				iterator.seekToFirst();
				while(iterator.hasNext()){
					if(Thread.currentThread().isInterrupted()){ throw new CancellationException("Interrupted"); }
					Entry<byte[], byte[]> entry = iterator.next();
					byte[] value = entry.getValue();
					if(value != null){
						Row row = Row.bytes(value);
						if(consumer != null){
							consumer.accept(row);
						}
					}
				}
			}
		}
	}
	
	public static void forEach(String keyspaces, int batch, Consumer<Set<Row>> consumer) {
		if(consumer == null){ return; }
		DB db = getKeyspaces(keyspaces);
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			Set<Row> rows = new HashSet<>();
			try(DBIterator iterator = db.iterator(readOptions)){
				iterator.seekToFirst();
				while(iterator.hasNext()){
					if(Thread.currentThread().isInterrupted()){ throw new CancellationException("Interrupted"); }
					Entry<byte[], byte[]> entry = iterator.next();
					byte[] value = entry.getValue();
					if(value != null){
						Row row = Row.bytes(value);
						rows.add(row);
						if(rows.size() == batch){
							consumer.accept(rows);
							rows.clear();
						}
					}
				}
				if(rows.size() > 0){
					consumer.accept(rows);
					rows.clear();
				}
			}
		}
	}

	public static void put(String keyspaces, Row row) {
		DB db = getKeyspaces(keyspaces);
		db.put(row.keyByte(), row.bytes(), new WriteOptions().sync(true));
	}
	
	public static ChangeEnum putAndGet(String keyspaces, Row row) {
		DB db = getKeyspaces(keyspaces);
		byte[] old = db.get(row.keyByte());
		db.put(row.keyByte(), row.bytes(), new WriteOptions().sync(true));
		if(old == null){ return ChangeEnum.ADDED; }
		Row oldRow = Row.bytes(old);
		if(!oldRow.equals(row)){
			return ChangeEnum.UPDATED;
		}
		return ChangeEnum.NONE;
	}

	public static void putAll(String keyspaces, Set<Row> rows) {
		DB db = getKeyspaces(keyspaces);
		WriteBatch batch = db.createWriteBatch();
		rows.forEach(row->{
			batch.put(row.keyByte(), row.bytes());
		});
		db.write(batch, new WriteOptions().sync(true));
		batch.close();
	}

	public static ChangeEnum remove(String keyspaces, String key) {
		DB db = getKeyspaces(keyspaces);
		byte[] old = db.get(key(key));
		db.delete(key(key));
		if(old != null){
			return ChangeEnum.DELETED;
		}
		return ChangeEnum.NONE;
	}

	public static void remove(String keyspaces, Set<String> rows) {
		DB db = getKeyspaces(keyspaces);
		WriteBatch batch = db.createWriteBatch();
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			rows.forEach(key->{
				byte[] old = db.get(key(key), readOptions);
				if(old == null){ return; }
				batch.delete(key(key));
			});
			db.write(batch, new WriteOptions().sync(true));
		}
		batch.close();
	}

	public static Set<String> removeAll(String keyspaces, Set<String> rows) {
		DB db = getKeyspaces(keyspaces);
		Set<String> deleted = new HashSet<>();
		WriteBatch batch = db.createWriteBatch();
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			rows.forEach(key->{
				byte[] old = db.get(key(key), readOptions);
				if(old == null){ return; }
				batch.delete(key(key));
				deleted.add(key);
			});
		}
		db.write(batch, new WriteOptions().sync(true));
		batch.close();
		return deleted;
	}

	public static long count(String keyspace) {
		long count = 0;
		DB db = getKeyspaces(keyspace);
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			try(DBIterator iterator = db.iterator(readOptions)){
				iterator.seekToFirst();
				while(iterator.hasNext()){
					iterator.next();
					count++;
				}
			}
			return count;
		}
	}
	
	public static boolean isEmpty(String keyspace) {
	    DB db = getKeyspaces(keyspace);
	    try(Snapshot snapshot = db.getSnapshot()){
	        ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
	        try(DBIterator iterator = db.iterator(readOptions)){
	            iterator.seekToFirst();
	            if(iterator.hasNext()){
	                return false;
	            }
	            return true;
	        }
	    }
	}
	
	public static synchronized void close(String keyspace) {
		DB db = caches.get(keyspace);
		caches.remove(keyspace);
		if(db == null){ return; }
		db.close();
	}
	
	public static Set<String> keyspaces() {
		Set<String> keyspaces = new HashSet<>();
		DB db = getKeyspaces("VERSION");
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			try(DBIterator iterator = db.iterator(readOptions)){
				iterator.seekToFirst();
				while(iterator.hasNext()){
					Entry<byte[], byte[]> entry = iterator.next();
					byte[] keyspace = entry.getKey();
					if(keyspace != null){
						keyspaces.add(new String(keyspace, Charset.forName("UTF-8")));
					}
				}
			}
			return keyspaces;
		}
	}
	
	public static long getVersion(String keyspace) {
		DB db = getKeyspaces("VERSION");
		byte[] bs = db.get(key(keyspace));
		if(bs == null){
			return 0;
		}
		return ByteUtil.byteToLong(bs);
	}

	public static void updateVersion(String keyspace, long version) {
		DB db = getKeyspaces("VERSION");
		byte[] bs = ByteUtil.longToByte(version);
		db.put(key(keyspace), bs, new WriteOptions().sync(true));
	}
	
	public static synchronized void clear(String keyspaces) {
		try {
			DB db = caches.get(keyspaces);
			if(db != null){
				db.close();
			}
			caches.remove(keyspaces);
			remove(null, keyspaces);
			Iq80DBFactory.factory.destroy(getPath(keyspaces), options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
