package com.beans.ko.java.training.design;

/**
 * 单例模式-懒汉式
 * 	本类对象并不在类加载的时候创建，而是在使用的时候创建，即在调用方法的时候赋值，切只赋值一次
 * 	优点：对象创建实际切合
 * 	缺点：线程不安全，在开始时期，多线程操作可能会产生多个对象
 *
 */
public class DesignTwo {

	public static void main(String[] args) {
		Student.getStudent().show();
		Student.getStudent().show();
	}
}

class Student{
	static int i=20;
	static Student student = null;
	private Student(){System.out.println("new Student()");}
	public static Student getStudent(){
		if(null == student){
			student = new Student();
		}
		return student;
	}
	
	public void show(){
		System.out.println("show()");
	}
}
