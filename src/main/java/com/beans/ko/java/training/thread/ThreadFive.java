package com.beans.ko.java.training.thread;

/**
 * 同步方法 synchronized 格式2
 *	在代码块时，有一个小括号，里面放的是锁的对象
 *	同步方法时没有小括号了，那么此时锁的是什么呢？
 *	run方法可不可以加锁？ 可以加锁，但是没有意义，强制变成了单线程
 *
 *同步方法可以实现同一时间只有一个线程可以拿到锁，可以操作共享数据和同步代码块功能一致
 *在代码块时，有一个小括号，里面放的是锁对象
 *同步方法时没有小括号了，那么此时锁的是什么呢？锁的是this,锁的是当前对象。同步代码块可以指定锁得对象
 */
public class ThreadFive {

	public static void main(String[] args) throws InterruptedException {
		RunnableFive run = new RunnableFive();
		new Thread(run,"晋三").start();
		
		Thread.sleep(1000);
		RunnableFive.b=false;
		new Thread(run,"秘籍").start();
		new Thread(run,"务工").start();
		System.out.println("end");
	}
}

class RunnableFive implements Runnable{
	Object obj = new Object();
	static boolean b = true;
	
	@Override
	public void run() {
		if(b){
			//这里锁住this和obj会有不同,如果两个锁对象相同，只会有一段代码执行
			synchronized (obj) {
				System.out.println(Thread.currentThread().getName()+"obj");
				while(true){}
			}
		} else {
			synchronized (this) {
				show();
			}
		}
	}
	
	public synchronized void show(){
		System.out.println(Thread.currentThread().getName()+"show");
		while(true){}
	}
}
