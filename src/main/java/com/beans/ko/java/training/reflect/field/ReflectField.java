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
		//获得类对象
		Class<?> clazz = Class.forName("com.beans.ko.java.training.reflect.field.Student");
		//使用反射获取属性，只能获取单个public的属性，也可以或去父类的公开属性
		Field field = clazz.getField("pname");
		System.out.println(field);
		//使用反射获取所有public的属性数组
		Field[] fields = clazz.getFields();
		for(Field f:fields){
			System.out.println(f);
		}
		//使用反射获取单个本类声明的属性,不能获得父类
		field = clazz.getDeclaredField("sage");
		System.out.print(field);
		//使用反射获取所有本类声明的属性数组
		fields = clazz.getDeclaredFields();
		for(Field f:fields){
			System.out.println(f);
		}
		//获取所有父类的属性
		fields = clazz.getSuperclass().getDeclaredFields();
		for(Field f:fields){
			System.out.println(f);
		}
		
		System.out.println("end");
		operation();
	}
	/**
	 * 操作属性有权限控制
	 * 属性有静态和非静态
	 */
	public static void operation() throws Exception{
		Class<?> clazz = Class.forName("com.beans.ko.java.training.reflect.field.Student");
		//操作静态属性，静态属性依附于类的，非静态属性依附于对象
		Field field = clazz.getDeclaredField("pswd");
		System.out.println(field);
		Object obj = field.get(null);
		System.out.println(obj);
	}
}
