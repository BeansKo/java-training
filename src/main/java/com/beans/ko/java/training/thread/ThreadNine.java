package com.beans.ko.java.training.thread;

/**
 * join 等待该线程终止
 *  join方法的含义：例如A线程，B线程对象对象使用了join方法，那么A线程要等B线程结束才能执行。
 *  		           如果B线程不结束，那么A线程会一直等待
 *  void join(毫秒)  最多等待多少
 *  void join（毫秒，纳秒）
 *  如果给了参数，那么代表对多等待多长时间，如果到达指定时间，那么将不在等待
 */ 
public class ThreadNine {

	public static void main(String[] args) throws InterruptedException {
		RunnableNine run = new RunnableNine();
		Thread th1 = new Thread(run,"aa");
		th1.start();
		for (int i = 0; i < 100; i++) {
			System.out.println("main()_"+i);
			if(i==40){
				th1.join();
			}
		}
		//打印当前线程名，优先级，父线程名
		System.out.println(Thread.currentThread().toString());
	}

}

class RunnableNine implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("run()_"+i);
		}
		System.out.println(Thread.currentThread().toString());
	}
	
}