package com.beans.ko.java.training.reflect.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 发射获取操作方法
 */
public class ReflectMethod {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> clazz = Class.forName("com.beans.ko.java.training.reflect.method.Student");
		//获得方法
		//1.获取单个公共的方法,方法有同名，我们靠参数来区分，没有参数就给null,有参数就给参数类对象，方法的class是可变参数
		System.out.println(clazz.getMethod("sName", null));
		System.out.println(clazz.getMethod("sName", String.class));
		System.out.println(clazz.getMethod("sName", new Class[]{String.class,int.class,boolean.class}));
		//2.获得所有的公共方法。一直到object类，所有的公共方法都能获得
		Method[] methods = clazz.getMethods();
		for(Method m:methods){
			System.out.println(m);
		}
		
		//3.获得本类声明的方法
		Method method = clazz.getDeclaredMethod("sName", null);
		System.out.println(method);
		System.out.println("==============本类声明方法====================");
		//4.获得本类声明的所有方法
		methods = clazz.getDeclaredMethods();
		for(Method m:methods){
			System.out.println(m);
		}
		//5.获取修饰符
		int modifier = method.getModifiers();
		System.out.println(modifier);
		System.out.println("程序结束");
		
		methodUse();
	}
	
	private static void methodUse() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Class<?> clazz = Class.forName("com.beans.ko.java.training.reflect.method.Student");
		//==========================================
		//操作静态方法
		//方法有静态方法和非静态方法     静态方法依附类   非静态方法依附对象
		Method method = clazz.getDeclaredMethod("show", null);
		//执行方法，需要给参数，第一个参数是要一个对象，静态的给null,第二个是可变参数，调用该方法需要的参数
		method.invoke(null, null);
		//操作有参的静态方法,要注意类型匹配
		method = clazz.getDeclaredMethod("show", String.class);
		method.invoke(null, "张三");
		//注意参数类型和顺序
		method = clazz.getDeclaredMethod("show", String.class,int.class);
		method.invoke(null, "张三",20);
		//==========================================
		//操作非静态方法，非静态方法依附于对象，此时要给对象
		method = clazz.getDeclaredMethod("sName", null);
		Object obj = clazz.newInstance();
		method.invoke(obj, null);
		method = clazz.getDeclaredMethod("sName", String.class,int.class);
		method.invoke(obj, "张三",19);
		//===========================================
		//私有方法
		method = clazz.getDeclaredMethod("sScore", null);
		method.setAccessible(true);
		System.out.println(method.invoke(obj, null));
	}
}

class Student extends Person{
	public static void show(){
		System.out.println("sShow");
	}
	public static void show(String name){
		System.out.println("sShow"+name);
	}
	public static void show(String name,int age){
		System.out.println("sShow"+name+","+age);
	}
	public void sName(){
		System.out.println("sName");
	}
	public void sName(String name){
		System.out.println("sName");
	}
	public void sName(String name,int age){
		System.out.println("sName"+age);
	}
	public void sName(String name,int age,boolean score){
		System.out.println("sName");
	}
	protected void sSex(){
		System.out.println("sSex");
	}
	void sAge(){
		System.out.println("sAge");
	}
	private int sScore(){
		return 100;
	}
}

class Person{
	public void pName(){
		System.out.println("pName");
	}
	protected void pSex(){
		System.out.println("pSex");
	}
	void pAge(){
		System.out.println("pAge");
	}
	private void pScore(){
		System.out.println("pScore");
	}
}
