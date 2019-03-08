package com.beans.ko.java.training.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {

	public static void main(String[] args) {
		try {
			for(Method method : AnnotationParsing.class
					.getClassLoader()
					.loadClass("com.beans.ko.java.training.annotation.AnnotationExample")
					.getMethods()){
				if(method.isAnnotationPresent(com.beans.ko.java.training.annotation.MethodInfo.class)) {
					for(Annotation anno : method.getDeclaredAnnotations()) {
						System.out.println("Annotaion in Method '"+method+"':"+anno);
					}
					MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
					if(methodAnno.revision() == 1) {
						System.out.println("Method with revision no 1 = " + method);
					}
				}
				
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
