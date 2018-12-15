package com.beans.ko.java.training.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取
 *	获取字符串中符合规则的字符串
 *	1.书写一个String类型的正则规则
 *	2.正则规则封装成正则对象
 *	3.把该对象和操作的字符串进行关联，并得到一个匹配引擎
 *	4.通过匹配引擎进行获取
 */
public class RegexFive {

	public static void main(String[] args) {
		getString("adsfsdf2323sdfsdfsdf1sdfsdf2333sdfsdf","[1-9]{2,}");
		getString("adsfddddsdf2323sdfsdfsdf1sdfsdf2333sdfsdf","(.)\\1{2,}");
	}

	private static void getString(String str,String regex){
		//把正则封装成正则对象
		Pattern compile = Pattern.compile(regex);
		//把该对象和操作的字符串进行关联，并得到一个匹配引擎
		Matcher matcher = compile.matcher(str);
		while (matcher.find()){
			System.out.println(matcher.group());
		}
	}
}
