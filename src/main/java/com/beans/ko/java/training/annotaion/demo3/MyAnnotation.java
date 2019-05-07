package com.beans.ko.java.training.annotaion.demo3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyAnnotation {
	String color() default "blue";
    /**
     * 为注解添加value属性，这个value属性很特殊，如果一个注解中只有一个value属性要设置，
     * 那么在设置注解的属性值时，可以省略属性名和等号不写， 直接写属性值，如@SuppressWarnings("deprecation")，
     * 这里的MyAnnotation注解设置了两个String类型的属性，color和value，
     * 因为color属性指定有缺省值，value属性又是属于特殊的属性，因此使用MyAnnotation注解时
     * 可以这样使用MyAnnotation注解："@MyAnnotation(color="red",value="xdp")"
     * 也可以这样使用："@MyAnnotation("孤傲苍狼")"，这样写就表示MyAnnotation注解只有一个value属性要设置，color属性采用缺省值
     * 当一个注解只有一个value属性要设置时，是可以省略"value="的
     */
	String value();
	int[] arrayAttr() default {1,2,4};
	EumTrafficLamp lamp() default EumTrafficLamp.RED;
	MetaAnnotation annotationAttr() default @MetaAnnotation("xdp");
}
