package com.beans.ko.java.training.regex;

/**
 * 正则切割和替换
 *
 */
public class RegexThree {

	public static void main(String[] args) {
		splitString("adfsdfsdf","[ds]");
	}
	
	public static void splitString(String str, String regex){
		String[] strs = str.split(regex);
		for(String s : strs){
			System.out.println(s);
			if(s.equals("")){
				System.out.println("nll");
			}
		}
	}
}
