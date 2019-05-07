package com.beans.ko.java.training.properties;

import java.util.Properties;

public class Test1 {

	public static void main(String[] args) {
		Properties prop = System.getProperties();
		for(String key : prop.stringPropertyNames()){
			System.out.println(key+"："+prop.getProperty(key));
		}
	}
	
	public void sayProp() {
		System.out.println("====================================");
		Properties prop = System.getProperties();
		for(String key : prop.stringPropertyNames()){
			System.out.println(key+"："+prop.getProperty(key));
		}
	}

}
