package com.beans.ko.java.training.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropOne {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		File file = new File("conf/hivetableMapinghdfsPath.properties");
		InputStream in = new FileInputStream(file);
		prop.load(in);
		String[] tableName = new String[prop.keySet().size()];
		String[] tablePath = new String[tableName.length];
		for(Object table:prop.keySet()){
			System.out.println(table);
			System.out.println(prop.getProperty((String)table));
		}

	}

}
