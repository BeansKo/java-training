package com.beans.ko.java.training.thread;

/**
 *线程的优先级
 *	优先级一共有多少级别？
 *		1--》10 最低1，最高10，默认5
 *	优先级的作用？
 *		优先级越高得到cpu时间片的机会越大，反之机会越少
 *		但是10的话也不是百分百可以拿到资源，1的话也不是百分百拿不到资源
 *		优先级高肯定比优先级低拿到的资源可能性高
 *	如何查看优先级？
 *		int getPriority() 返回线程的优先级，来源于Thread
 *	如何修改优先级？
 *		在A线程开启了B线程，那么B线程的优先级和A线程相同
 *		void setPriority() Thread.currentThread().setPriority()
 *		
 */
public class ThreadSeven {

	public static void main(String[] args) {
		System.out.println(Thread.MIN_PRIORITY);
		System.out.println(Thread.NORM_PRIORITY);
		System.out.println(Thread.MAX_PRIORITY);
		Thread.currentThread().setPriority(8);
		int level = Thread.currentThread().getPriority();
		System.out.println("main优先级："+level);
		Runnable run = new RunnableSeven();
		Thread th1 = new Thread(run,"aa");
		th1.setPriority(1);
		Thread th2 = new Thread(run,"bb");
		th2.setPriority(2);
		th1.start();
		th2.start();
	}

}

class RunnableSeven implements Runnable{
	int i=0;
	@Override
	public void run() {
		System.out.println("RunnableSeven优先级:"+Thread.currentThread().getPriority());
		for(;i<=1000;i++){
			if(Thread.currentThread().getPriority() == 1){
				System.out.println(Thread.currentThread().getName()+i);
			}
		}
	}
	
} 
