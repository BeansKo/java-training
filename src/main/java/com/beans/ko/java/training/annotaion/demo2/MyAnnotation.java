package com.beans.ko.java.training.annotaion.demo2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyAnnotation {
	/**
	 * 定义基本属性
	 * @return
	 */
	String color() default "blue"; //为属性指定缺省值
	String value();
}
