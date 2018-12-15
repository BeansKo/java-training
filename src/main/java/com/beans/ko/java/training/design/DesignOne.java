package com.beans.ko.java.training.design;

/**
 * 单例模式-饿汉式
 *	一个类只能有一个对象，而且外界可以使用这个对象
 *	1.构造器私有化,外界不能创建对象
 *	2.提供一个公共的GET方法给予外界该对象，外界通过这个方法得到该类的对象
 *	3.本类的对象作为本类的属性，并且赋值，必须有static修饰
 *	数据库连接池一般采用单例
 *	优点：线程安全
 *	缺点：加载时间可能过早，有可能该对象不用，但是加载了。
 */
public class DesignOne {

	public static void main(String[] args) {
		Person.getPerson().show();
		Person.getPerson().show();
		Person.getPerson().show();
	}
}

class Person {
	static Person person = new Person();
	private Person(){
		System.out.println("new Person()");
	}
	public static Person getPerson(){
		return person;
	}
	public synchronized void show(){
		System.out.println("show()");
	}
}
