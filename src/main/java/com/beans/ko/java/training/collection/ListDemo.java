package com.beans.ko.java.training.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import com.beans.ko.java.training.entity.Car;
import com.beans.ko.java.training.entity.ShouJi;

/**
 * List:可以存储重复元素,可以存储null，有序，写入顺序
 * Vector:底层基于数组，线程安全，但是性能较差
 * ArrayList:底层基于数组，插入删除要更新索引，所以插入删除效率不好，但是基于for的遍历效果非常好。比较适合顺序添加，随机（根据索引）访问。需要维护容量，尽量指定大小，减少扩容操作
 * ArrayList实现了List接口,它是以数组的方式来实现的,数组的特性是可以使用索引的方式来快速定位对象的位置,因此对于快速的随机取得对象的需求,使用ArrayList实现执行效率上会比较好
 * LinkedList：底层基于链表，对于新增和删除操作LinkedList比较有优势，因为ArrayList要移动数据
 * LinkedList是采用链表的方式来实现List接口的,它本身有自己特定的方法，如: addFirst(),addLast(),getFirst(),removeFirst()等. 由于是采用链表实现的,因此在进行insert和remove动作时在效率上要比ArrayList要好得多!适合用来实现Stack(堆栈)与Queue(队列),前者先进后出，后者是先进先出.
 * @author fl76
 *
 */
public class ListDemo {

	public static void main(String[] args) {
		DoArrayList();
		DoVector();
		DoLinkedList();
	}

	private static void DoArrayList(){
		List<Car> list = new ArrayList<Car>();
		list.add(new Car(1,"aa"));
		list.add(new Car(2,"bb"));
		list.add(new Car(3,"cc"));
		list.add(new Car(4,"dd"));
		//外部比较器
		Collections.sort(list,(c1,c2)->{return c2.getNum() - c1.getNum(); });
		int size = list.size();
		//ArrayList的遍历，采用索引的形式效率较高
		for(int i=0;i<size;i++){
			System.out.println(list.get(i));
		}
		list.stream().forEach((car) -> {System.out.println(car);});
	}
	
	private static void DoVector() {
		List<ShouJi> list = new Vector<ShouJi>();
		list.add(new ShouJi("1","aa"));
		list.add(new ShouJi("2","bb"));
		list.add(new ShouJi("3","cc"));
		list.add(new ShouJi("4","dd"));
		list.add(new ShouJi("4","dd"));
		list.add(new ShouJi("c","dd"));
		//使用collections进行排序，要求排序类要实现Comparable接口，如果没有实现接口可以使用外部比较器
		Collections.sort(list);
		Comparator<ShouJi> c = (t1,t2) -> {
			if (Integer.parseInt(t1.getId())>Integer.parseInt(t2.getId())){
				return 1;
			} else if (Integer.parseInt(t1.getId()) == Integer.parseInt(t2.getId())){
				return 0;
			} else {
				return -1;
			}
		};
		Collections.sort(list, c);
		
		list.add(null);
		
		//list的遍历，使用索引的情况效率是最高的
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}

	private static void DoLinkedList(){
		List<ShouJi> list = new LinkedList<ShouJi>();
		list.add(new ShouJi("1","aa"));
		list.add(new ShouJi("2","bb"));
		list.add(new ShouJi("3","cc"));
		list.add(new ShouJi("4","dd"));
		list.add(new ShouJi("4","dd"));
		list.add(new ShouJi("c","dd"));
		//正向遍历
		ListIterator<ShouJi> it = list.listIterator();
		System.out.println("正向遍历");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		//反向遍历
		System.out.println("反向遍历");
		while(it.hasPrevious()){
			System.out.println(it.previous());
		}
	}
}
