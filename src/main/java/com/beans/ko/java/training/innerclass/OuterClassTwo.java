package com.beans.ko.java.training.innerclass;

/**
 * 局部内部类
 * 1.外部类不能直接访问内部类成员
 * 2.内部类可以直接访问外部类成员
 * 3.外部类如何访问内部类，创建对象
 * 4.对于局部内部类如何构建对象？直接new对象就可以，注意点，new对象的语句必须在局部内部类之后。
 * 5.局部内部类有作用范围吗？有的，局部内部类的作用范围从声明处开始，一直到该方法或语句块的结束。超出了作用范围那么局部内部类就是不可达的，可以按照局部变量来理解。
 * 6.局部内部类可以有静态成员吗？本身不可以被static修饰，不可以 局部内部类中不可以包含静态成员，他的机制和静态机制冲突
 */
public class OuterClassTwo {

	public static void main(String[] args) {
		int i=3;
		class Student extends Person{//局部内部类
//			public static boolean isUse;
			@Override
			public void add() {
				System.out.println("i="+i);
				System.out.println("Student");
			}
			
		}
		
		class Test{//局部内部类
			private void show(){
				System.out.println("Test");
			}
		}
		Student stu = new Student();
		stu.add();
		Test test = new Test();
		test.show();
	}
}

abstract class Person{
	public abstract void add();
}
