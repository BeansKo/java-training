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

import org.iq80.leveldb.impl.Iq80DBFactory;
import org.iq80.leveldb.util.ByteUtil;

public class LocalMap{

	static Options options = new Options()
			.maxOpenFiles(100)
			.createIfMissing(true)
			.cacheSize(0)
			.paranoidChecks(true);
	
	static File getPath(String keyspaces){
		File file;
		try {
			file = new File("MAP/" + java.net.URLEncoder.encode(keyspaces,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			file = new File("MAP/" + keyspaces);
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

	public static String get(String keyspaces, String key) {
		DB db = getKeyspaces(keyspaces);
		return ByteUtil.byteArrayToString(db.get(key(key)));
	}

	public static void forEach(String keyspaces, Consumer<Entry<String, String>> consumer) {
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
						consumer.accept(new Entry<String, String>() {

							@Override
							public String getKey() {
								return ByteUtil.byteArrayToString(entry.getKey());
							}

							@Override
							public String getValue() {
								return ByteUtil.byteArrayToString(value);
							}

							@Override
							public String setValue(String value) {
								throw new RuntimeException("can not change data");
							}
						});
					}
				}
			}
		}
	}
	
	public static void forEach(String keyspaces, int batch, Consumer<Map<String, String>> consumer) {
		if(consumer == null){ return; }
		DB db = getKeyspaces(keyspaces);
		try(Snapshot snapshot = db.getSnapshot()){
			ReadOptions readOptions = new ReadOptions().snapshot(snapshot).fillCache(false);
			Map<String, String> rows = new HashMap<>();
			try(DBIterator iterator = db.iterator(readOptions)){
				iterator.seekToFirst();
				while(iterator.hasNext()){
					if(Thread.currentThread().isInterrupted()){ throw new CancellationException("Interrupted"); }
					Entry<byte[], byte[]> entry = iterator.next();
					byte[] value = entry.getValue();
					if(value != null){
						rows.put(ByteUtil.byteArrayToString(entry.getKey()), ByteUtil.byteArrayToString(value));
						if(rows.size() > batch){
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

	public static void put(String keyspaces, String key, String value) {
		DB db = getKeyspaces(keyspaces);
		db.put(ByteUtil.stringToByteArray(key), ByteUtil.stringToByteArray(value), new WriteOptions().sync(true));
	}
	
	public static ChangeEnum putAndGet(String keyspaces, String key, String value) {
		DB db = getKeyspaces(keyspaces);
		byte[] old = db.get(ByteUtil.stringToByteArray(key));
		db.put(ByteUtil.stringToByteArray(key), ByteUtil.stringToByteArray(value), new WriteOptions().sync(true));
		if(old == null){ return ChangeEnum.ADDED; }
		if(!value.equals(ByteUtil.byteArrayToString(old))){
			return ChangeEnum.UPDATED;
		}
		return ChangeEnum.NONE;
	}

	public static void putAll(String keyspaces, Map<String, String> rows) {
		DB db = getKeyspaces(keyspaces);
		WriteBatch batch = db.createWriteBatch();
		rows.forEach((key, value)->{
			batch.put(ByteUtil.stringToByteArray(key), ByteUtil.stringToByteArray(value));
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
	
	public static synchronized void close(String keyspace) {
		DB db = caches.get(keyspace);
		caches.remove(keyspace);
		if(db == null){ return; }
		db.close();
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
