package com.beans.ko.java.training.thread;

/**
 * 生产者消费者模式
 * 	一个或一组线程进行生产
 * 	一个或一组线程进行消费
 * 		生产者负责生产产品，如果到达指定值就会等待，等待消费者消费
 * 		消费者负责消费产品，如果没有剩余的产品就会等待，等待生产者生产
 *	模拟手机店卖手机
 *		生产者负责生产手机	生产者类
 *		消费者负责消费手机	消费者类
 *	发现问题：
 *		产品生产到一半，就被消费了
 *		某个产品被反复消费
 *		有些产品没有及时消费
 *	问题总结：
 *		线程不同步
 *		生产者消费者没有沟通  设置b的状态，控制通信，每次生产和消费之后要更改状态。生产和消费仍然沟通不好，资源浪费，使用唤醒机制
 *		
 */
public class ThreadTen {
	static boolean b = false;
	public static void main(String[] args) {
		ShouJi shouJi = new ShouJi();
		Runnable sC = new ShengChan(shouJi);
		Runnable xF = new XiaoFei(shouJi);
		Thread th1 = new Thread(sC,"shengchan");
		Thread th2 = new Thread(xF,"xiaofei");
		th1.start();
		th2.start();
	}

}

/**
 * 生产者
 *
 */
class ShengChan implements Runnable{

	private ShouJi shouJi;
	
	public ShengChan(){}
	
	public ShengChan(ShouJi shouJi){
		this.shouJi = shouJi;
	}
	
	@Override
	public void run() {
		int i = 0;
		while (true) {
			if (!ThreadTen.b) {
				synchronized (shouJi) {
					if (i % 2 == 0) {
						shouJi.setId("hw" + i);
						shouJi.setName("华为");
					} else {
						shouJi.setId("pg" + i);
						shouJi.setName("苹果");
					}
				}
				ThreadTen.b=true;
				i++;
				
			}else{
				System.out.println("还没消费，拒绝生产");
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
}

/**
 * 消费者
 *
 */
class XiaoFei implements Runnable{
	private ShouJi shouJi;
	
	public XiaoFei(){}
	
	public XiaoFei(ShouJi shouJi){
		this.shouJi = shouJi;
	}

	@Override
	public void run() {
		while(true){
			if(ThreadTen.b){
				synchronized (shouJi) {
					System.out.println(shouJi.toString());
				}
				ThreadTen.b=false;
			}else{
				System.out.println("还没生产，拒绝消费");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ShouJi{
	private String id;
	private String name;
	
	public ShouJi() {}
	
	public ShouJi(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "手机：[ID,"+id+";NAME:"+name+"]";
	}
	
	
}