package com.beans.ko.java.training.thread.pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public static void main(String[] args) {
//		Runnable run1 = new RunnablePool();
//		ExecutorService es = Executors.newSingleThreadExecutor();
//		es.submit(run1);
		
		ExecutorService espool = Executors.newSingleThreadExecutor();
		Runnable run2 = new RunnablePool();
		Thread th1 = new Thread(run2,"aa");
		Thread th2 = new Thread(run2,"bb");
		Thread th3 = new Thread(run2,"cc");
		espool.submit(th1);
		espool.submit(th2);
		System.out.println("开始结束线程池");
//		espool.shutdown();
		List<Runnable> ls = espool.shutdownNow();
		for(Runnable run: ls){
			System.out.println(run.toString());
		}
		System.out.println("线程池结束完成");
		if(espool.isShutdown()){
			espool.submit(th3);
		}
		System.out.println("end");
		
	}
}

class RunnablePool implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"_开始任务");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"_任务结束");
	}
	
}