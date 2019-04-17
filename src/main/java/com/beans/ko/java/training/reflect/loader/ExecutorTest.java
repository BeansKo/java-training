package com.beans.ko.java.training.reflect.loader;

public class ExecutorTest {

	public static void main(String[] args) {
		testExecuteV1();
		testExceuteV2();
	}
	
	public static void testExecuteV1() {
		Executor executor = new ExecutorProxy("v1");
		executor.execute("TOM");
	}
	
	public static void testExceuteV2() {
		Executor executor = new ExecutorProxy("v2");
		executor.execute("TOM");
	}

}
