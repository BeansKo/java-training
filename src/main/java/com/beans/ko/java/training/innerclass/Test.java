package com.beans.ko.java.training.innerclass;

import com.beans.ko.java.training.innerclass.OuterClassThree.InnerClassThree;

/**
 * 匿名内部类
 *什么是匿名内部类？类没有名字
 *匿名内部类存在于哪里？方法可以，语句块可以，不能以成员内部类的方式存在；可以以局部内部类的方式存在；匿名内部类有局部内部类的特性，也满足局部内部类的约束
 *匿名内部类如何构建？new 父类构造器(){};{}里是类题，整个表达式是得到匿名内部类的对象
 *匿名内部类的对象如何存储？使用父类型即可，因为本类没有名字，无法使用
 *匿名内部类有没有构造器？构造器的格式是：权限修饰符 类名（）{}，因为没有类名，所以没有构造器
 *匿名内部类可以有属性吗？正常使用；可以直接赋值，或者使用语句块赋值
 *匿名内部类可以有静态成员吗？不可以
 *
 *匿名内部类如何使用？
 *	我们在写内部类的时候，可以接受一个返回值，该返回值就是匿名内部类的对象，我们使用这个对象就可以。
 *	也可以直接在大括号后面调用，因为前者会返回本类对象，这样就属于不接受返回值直接进行下一步操作。
 *匿名内部类的作用范围？和局部内部类一致
 *匿名内部类的应用场景？
 *	在我们需要使用接口或抽象类方法时，因为不能实例化对象，不能构建对象
 *	我们也不想专门写一个类去实现继承抽象类或接口，那么可以写成匿名类
 *	我们以后学习的外部比较器，使用匿名内部类就很合适
 *
 *内部类种类较多，整体使用较少
 *就整体而言，匿名内部类和静态内部类使用较多
 */
public class Test {

	public static void main(String[] args) {
		//静态内部类测试
		System.out.println(InnerClassThree.inner);
		System.out.println(new InnerClassThree().iname);
		
		System.out.println("开始匿名内部类");
		//匿名内部类
		PersonClass person = new PersonClass(){
			public String name="周星期";
			{
				name="周润发";
			}
			@Override
			public void add() {
				System.out.println(this.name);
				System.out.println("匿名内部类方法");
				
			}
			
			public void getName(){
				System.out.println(name);
			}
			
		};
		person.add();
		//匿名内部类
		new PersonClass(){
			public String name="周星期";
			{
				name="周润发";
			}
			@Override
			public void add() {
				System.out.println(this.name);
				System.out.println("匿名内部类方法");
				
			}
			
			public void getName(){
				System.out.println(name);
			}
			
		}.getName();
	}

}

abstract class PersonClass{
	public void show(){
		System.out.println("Person show");
	}
	
	public abstract void add();
}
