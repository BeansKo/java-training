package org.iq80.leveldb;


import java.io.Closeable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import org.iq80.leveldb.ChangeEnum;
import org.iq80.leveldb.LocalMap;

public class StorageMap implements Closeable{
	
	String keyspaces;
	
	public StorageMap(String key) {
		this.keyspaces = key;
	}
	
	public void forEach(Consumer<Entry<String, String>> consumer) {
		LocalMap.forEach(keyspaces, consumer);
	}
	
	public long count() {
		return LocalMap.count(keyspaces);
	}
	
	public String get(String key) {
		return LocalMap.get(keyspaces, key);
	}
	
	public void put(String key, String value){
		LocalMap.put(keyspaces, key, value);
	}
	
	public void putAll(Map<String, String> rows) {
		LocalMap.putAll(keyspaces, rows);
	}
	
	public void remove(String key) {
		LocalMap.remove(keyspaces, key);
	}
	
	public void removeAll(Set<String> rows) {
		LocalMap.removeAll(keyspaces, rows);
	}
	
	public ChangeEnum set(String key, String value){
		return LocalMap.putAndGet(keyspaces, key, value);
	}

	public void clear(){
		LocalMap.clear(keyspaces);
	}
	
	@Override
	public void close() {
		LocalMap.close(keyspaces);
	}
}
