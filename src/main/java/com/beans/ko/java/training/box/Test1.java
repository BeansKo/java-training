package com.beans.ko.java.training.box;

public class Test1 {

	public static void main(String[] args) {
		int i = 3;
		long l = 9;
		//装箱
		Integer in = new Integer(i);
		Long lo = new Long(l);
		
		//拆箱
		int a = in.intValue();
		long b = lo.longValue();
		
		//这种装换NULL会返回"null"
		String str = String.valueOf(in);
		System.out.println(str);
		String s = Integer.toString(i);
		System.out.println(s);

	}

}
