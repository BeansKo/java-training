package com.beans.ko.java.training.reflect.loader;

public class Test1 {

	public static void main(String[] args) {
		//BootstrapClassLoader加载内容
		System.out.println(System.getProperty("sun.boot.class.path"));
		//ExtClassLoader加载内容
		System.out.println(System.getProperty("java.ext.dirs"));
		//AppClassLoader加载内容
		System.out.println(System.getProperty("java.class.path"));
		
		//Executor是AppClassLoader加载的
		ClassLoader cl = Executor.class.getClassLoader();
		System.out.println("ClassLoader is:" + cl.toString());
		System.out.println("Parent classLoader is:" + cl.getParent());
		//int.class是BootStrap加载的，所以这里获取的ClassLoad是null
//		cl = int.class.getClassLoader();
//		System.out.println("ClassLoader is:" + cl.toString() );
	}
}
