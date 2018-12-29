package com.beans.ko.java.training.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * hashCode方法,返回一个根据自身条件计算出来的内存地址，以整数的形式返回
 *	第一种情况，我们不重写hashCode和equals方法
 *	p1,p2,p3,p4因为地址不同所以hashCode不同，equals比，引用类型比较的是地址，他们都不相等
 *	重写hashCode方法和equals方法（自动生成）
 *	p1,p2,p3,p4hashCode相同，equals比，他们相等
 *
 *　　1）对于==，如果作用于基本数据类型的变量，则直接比较其存储的 “值”是否相等；
　　　　如果作用于引用类型的变量，则比较的是所指向的对象的地址
　　       2）对于equals方法，注意：equals方法不能作用于基本数据类型的变量
　　　　如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址；
　　　　诸如String、Date等类对equals方法进行了重写的话，比较的是所指向的对象的内容。
 * 重写equals方法，为什么要重写hashCode方法？
 *  Set不能存在重复元素，java就采用了hash表，利用哈希算法（也叫散列算法），就是将对象数据根据该对象的特征使用特定的算法将其定义到一个地址上，
 *  那么在后面定义进来的数据只要看对应的hashCode地址上是否有值，那么就用equals比较，如果没有则直接插入，只要就大大减少了equals的使用次数，执行效率就大大提高了。
 *  hashCode不行等，元素肯定不相等，hashCode相等，元素不一定相等
 */
public class HashCodeExample {

	public static void main(String[] args) {
		Set<Person> set = new HashSet<Person>();
		
		Person p1 = new Person("1","a");
		Person p2 = new Person("1","a");
		Person p3 = new Person("1","a");
		Person p4 = new Person("1","a");
		set.add(p1);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
		System.out.println(p4.hashCode());
		System.out.println(p1.equals(p2));
		System.out.println(p1.toString());
	}

}

class Person extends Object{
	private String id;
	private String name;
	
	public Person(String id, String name) {
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
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
