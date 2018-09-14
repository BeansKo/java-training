package com.beans.ko.java.training.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Callable学习
 * 	如何使用Callable创建线程，并开启
 * 		1.实现Callable接口,重写覆盖call方法，call就是线程体，类似run方法
 * 		2.使用FutureTask进行包装，得到Runnable对象
 * 		3.使用Ruunable对象构建Thread类对象
 * 		4.调用start()方法进行开启
 * FutureTask
 * 	FutureTask 的get方法是一个阻塞方法,方法重载，设置最多等待时间
 *
 */
public class ThreadFourTeen {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new CallableFourTeen();
		FutureTask<Integer> result = new FutureTask<Integer>(callable);
		new Thread(result).start();
		Integer r=0;
		try {
			r = result.get(5,TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			System.out.println("异常");
		}
		System.out.println("result:"+r);
		System.out.println("main...");
	}

}

class CallableFourTeen implements Callable<Integer>{

	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 101; i++) {
			sum ++;
			System.out.println(i);
			Thread.sleep(1000);
		}
		
		return sum;
	}
	
}
