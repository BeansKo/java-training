package com.beans.ko.java.training.regex;

/**
 * 正则匹配
 * 正则符号
 * 	public boolean matches(String regex) 告知此字符串是否符合传入的正则
 * 正则符号含义
 * 	[]一对中括号，只能校验一位
 *	[abc] 只能是abc其中的一个，且只能是一位
 *	[^abc] 除了中括号里面给出其余任意字符都可以
 *	[a-z] a到z都可以，两头字母包括在内
 *	[A-Z] A到Z都可以，两头字母包括在内
 *	[a-d[m-p]] a到d或m到p:[a-dm-p](并集)
 *	[a-z&&[def]] d、e或f（交集）
 *	[a-z&&[^bc]] a到z除了b和c
 *	[a-z&&[^m-p]] a到z,而非m到p
 * 预定义字符类
 * 	.代表任意字符（与\n\r可能匹配也可能不匹配）
 * 	\d 数字：[0-9]  \d报错的原因，是因为java中没有这个转译字符，正则里\d是一体的，所以要写成\\d
 * 	\D 非数字：[^0-9]
 * 	\s 空白字符:[ \t\n\x0B\f\r]
 * 	\S 非空表 :[^\s]
 * 	\w 单词字符：[a-zA-Z_0-9]
 * 	\W 非单词字符:[^\w]
 * 计数
 * 	x? x,0或1次
 * 	x* x,0或多次
 * 	x+ x,一次或多次
 * 	x{n} x,恰好n次
 * 	x{n,} x，至少n次
 * 	x{n,m} x，至少n次，但不能超过m次
 */
public class RegexTwo {

	public static void main(String[] args) {
		//并集
		matcherString("oc","[a-d[m-p]][c]");
		
		matcherString("9","[^\\d]");
		
		matcherString(" ","[\\s]");
		
		matcherString("123","\\d*");
		
		matcherString("123333","\\d{6,8}");
	}
	
	public static void matcherString(String str, String regex){
		System.out.println(str.matches(regex));
	}

}
