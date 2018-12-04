package com.beans.ko.java.training.regex;

/**
 * 什么是正则表达式？符合正确规则的表达式
 * 正则表达式的作用？完成对字符串的操作，String已经提供了很多字符串的操作方法，为什么还要正则呢？String提供的，在很多操作时候非常的繁琐，不够用。
 * 正则表达式的优缺点？
 * 	优点：简化了操作，用一些符号来表达特殊的含义，书写简单，减少代码量，逻辑清晰。
 * 	缺点：可读性比较差，针对复杂操作时。
 * 正则主要操作？
 * 	匹配
 * 	切割
 * 	替换
 * 	获取
 */
public class RegexOne {
	public static void main(String[] args) {
		
	}
	
	public static boolean regexString(String str){
		String regex = "[1-9][0-9]{4,11}";		
		return str.matches(regex);
	}

}
