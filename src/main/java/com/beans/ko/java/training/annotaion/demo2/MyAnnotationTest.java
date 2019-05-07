package com.beans.ko.java.training.annotaion.demo2;

//等价于@MyAnnotation(value="frank")
@MyAnnotation("frank")
public class MyAnnotationTest {

	public static void main(String[] args) {
		//用反射方式获得注解对应的实例对象后，在通过该对象调用属性对应的方法
		MyAnnotation annotation = MyAnnotationTest.class.getAnnotation(MyAnnotation.class);
		System.out.println(annotation.color());
		System.out.println(annotation.value());
	}
}
