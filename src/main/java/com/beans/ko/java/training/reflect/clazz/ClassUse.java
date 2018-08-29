package com.beans.ko.java.training.reflect.clazz;

/*
 * java有8种数据类型，反射有9种多了void
 */
public class ClassUse {
	public static void main(String[] args) throws ClassNotFoundException {
		//返回类对象
		Class<?> clazz = Class.forName("com.beans.ko.java.training.entity.Person");
		//访问修饰符编号
		System.out.println(clazz.getModifiers());
		//包名类名
		System.out.println(clazz.getName());
		//包名
		System.out.println(clazz.getPackage());
		//类名
		System.out.println(clazz.getSimpleName());
		//父类的类对象
		System.out.println(clazz.getSuperclass());
		//判断class对象是否一个基本数据类型
		System.out.println(long.class.isPrimitive());
		//反射类型多了void
		System.out.println(void.class.isPrimitive());
	}
}
