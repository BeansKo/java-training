package com.beans.ko.java.training.stream;

import java.util.function.Consumer;

public class ConsumerOne {
	public static void main(String[] args) {
		consumerTest();
	}
	
	public static void consumerTest(){
		Consumer f = System.out::println;
		Consumer f2 = n -> System.out.println(n + " F2");
		
		//执行完F后再执行F2的Accept方法
		f.andThen(f2).accept("test");
		
		 //连续执行F的Accept方法
	    f.andThen(f).andThen(f).andThen(f).accept("test1");
	}
}
