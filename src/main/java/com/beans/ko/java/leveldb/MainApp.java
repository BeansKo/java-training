package com.beans.ko.java.leveldb;

import java.util.TreeMap;

import org.iq80.leveldb.LocalStrage;
import org.iq80.leveldb.Row;


public class MainApp {
	public static void main(String[] args) {
		//构建keySpace,数据按keySpace进行管理
		String keyspaces= System.currentTimeMillis()+"";
		String rowKey = "hu";
		TreeMap<String, String> data = new TreeMap<String, String>();
		data.put("Name", "胡八一");
		data.put("age", "20");
		//Row的组成为Key和Data,其中Data为TreeMap
		Row row = new Row(keyspaces,rowKey,data);
		//keySpace中保存数据的单位为Row
		LocalStrage.put(keyspaces, row);
		System.out.println("保存成功！");
		
		System.out.println("开始查询");
		Row rs = LocalStrage.get(keyspaces, rowKey);
		System.out.println(rs.getKey());
		rs.getData().forEach((k,v) -> {
			System.out.println("K:"+k);
			System.out.println("V:"+v);
		});
		
		//关闭keyspace
		LocalStrage.close(keyspaces);
	}
}
