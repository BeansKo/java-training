package com.beans.ko.java.training.regex;

/**
 * 正则切割和替换
 * public String[] split(String regex) 根据指定的正则表达式的匹配拆分此字符串。
 *	把字符串按指定规则进行切割，被切割的部分以数组放回
 *	按照点切 
 *	 	在正则里.有特殊含义，代表了任意字符。在正则里"\."代表.
 *      我们在java里\也是转译字符，故要写成\\.才是正则里的一个.
 *	按照空格切 
 *		注意不同长度的空格
 *  反斜杠切 
 *  	要双倍给反斜杠
 *  按照单词进行切割 
 *  	\b 正则里\b代表单词边界
 *  按照叠词进行切割,叠词--重复两次及以上
 *  	按照叠词切割，需要组的概念
 *  	如何判断是一个叠词？把后面的字符和前面的比较，如果一致就代表是叠词
 *  	发现后面总是在使用前面的规则，此时可以把规则封装成组
 *  	组的概念：
 *  		封装规则，以便重复使用
 *  		重复使用封装好的规则
 *  	正则里如何封装组
 *  		一对小括号括起来一个规则，就是一个组(.)
 *  		组是有索引的，从0开始，但是我们定义的组从1开始
 *  		如何判断出组的索引呢?
 *  		(((())))
 *  		从开始处计算第一个"("就是第一组,第二个"("就是第二组，不去考虑")"
 *  		如何引用组？引用组使用\数字 \1代表第一组 \2代表第二组 ,java中要写成\\数字
 *  		(.)\\1+ 组可以校验一位，引用一次代表检验一次，+代表一次或多次
 */
public class RegexThree {

	public static void main(String[] args) {
		//点切割
		splitString("www.baidu.com","\\.");
		//空格切
		splitString("jin tian     zhen de      hen  bu cuo"," +");
		//反斜杠切
		splitString("c:\\text\\a\\b\\c","\\\\");
		//按照单词切
		splitString("**jin**tian********zhen*de****hen*********bu*cuo","\\b[a-z]{3}\\b");
		//按照数字叠词切割
		splitString("abc22ba2dfgs222sdfsdf3333ff","(\\d){2,3}");
		//按照叠词切割
		splitString("abc22ba2dffgs222sddfsdf3333ff","(.)\\1+");
		//按照d或者s切割
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
		System.out.println("---------------------------");
	}
}
