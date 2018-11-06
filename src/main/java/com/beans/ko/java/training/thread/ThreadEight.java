package com.beans.ko.java.training.thread;

/**
 * yield学习 public static void yield() 暂停当前线程对象的执行，交给cpu，让cpu重新选择线程的执行
 * 需要在计划任务中设置相关性才能跑程这个例子
 * 让cpu重新选择，只有在对方线程优先级高于自己的时候才有效
 */
public class ThreadEight {

	public static void main(String[] args) {
		RunableEight run = new RunableEight();
		Thread th1 = new Thread(run,"aa");
		Thread th2 = new Thread(run,"bb");
//		th1.setPriority(10);
//		th2.setPriority(5);
		th1.start();
		th2.start();
	}

}

class RunableEight implements Runnable {
	int i=0;
	@Override
	public void run() {
		for(;i<20;i++){
			System.out.println(Thread.currentThread().getName()+"_"+i);
			Thread.yield();
		}
	}
}
