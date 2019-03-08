package com.beans.ko.java.training.annotation;

public class AnnotationExample {

	@Override
	@MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 1)
	public String toString(){
		return "Overriden toString method";
	}
	
	@Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012", revision = 2)
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }
}
