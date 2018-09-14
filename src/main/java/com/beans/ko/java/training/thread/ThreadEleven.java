package com.beans.ko.java.training.thread;

import com.beans.ko.java.training.entity.ShouJi;

/**
 * 等待唤醒机制
 * 	void wait()在其他线程调用此对象的notify()方法或notifyAll()方法，导致当前线程等待。
 * 	遇到wait方法，当前线程进入沉睡状态
 * 	void notify()唤醒在此对象监视器上等待的单个线程
 * 	遇到notify唤醒正在沉睡的线程
 * 唤醒的是哪个线程呢？ 唤醒的是在同一个锁内的线程
 * 注意：在锁中才能使用wait和notify,对象监视器指的就是锁
 * 如果拿到锁，在同步代码块里面执行，遇到了wait,锁会释放吗？
 * 	会释放,wait()的时候，其他线程会执行锁的内容，但是sleep不会
 * 如果有多个线程在等待，那么先唤醒哪一个？
 * 	内存里有一个等待区，唤醒时会去等待区寻找，谁先进入等待区，谁就先被唤醒
 *
 */
public class ThreadEleven {

	public static void main(String[] args) throws InterruptedException {
		//唤醒机制基础部分
//		RunnableEleven run11 = new RunnableEleven();
//		new Thread(run11,"aa").start();
//		new Thread(run11,"bb").start();
//		new Thread(run11,"cc").start();
		
		//生产消费部分 注意唤醒机制，操作的必须是同一个对象
		ShouJi shouJi = new ShouJi();
		Runnable sc = new ShengChanEleven(shouJi);
		Runnable xf = new XiaoFeiEleven(shouJi);
		new Thread(sc,"aa").start();
		Thread.sleep(1000);
		new Thread(xf,"bb").start();
	}

}

class RunnableEleven implements Runnable{

	@Override
	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+" 进入了锁");
			//唤醒别人
			notify();
			System.out.println(Thread.currentThread().getName()+" 唤醒了另一线程，自己开始等待");
			try {
				System.out.println(Thread.currentThread().getName()+" 开始等待");
				//等待
				wait();
				//sleep等待的时候其他线程不能访问锁内容，但是wait可以
				//Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" 结束等待");
		}
	}
	
}

/**
 * 生产者
 *
 */
class ShengChanEleven implements Runnable{

	private ShouJi shouJi;
	
	public ShengChanEleven(){}
	
	public ShengChanEleven(ShouJi shouJi){
		this.shouJi = shouJi;
	}
	
	@Override
	public void run() {
		int i = 0;
		while (true) {
			synchronized (shouJi) {
				if (i % 2 == 0) {
					shouJi.setId("hw" + i);
					shouJi.setName("华为");
				} else {
					shouJi.setId("pg" + i);
					shouJi.setName("苹果");
				}
				i++;
				shouJi.notify();//唤醒消费线程
				try {
					shouJi.wait();//自己进入沉睡
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/**
 * 消费者
 *
 */
class XiaoFeiEleven implements Runnable{
	private ShouJi shouJi;
	
	public XiaoFeiEleven(){}
	
	public XiaoFeiEleven(ShouJi shouJi){
		this.shouJi = shouJi;
	}

	@Override
	public void run() {
		while(true){
			synchronized (shouJi) {
				System.out.println(shouJi.toString());
				//唤醒生产
				shouJi.notify();
				try {
					//等待
					shouJi.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
