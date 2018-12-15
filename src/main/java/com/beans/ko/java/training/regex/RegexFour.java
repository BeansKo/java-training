package com.beans.ko.java.training.regex;

/**
 * 替换
 * replaceAll(String regex, String replacement) 参数1为正则表达式，参数2为被替换的字符系列
 * 
 *
 */
public class RegexFour {

	public static void main(String[] args) {
		//点替换
		System.out.println(replaceStr("www.baidu.com","\\.","@"));
		//空格
		System.out.println(replaceStr("www   baidu   com"," +","@"));
		//按照单词来替换成三个***
		System.out.println(replaceStr("www   baidu   com","\\b[a-zA-Z]{3}\\b","*"));
		//\替换成三个*
		System.out.println(replaceStr("www\\baidu\\\\com","\\\\","*"));
		//按叠词来替换3个及以上的叠词替换成***
		System.out.println(replaceStr("sdfsdfrwewerfffsdswerwdddssssfdfdkkk","(.)\\1{2,}","*"));
		//叠词去重3个及以上替换成1个,且由[]扩起来,需要用到$
		//$数字，$代表引用组的规则，数字代表引用的是那一个组
		System.out.println(replaceStr("sdfsdfrwewerfffsdswerwdddssssfdfdkkk","(.)\\1{2,}","[$1]"));
	}
	
	public static String replaceStr(String oldStr,String regex,String newStr){
		return oldStr.replaceAll(regex, newStr);
	}

}
