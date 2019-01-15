package com.beans.ko.java.training.innerclass.demo;

/*
 * 局部内部类
	1、局部内部类也叫区域内嵌类，局部内部类与成员内部类类似，不过，区域内嵌类是定义在一个方法中的内嵌类。
	2、使用场合：如果内部类对象仅仅为外部类的某个方法使用，使用局部内部类
	3、特征：
		用在方法内部，作用范围仅限于该方法中
		根据情况决定持有外部类对象引用
		不能使用private，protected，public修饰符
		不能包含静态成员
 */
public class School {
	String schoolName;
	String buss = "培养人才";
	int studentNum;
	public School(){}
	public School(String schoolName,String buss,int studentNum){
		super();
		this.schoolName = schoolName;
		this.buss = buss;
		this.studentNum = studentNum;
	}
	
	public void show(){
		final double tvMoney=10000L;
		final double netMoney=20000L;
		//局部内部类
		class AdverTeam {
			String teamName="frank_team";
			public void tvShow() {
				System.out.println("宣传队是:"+teamName);
				System.out.println("这是电视宣传，学校名称"+schoolName+",业务内容"+buss+",学校人数:"+studentNum+",宣传所需费用"+tvMoney);
			}
			public void netShow() {
				System.out.println("宣传队是:"+teamName);
				System.out.println("这是网络宣传，学校名称"+schoolName+",业务内容"+buss+",学校人数:"+studentNum+",宣传所需费用"+netMoney);
			}
		}
		new AdverTeam().tvShow();
		new AdverTeam().netShow();
	}
}
