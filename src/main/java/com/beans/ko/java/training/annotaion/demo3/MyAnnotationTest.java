package com.beans.ko.java.training.annotaion.demo3;

@MyAnnotation(
		color="red",
		value="frank",
		arrayAttr={3,5,6},
		lamp=EumTrafficLamp.GREEN,
		annotationAttr=@MetaAnnotation("gacl"))
public class MyAnnotationTest {

	public static void main(String[] args) {
		if(MyAnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
			  /**
             * 用反射方式获得注解对应的实例对象后，在通过该对象调用属性对应的方法
             * MyAnnotation是一个类，这个类的实例对象annotation是通过反射得到的，这个实例对象是如何创建的呢？
             * 一旦在某个类上使用了@MyAnnotation，那么这个MyAnnotation类的实例对象annotation就会被创建出来了
             */
			MyAnnotation annotation = (MyAnnotation)MyAnnotationTest.class.getAnnotation(MyAnnotation.class);
			System.out.println(annotation.color());
			System.out.println(annotation.value());
			System.out.println(annotation.arrayAttr().length);
			System.out.println(annotation.lamp());
			System.out.println(annotation.annotationAttr().value());
			MetaAnnotation ma = annotation.annotationAttr();
			System.out.println(ma.value());
		}
	}

}
