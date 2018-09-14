package com.beans.ko.java.training.thread;

/**
 * 如何结束一个线程，让它进入死亡状态
 *	API提供的方法都是不靠谱的
 * 我们只能自己想办法
 * 	线程的状态 ：新生-》就绪-》执行（阻塞）-》死亡
 * 	线程体执行结束，线程也就结束了。那么能不能操作线程体呢？决定是否结束呢？ 可以的
 * 当线程进入睡眠状态，该如何结束？ void interrupt()中断线程的冻结状态
 */
public class ThreadThirteen {

	public static void main(String[] args) throws Exception {
		//没有进入睡眠状态的线程停止
		RunnableThirteen run = new RunnableThirteen();
		new Thread(run).start();
		Thread.sleep(1000);
		System.out.println("开关");
		run.b=false;
		System.out.println("main....");
		
		//进入睡眠状态的线程，冻结后再停止
		RunnableThirteen1 run1 = new RunnableThirteen1();
		Thread th = new Thread(run1);
		th.start();
		Thread.sleep(1000);
		System.out.println("开关");
//		run1.b=false;
		//中断睡眠线程
		th.interrupt();
		System.out.println("main....");
	}
}

class RunnableThirteen implements Runnable{
	boolean b=true;
	@Override
	public void run() {
		int i=0;
		
		while(b){
			System.out.println(i++);
		}
		System.out.println("线程结束了");
	}
}

class RunnableThirteen1 implements Runnable{
	boolean b=true;
	@Override
	public void run() {
		int i=0;
		while(b){
			synchronized (this) {
				System.out.println(i++);
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println("外界想要结束，我就结束自己");
					return;
				}
			}
		}
		System.out.println("冻结线程结束了");
	}
}
