package com.beans.ko.java.training.thread;

/**
 * 等待唤醒测试,一般第一个进入睡眠的先被唤醒
 * 
 * 守护线程又叫后台线程
 * 	当所有的前台线程都结束之后，守护线程会自动结束
 * 什么是前台线程？我们创建的线程都是前台线程
 * 查看是否是守护线程 boolean isDaemon()
 * 如何创建一个守护线程？
 * 	void setDaemon（true）将该线程标注为守护线程
 * 举例后台线程，垃圾回收线程
 */
public class ThreadTwelve {

	public static void main(String[] args) throws InterruptedException {
		Object obj = new Object();
		shouHu shouHu = new shouHu();
		Thread th = new Thread(shouHu,"shouhu");
		th.setDaemon(true);
		System.out.println(Thread.currentThread().getName()+":"+th.isDaemon());
		th.start();
		RunnableTwelve1 run1 = new RunnableTwelve1(obj);
		RunnableTwelve2 run2 = new RunnableTwelve2(obj);
		new Thread(run1,"aa").start();
		new Thread(run1,"bb").start();
		new Thread(run1,"cc").start();
		Thread.sleep(1000);
		new Thread(run2,"dd").start();
		
		System.out.println(Thread.currentThread().isDaemon());
	}

}

class RunnableTwelve1 implements Runnable{

	Object obj;
	
	public RunnableTwelve1() {
	}

	public RunnableTwelve1(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public void run() {
		synchronized (obj) {
			System.out.println(Thread.currentThread().getName()+"_开始等待");
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"_等待结束");
		}
	}
	
}


class RunnableTwelve2 implements Runnable{

	Object obj;
	
	public RunnableTwelve2() {
	}

	public RunnableTwelve2(Object obj) {
		super();
		this.obj = obj;
	}
	
	@Override
	public void run() {
		synchronized (obj) {
			System.out.println(Thread.currentThread().getName()+"_唤醒一个");
//			obj.notify();
			obj.notifyAll();
		}
	}
}

class shouHu implements Runnable{

	@Override
	public void run() {
		while(true){
			System.out.println("守护者...");
		}
	}
	
}