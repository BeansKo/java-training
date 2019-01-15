package com.beans.ko.java.training.innerclass.demo;
/**
 * 静态内部类
	1、内部类如果使用static声明，则此内部类就称为静态内部类。（其实也相当于外部类）可以通过外部类 . 内部类来访问。
	2、静态内部类使用场合：内部类不需要外部类的实例（注意区分成员内部类），静态内部类存在仅仅为外部类提供服务或者逻辑上属于外部类，且逻辑上可以单独存在。
	3、静态内部类的特征：
		静态内部类不会持有外部类的引用
		静态内部类可以访问外部的静态变量，如果访问外部类的成员变量必须通过外部类的实例访问
	4、Java中只有内部类才可以是静态的
		使用格式：Outer.Inner inner = new Outer.Inner();
	静态内部类的加载不需要依附外部类，在使用时才加载。不过在加载静态内部类的过程中也会加载外部类。
 */
public class Company {
	String companyName;
	static String country;
	private int i=5;
	
	static class Clear {
		String name;
		public Clear() {}
		
		public Clear(String name) {
			super();
			this.name = name;
		}
		
		public void work(String name) {
			String sname = new Company().companyName="阿里";
			country  = "中国";
			//获取外部类的非静态成员
			int num = new Company().i;
			System.out.println(name + "为" + sname +"打扫卫生，该公司属于"+ country +",打扫"+num+"次");
		}
		
		public static void saodi(){
			System.out.println("我们都会扫地");
		}
	}
}
