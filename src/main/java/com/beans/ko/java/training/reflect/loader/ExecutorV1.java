package com.beans.ko.java.training.reflect.loader;

public class ExecutorV1 extends AbstractExecutor{

	@Override
	public void execute(String name) {
		this.handle(new Handler(){

			@Override
			public void handle() {
				System.out.println("V1:" + name);
			}
			
		});
	}

}
