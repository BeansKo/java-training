package com.beans.ko.java.training.jvm;

public class StringTest {

	public static void main(String[] args) {
		//常量池中保存一份对象
		String str="你好";
		String strs="你好";
		System.out.println(str.equals(strs));
		System.out.println(str==strs);
		//堆里创建新对象
		String s = new String("你好");
		System.out.println(str.equals(s));
		System.out.println(str==s);
		
		
		//对象不可达说明
		String ss = new String("你好");
		//变量ss指向了堆里面的对象，在有地址可达的时候，该对象会一直存在不会被回收。
		ss = null;
		//此时ss里面没有到达之前对象的地址了
		//此时GC进行检测的话会发送该对象为垃圾，就会把它回收
		System.gc();
	}

}
