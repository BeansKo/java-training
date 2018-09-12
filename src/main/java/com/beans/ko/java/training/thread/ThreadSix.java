package com.beans.ko.java.training.thread;

/*
 * 什么是死锁？
 * 	往往是一个线程手持另一个线程的锁，而另一个线程持有前者的锁，互相拿不到锁，只能陷入无尽的等待
 * 例如：
 * 	线程A持有obj1的锁
 * 	线程B持有obj2的锁
 * 	A要去拿到obj2的锁
 * 	B要去拿到obj1的锁
 * 	此时obj1,obj2都被线程持有
 */
public class ThreadSix {

	public static void main(String[] args) throws InterruptedException {
		Runnable runn = new RunnableSix();
		new Thread(runn,"aa").start();
		Thread.sleep(3000);
		RunnableSix.b=false;
		new Thread(runn,"bb").start();
		System.out.println("end");
	}

}

class RunnableSix implements Runnable{
	static boolean b = true;
	Object obj1 = new Object();
	Object obj2 = new Object();
	@Override
	public void run() {
		if(b){
			synchronized (obj1) {
				System.out.println(Thread.currentThread().getName()+"拿到了obj1");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"睡觉结束");
				synchronized(obj2){
					System.out.println("最终拿到了");
				}
			}
		}else{
			synchronized (obj2) {
				System.out.println(Thread.currentThread().getName()+"拿到了obj2");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"睡觉结束");
				synchronized (obj1) {
					System.out.println("最终拿到了");
				}
			}
		}
	}
	
}
