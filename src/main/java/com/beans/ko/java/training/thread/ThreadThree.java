package com.beans.ko.java.training.thread;

/**
 * 线程不同步问题
 * 卖票
 * 	3个窗口同时去卖50张票
 *	如果卖出相同编号的票就是问题
 *	如果多卖票也是问题
 *	不交替卖票也是问题
 *
 *问题原因
 *	多个线程去同时访问共享资源
 *如何解决这种问题？使用线程同步锁，来保证线程同步
 *
 */
public class ThreadThree {

	public static void main(String[] args) {
		RunnableThree rt = new RunnableThree();
		new Thread(rt,"售票窗口1").start();
		new Thread(rt,"售票窗口2").start();
		new Thread(rt,"售票窗口3").start();
	}
}

class RunnableThree implements Runnable{
	int i = 0;
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"_窗口启动");
		for (; i < 50; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"..卖出.."+i);
		}
		System.out.println(Thread.currentThread().getName()+"_窗口关闭");
	}
	
}
