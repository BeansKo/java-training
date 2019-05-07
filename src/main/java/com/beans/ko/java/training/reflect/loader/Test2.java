package com.beans.ko.java.training.reflect.loader;

import java.lang.reflect.Method;

public class Test2 {

	public static void main(String[] args) {
		DistkClassLoader distLoader = new DistkClassLoader("E:\\github\\java-training\\jar");
		
		try {
			Class<?> c = distLoader.loadClass("com.Test1");
			if (c != null) {
				Object obj = c.newInstance();
				Method method = c.getDeclaredMethod("sayProp",null);
				method.invoke(obj, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
