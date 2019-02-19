package com.beans.ko.java.leveldb;

import org.iq80.leveldb.LocalStrage;


public class MainApp {
	public static void main(String[] args) {
		LocalStrage.get("keyspace", "row");
	}
}
