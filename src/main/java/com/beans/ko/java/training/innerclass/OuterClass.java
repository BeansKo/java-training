package com.beans.ko.java.training.innerclass;

import com.beans.ko.java.training.jvm.StringTest;

/**
 * 内部类如何去创建对象
 * 外部类和内部类的访问方式，如何互相访问
 * 作用范围
 * 使用技巧，应用场景
 * @author fl76
 *
 */
//外部类
public class OuterClass {
	//成员内部类
	public class InnerClass{}
	
	public static void main(String[] args){
		//局部内部类
		class InnerClass1{}
	}
	
	//静态内部类，是类的静态成员，不能出现在方法或语句块中，被称为局部内部类
	static class InnerClass2{}
	
	//匿名内部类
	StringTest t = new StringTest(){};
}
