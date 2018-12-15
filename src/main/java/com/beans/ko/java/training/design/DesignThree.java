package com.beans.ko.java.training.design;

/**
 * 单例模式--双重检索式
 *	优点：解决了一开始创建对象的问题
 *		多个线程在一起执行，初期可能会创建多个对象，双重检索避免了这一个问题
 *		程序初期结束后，并不会影响后面的效率
 *	缺点：应为JVM内存机制，有微小的几率会创建多个对象，这种几率可以忽略
 */
public class DesignThree {

	public static void main(String[] args) {

	}

}
class Teacher{
	int i=20;
	static Teacher teacher = null;
	private Teacher(){System.out.println("new Student()");}
	public static Teacher getTeacher(){
		if(null == teacher){
			synchronized (Teacher.class) {
				if(null == teacher){
					teacher = new Teacher();
				}
			}
		}
		return teacher;
		
	}
	
	public void show(){
		System.out.println("show()");
	}
}