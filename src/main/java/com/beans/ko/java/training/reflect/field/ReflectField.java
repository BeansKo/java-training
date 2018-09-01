package com.beans.ko.java.training.reflect.field;

import java.lang.reflect.Field;

/**
 * 反射获取操作属性
 *
 */
public class ReflectField {

	/**
	 * 获取属性的四种方式
	 */
	public static void main(String[] args) throws Exception {
		// 获得类对象
		Class<?> clazz = Class
				.forName("com.beans.ko.java.training.reflect.field.Student");
		// 使用反射获取属性，只能获取单个public的属性，也可以获取父类的公开属性
		Field field = clazz.getField("pname");
		System.out.println(field);
		// 使用反射获取所有public的属性数组
		Field[] fields = clazz.getFields();
		for (Field f : fields) {
			System.out.println(f);
		}
		// 使用反射获取单个本类声明的属性,不能获得父类
		field = clazz.getDeclaredField("sage");
		System.out.print(field);
		// 使用反射获取所有本类声明的属性数组
		fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f);
		}
		// 获取所有父类的属性
		fields = clazz.getSuperclass().getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f);
		}

		System.out.println("end");
		fieldUse();
	}

	/**
	 * 操作属性有权限控制 属性有静态和非静态
	 */
	public static void fieldUse() throws Exception {
		Class<?> clazz = Class
				.forName("com.beans.ko.java.training.reflect.field.Student");
		// 操作属性，此时有权限限制，主要操作位获取值和修改值
		// 操作静态属性，静态属性依附于类的，非静态属性依附于对象
		Field field = clazz.getDeclaredField("pswd");
		// 静态属性不依附于对象，此时我们传一个null对象，非静态要传入具体的对象
		Object obj = field.get(null);
		System.out.println(obj);
		// 修改静态属性的值,第一个参数是对象，第二参数是要改的value
		field.set(null, "你好不好？");
		System.out.println(field.get(null));

		// ==========================
		// 非静态属性操作 1.获得非静态属性的值，非静态属性是依附对象的
		field = clazz.getDeclaredField("sname");
		// 非静态属性依附对象，此时我们必须给一个对象
		Object instance = clazz.newInstance();// 此方法是创建本类对象，调用的是无参构造器
		obj = field.get(instance);
		System.out.println(obj);
		field.set(instance, "花牛");
		System.out.println(field.get(instance));

		// 操作私有属性
		field = clazz.getDeclaredField("sscore");
		field.setAccessible(true);// 暴力反射，打开缺口，无视修饰符
		instance = clazz.newInstance();
		System.out.println(field.get(instance));
		field.set(instance, 99);
		System.out.println(field.get(instance));
	}
}
 class Person {

	public String pname;
	protected boolean psex;
	int page;
	private int pscore;
}

class Student extends Person {
	public Student() {
		sname = "hehe";
	}

	public static String pswd = "你好";
	public String sname;
	protected boolean ssex;
	int sage;
	private int sscore;
}
