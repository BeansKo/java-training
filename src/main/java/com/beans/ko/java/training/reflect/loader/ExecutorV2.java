package com.beans.ko.java.training.reflect.loader;

public class ExecutorV2 extends AbstractExecutor {

	@Override
	public void execute(String name) {
		this.handle(new Handler(){

			@Override
			public void handle() {
				System.out.println("V2:" + name);
			}
			
		});
	}

}
