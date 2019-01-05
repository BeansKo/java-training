package com.beans.ko.java.training.collection;

import java.util.Vector;

import com.beans.ko.java.training.entity.ShouJi;

/**
 * List:可以存储重复元素,可以存储null，有序，写入顺序
 * Vector:线程安全，但是性能较差
 * ArrayList:插入要跟新所以，所以插入较慢。比较适合顺序添加，随机访问
 * @author fl76
 *
 */
public class ListDemo {

	public static void main(String[] args) {
		DoVector();
	}

	private static void DoVector() {
		Vector<ShouJi> vector = new Vector<ShouJi>();
		vector.add(new ShouJi("1","aa"));
		vector.add(new ShouJi("2","bb"));
		vector.add(new ShouJi("3","cc"));
		vector.add(new ShouJi("4","dd"));
		vector.add(new ShouJi("4","dd"));
		vector.add(new ShouJi("c","dd"));
		vector.add(null);
		
		//list的遍历，使用索引的情况效率是最高的
		for(int i=0;i<vector.size();i++) {
			System.out.println(vector.get(i));
		}
	}

}
