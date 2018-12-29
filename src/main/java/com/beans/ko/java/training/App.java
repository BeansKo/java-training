package com.beans.ko.java.training;

import java.time.LocalDate;
import java.util.Date;

public class App {

	public static void main(String[] args) {
		
		System.out.println(new Date().getTime());
		System.out.println(System.currentTimeMillis());
		System.out.print(LocalDate.now());
	}

}
