package com.beans.ko.java.training.thread;

/**
 *synchronized
 *	格式1：所用于代码块 synchronized(对象){}
 *
 *作用
 *	这是线程锁，被锁住的是一个对象
 *  多个线程在访问时，只有一个线程能够拿到锁，进入去执行代码块
 *  在任意时间，只有一个线程可以拿到锁
 *  每当有线程进行访问该代码块的时候，会首先判断这个代码块有没有锁住
 *  	1.锁住的话，那么就在外面等待
 *  	2.如果没有锁住的话，那么自己进入，把代码块锁上
 *       很像火车上的厕所
 *      3.对象解析，锁的单位是对象，通常这个对象是共享资源
 * 锁那个对象非常重要，如果需要实现同步，使用了锁还没有达到预期结果，那么肯定锁的东西不对
 * 锁的对象是共享数据，共享的数据可能有很多，锁住其中一个就可以   
 * 我们有必要专门去写一个对象，用来被锁吗？不需要，使用this就可以
 * 锁保证了什么？
 * 	保证了同一时间，只有一个线程可以操作这段代码
 * 	防止了多个线程同时去操作共享数据，影响线程同步
 */
public class ThreadFour {

	public static void main(String[] args) {
		RunnableFour rt = new RunnableFour();
		new Thread(rt,"售票窗口1").start();
		new Thread(rt,"售票窗口2").start();
		new Thread(rt,"售票窗口3").start();
	}

}
class RunnableFour implements Runnable{
	//一定是线程共享变量，没有必要专门去写一个对象用来被锁，一般会去锁this
	public Object obj = new Object();
	@Override
	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"_获得锁");
			System.out.println(Thread.currentThread().getName()+"_放弃锁");
		}

	}
	
}