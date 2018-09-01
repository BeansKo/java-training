package com.beans.ko.java.training.reflect.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

/**
 * 反射获取和操作构造器
 */
public class ReflectConstructor {

	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> clazz = Class
				.forName("com.beans.ko.java.training.reflect.constructor.Student");
		// 获得构造器
		// 获得单个公开的构造器
		Constructor constructor = clazz.getConstructor(null);
		System.out.println(constructor);
		// 获得所有的公共构造方法 不包含父类的 如何得到父类的，先获得父类的类对象，然后再获得
		Constructor[] constructors = clazz.getConstructors();
		for (Constructor cons : constructors) {
			System.out.println(cons);
		}

		System.out.println("===========声明的构造器==============");
		// 获得声明的构造器
		constructor = clazz.getDeclaredConstructor(null);
		System.out.println(constructor);
		constructor = clazz.getDeclaredConstructor(String.class);
		System.out.println(constructor);
		constructors = clazz.getDeclaredConstructors();
		for (Constructor cons : constructors) {
			System.out.println(cons);
		}
		System.out.println("end");
		useConstructor();
	}

	@Test
	private static void useConstructor() throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> clazz = Class
				.forName("com.beans.ko.java.training.reflect.constructor.Student");
		System.out.println("===============操作构造器=====================");
		// 操作无参构造器
		Constructor constructor = clazz.getDeclaredConstructor(null);
		Object obj = constructor.newInstance(null);
		System.out.println(obj);
		// 操作有参构造器
		constructor = clazz.getDeclaredConstructor(String.class,boolean.class);
		constructor.setAccessible(true);
		obj = constructor.newInstance("zhangsan",true);
		System.out.println(obj);
	}

}

class Person {
	public Person() {
	}

	public Person(String pname, boolean psex, int page, int pscore) {
		super();
		this.pname = pname;
		this.psex = psex;
		this.page = page;
		this.pscore = pscore;
	}

	String pname;
	boolean psex;
	int page;
	int pscore;
}

class Student extends Person {
	public Student() {
	}

	public Student(String name) {
		super();
		this.sname = name;
	}

	private Student(String sname, boolean ssex) {
		super();
		this.sname = sname;
		this.ssex = ssex;
	}

	Student(String sname, boolean ssex, int sage, int sscore) {
		super();
		this.sname = sname;
		this.ssex = ssex;
		this.sage = sage;
		this.sscore = sscore;
	}

	static String pswd;
	String sname;
	boolean ssex;
	int sage;
	int sscore;
	
	public String toString() {
		
		return "[name="+sname+" sex="+ssex+" age="+sage+" score="+sscore+"]";
	}
}
