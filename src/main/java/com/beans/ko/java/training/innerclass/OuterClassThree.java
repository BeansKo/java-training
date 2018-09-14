package com.beans.ko.java.training.innerclass;

/**
 * 静态内部类
 *1.外部类可以直接访问内部静态类的成员吗？外部类可以直接访问内部类的静态成员,不受权限限制;外部类不可以直接访问内部类的非静态成员
 *2.在不是外部类的类中可以直接方位静态内部类的成员吗？可以直接访问静态内部类的成员，但是有权限限制
 *3.静态内部类依附外部类存在吗？不依附外部类存在
 *4.何以证明静态内部类不依附外部类存在呢？1.可以直接创建静态内部类的对象，而不需要依附外部类    2.在不是外部类的类中也支持以上操作
 *5.如果创建对象能不能访问静态内部类的非静态成员？可以，创建对象就可以
 *6.如何创建静态内部类的对象?new 静态内部类构造器就可以，由此可以，静态内部类不依附外部类对象存在，不管有没有外部类对象，静态内部类都可以加载构建对象
 *7.不是外部类的其他类如何构建静态内部类对象？new 静态内部类构造器就可以
 *总结：静态内部类不依附外部类对象存在；静态内部类作为外部类的静态成员存在，可以直接掉用静态部分；创建对象比较简单；支持继承；静态元素可以通过 外部类名.属性名||属性名 调用
 */
public class OuterClassThree {
	public static void main(String[] args) {
		new OuterClassThree().show();
		System.out.println(new InnerClassThree().iage);
	}
	
	public void show(){
		//静态成员可以通过类名.属性名访问
		System.out.println(InnerClassThree.inner);
		//非静态成员不能直接访问  System.out.println(InnerClassThree.iname);
		InnerClassThree.innerStatic();
		System.out.println(new InnerClassThree().iage);
	}
	static class InnerClassThree{
		public static String inner;
		public String iname;
		private int iage;
		
		
		
		public InnerClassThree() {
			super();
		}

		public InnerClassThree(String iname, int iage) {
			super();
			this.iname = iname;
			this.iage = iage;
		}

		@Override
		public String toString() {
			return "InnerClassThree [iname=" + iname + ", iage=" + iage + "]";
		}
		
		private static void innerStatic(){
			System.out.print("IS Static mothed");
		}
	}
}
