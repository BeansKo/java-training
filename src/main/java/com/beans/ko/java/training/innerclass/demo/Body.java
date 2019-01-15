package com.beans.ko.java.training.innerclass.demo;

/*
 * 成员内部类
 * 访问方式
 * Outer outer = new Outer();
 * Outer.Inner inner = outer.new Inner();
 */
public class Body {
	String arm;
	String leg;
	String blood;
	
	public Body(String arm, String leg, String blood) {
		super();
		this.arm = arm;
		this.leg = leg;
		this.blood = blood;
	}
	
	//定义外部类方法
	public void check(){
		//通过内部类的实例化对象调用方法
		new Heart().work();
	}
	
	//成员内部类
	class Heart {
		String name;
		void work() {
			System.out.println("心脏正在给"+arm+leg+"输"+blood);
		}
	}
	
}
