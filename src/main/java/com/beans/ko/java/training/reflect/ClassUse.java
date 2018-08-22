package com.beans.ko.java.training.reflect;

/*
 * java有8种数据类型，反射有9种多了void
 */
public class ClassUse {
	public static void main(String[] args) throws ClassNotFoundException {
		//返回类对象
		Class<?> clazz = Class.forName("com.beans.ko.java.training.entity.Person");
		int i = clazz.getModifiers();
		System.out.println(i);
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getSuperclass());
		System.out.println(long.class.isPrimitive());
		//反射类型多了void
		System.out.println(void.class.isPrimitive());
	}
}
