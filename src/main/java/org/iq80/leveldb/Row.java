package org.iq80.leveldb;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.TreeMap;

import org.iq80.leveldb.util.ByteUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Row implements Serializable{
	private static final long serialVersionUID = -6416943549864767651L;

	private String keyspaces;
	private String key;
	private TreeMap<String, String> data;
	private String hash;
	
	public Row() {}
	
	public Row(String keyspaces, String key){
	    this.keyspaces = keyspaces;
		this.key = key;
	}
	
	public Row(String keyspaces, String key, TreeMap<String, String> data){
	    this.keyspaces = keyspaces;
		this.key = key;
		this.data = data;
		initHash();
	}
	public String getKeyspaces() {
        return keyspaces;
    }
    public void setKeyspaces(String keyspaces) {
        this.keyspaces = keyspaces;
    }
    public String getKey() {
		return key;
	}
	public byte[] keyByte() {
		return key.getBytes(Charset.forName("UTF-8"));
	}
	public void setKey(String key) {
		this.key = key;
	}
	public TreeMap<String, String> getData() {
		return data;
	}
	public void setData(TreeMap<String, String> data) {
		this.data = data;
		initHash();
	}

	private void initHash() {
		StringBuffer buffer = new StringBuffer(key == null ? "[NULL]" : key).append("=");
		this.data.forEach((k, v)->{
			buffer.append(k).append(":").append(v.hashCode());
		});
		this.hash = buffer.toString();
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public String hash() {
		if(data == null || data.size() == 0){ return ""; }
		if(this.hash == null){
			initHash();
		}
		return this.hash;
	}
	
	public boolean equals(Row row) {
		return hash().equals(row.hash());
	}
	
	public byte[] bytes() {
		String json = JSON.toJSONString(this);
		byte[] data = json.getBytes(Charset.forName("UTF-8"));
		byte[] verify = ByteUtil.intToByteArray(json.hashCode());
		byte[] dataLength = ByteUtil.intToByteArray(data.length);
		return ByteUtil.merges(dataLength, verify, data);
	}
	public static Row bytes(byte[] bytes){
		if(bytes == null){ return null; }
		byte[] dataLength = ByteUtil.sub(bytes, 0, 4);
		byte[] verify = ByteUtil.sub(bytes, 4, 4);
		byte[] data = ByteUtil.sub(bytes, 8, ByteUtil.byteArrayToInt(dataLength));
		String json = new String(data, Charset.forName("UTF-8"));
		if(json.hashCode() == ByteUtil.byteArrayToInt(verify)){
			return JSONObject.parseObject(json, Row.class);
		}
		return null;
	}
	public static Row bytes(DataInputStream in) throws IOException{
		byte[] dataLength_bs = new byte[4];
		byte[] verify_bs = new byte[4];
		in.read(dataLength_bs);
		in.read(verify_bs);
		int dataLength = ByteUtil.byteArrayToInt(dataLength_bs);
		int verify = ByteUtil.byteArrayToInt(verify_bs);
		byte[] data = new byte[dataLength];
		in.read(data);
		String json = new String(data, Charset.forName("UTF-8"));
		if(json.hashCode() == verify){
			return JSONObject.parseObject(json, Row.class);
		}
		return null;
	}
}
