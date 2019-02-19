package com.beans.ko.java.training.ex;

import java.io.File;
import java.io.FileInputStream;


public class ExceptionOne {

	public static void main(String[] args) {
		File f = new File("a.txt");
		try {
			//检查异常
			@SuppressWarnings("resource")
			FileInputStream in = new FileInputStream(f);
			int s = in.read();
			System.out.println((char)s);
		} catch (Exception e) {
			//catch中处理检查异常，发现检查异常，catch中会去处理，保证程序继续运行
			e.printStackTrace();
			//抛出运行异常，终止程序，程序不会继续执行
			throw new RuntimeException("aa");
		}
		System.out.println("OK");
		//运行异常，发现运行异常，程序会立即终止，不会继续运行
		int i=10;
		double resutlt = i/0;
		System.out.println("OK"+resutlt);		
	}

}
