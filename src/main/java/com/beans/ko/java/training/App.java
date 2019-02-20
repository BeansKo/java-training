package com.beans.ko.java.training;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class App {
	private Class<?> clazz = getClass();
	public static void main(String[] args) {
		App app = new App();
		System.out.println(new Date().getTime());
		System.out.println(System.currentTimeMillis());
		System.out.print(LocalDate.now());
		System.out.println(app.clazz);
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		
		
		Integer i = null;
		String str = String.valueOf(i.intValue());
		System.out.println(str);
//		String str1 = Integer.toString(i);
	}
}
