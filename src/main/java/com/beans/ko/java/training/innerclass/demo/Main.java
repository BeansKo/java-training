package com.beans.ko.java.training.innerclass.demo;

import com.beans.ko.java.training.innerclass.demo.Company.Clear;

/**
 * 使用内部类有什么好处；
		1）实现多重继承; 
		2）内部类可以很好的实现隐藏：一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
		3）减少了类文件编译后的产生的字节码文件的大小
 *
 */
public class Main {

	public static void main(String[] args) {
		//-------成员内部类
		Body body = new Body("两个胳膊","两条腿","血");
		body.check();
		Body.Heart heart = body.new Heart();
		heart.work();
		
		//-------静态内部类
		Company.Clear zcl = new Company.Clear();
		zcl.work("frank");
		//静态内部类不会依赖外部类存在
		Clear.saodi();
		
		//-------局部内部类
		School beida = new School("北大","互联网培训",1000);
		beida.show();
	}

}
