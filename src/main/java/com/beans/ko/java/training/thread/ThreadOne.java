package com.beans.ko.java.training.thread;

/**
 *创建线程 
 *创建线程共有三种方式：
 *	第一种方式
 *		集成Thread类
 *		重写run方法
 *		创建该类对象，调用start方法
 *开启两个线程：
 *	发现问题：main方法结束了，main线程结束了，程序还没有结束
 *	正常，每个线程都有自己的执行内容，内容执行完毕之后线程会结束
 *	main线程执行的是main方法的内容
 *	创建线程，他的方法体是run方法
 *	调用start方法，此时新的线程开启了
 *	步骤学习：
 *		1.继承Thread类，Thread就是线程类，它的子类都是线程，都有run方法和start方法
 *		2.覆盖run方法，run方法就是该线程的执行体，自行实现的必须要重写该方法，不重写没有意义
 *		3.start方法沟通操作系统，是开启线程的方法
 *		4.如果不调用start方法，而是直接调用run方法，那么就是简单的方法调用，和开启线程没有任何关系
 *方法getName 得到当前线程名字，我们没有给，会得到默认名字
 *	第一种方式创建线程，有没有缺陷？
 *		1.Java单继承，继承了Thread类，那么就不能再集成其他类了
 *		2.每个线程都是独立的，没有共享数据，例如实现买票功能，5个线程卖10张票，会造成卖出50张票的问题
 */
public class ThreadOne {

	public static void main(String[] args) {
		Thread th1 = new Test("frank");
		th1.start();
		//创建多个线程
		Thread th2 = new Test("foster");
		Thread th3 = new Test("lola");
		th2.start();
		th3.start();
		for(int i=0;i<50;i++){
			System.out.println("main....."+i);
		}
	}

}

class Test extends Thread{

	public Test(String name){
		super(name);
	}
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			System.out.println(this.getName()+"_run....."+i);
		}
	}
	
}
