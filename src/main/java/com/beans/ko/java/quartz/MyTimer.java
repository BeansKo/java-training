package com.beans.ko.java.quartz;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	public static void main(String[] args) throws Exception {
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				System.out.println("hello");
			}
			
		};
	
		new Thread(()->{
			
		}).start();
		
		Timer timer = new Timer();
		timer.schedule(task, 0,5000);
		Thread.sleep(20000);
		timer.cancel();
	}
}
