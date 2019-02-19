package com.beans.ko.java.training.string;

import java.util.StringTokenizer;

//String.Split（）使用正则表达式，而StringTokenizer的只是使用逐字分裂的字符。
//如果不用正则表达式（StringTokenizer也不能使用正则表达式），StringTokenizer在截取字符串中的效率最高。
public class StringToken {

	public static void main(String[] args) {
      String str = "100|66,55:200|567,90:102|43,54";
      StringTokenizer token = new StringTokenizer(str,":,|");
      while(token.hasMoreElements()){
      	System.out.println(token.nextToken());
      }

	}

}
