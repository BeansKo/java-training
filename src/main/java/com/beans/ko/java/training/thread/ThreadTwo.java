package com.beans.ko.java.training.thread;

/**
 * 产生线程的第二种方式
 * 	1.实现Runnable接口
 * 	2.实现重写run方法
 * 	3.创建实现Runnable的实现类对象
 * 	4.根据Runnable类的对象去构建一个Thread类的对象
 * 	5.开启Thread对象
 * 
 * 优势：
 * 	1.仍然可以继承其他类
 * 	2.共享数据操作起来变得简单
 * 
 * 模拟卖票功能 3个窗口同时卖票，共有100张票
 * 	发现问题为什么卖了300张？
 * 		1.每个线程都开启了
 * 		2.卖票的条件在方法里，方法从属于队形，每个对象之间的信息不互通，操作的都是自己单独的数据
 * 	如何解决？
 * 		让每个线程都去操作一个数据
 * 		实际上runnable对象只有一个，Thread对象都是根据runnable对象构建的，此时Thread都有i这个成员变量，并且共享
 *
 */
public class ThreadTwo {

	public static void main(String[] args) {

		Runnable run = new TestRun();
		//根据Runnable类的对象去构建一个Thread类的对象
		Thread th = new Thread(run,"售票员1");
		th.start();
		Thread th1 = new Thread(run,"售票员2");
		th1.start();
		Thread th2 = new Thread(run,"售票员3");
		th2.start();
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println(i);
//		}
	}
}

class TestRun implements Runnable{
	//线程共享数据，但是没有加锁，会发生线程不同步的问题
	int i=0;
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"_来了");
		for (; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+"_"+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"_走了");
	}
}
