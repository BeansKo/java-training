package com.beans.ko.java.training.reflect.clazz;

/**
 * 每个类用之前都会被加载，被加载时会产生一个对象，一个类只被加载一次
 * 这个对象包含了这个类的信息，存储着该类的信息，存在堆里
 * 这个对象是Class的对象
 * 这个对象被称之为类对象
 * 
 * 得到Class对象的三种方式
 *   苹果手机很厉害，华为想生产苹果，华为如何生产苹果呢？
 *   1.苹果公司直接把图纸技术给华为                  直接把类给你，让你创建类对象
 *   2.华为买1W个苹果，进行拆机，反推图纸    通过对象来得到类对象
 *   3.找组织拿到图纸和技术                                   通过地址
 * 
 */
public class Clazz {
	public static void main(String[] args) throws ClassNotFoundException {
		//1.通过类的方式
		Class<Person> clazz = Person.class;
		//2.通过对象
		Class<? extends Person> clazz1 = new Person().getClass();
		
		Class<?> clazz2 = Class.forName("com.beans.ko.java.training.reflect.clazz.Person");
		System.out.println(clazz == clazz1);
		System.out.println(clazz1 == clazz2);
	}

	
	
}
