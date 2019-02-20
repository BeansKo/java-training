package com.beans.ko.java.training.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.beans.ko.java.training.entity.ShouJi;

/**
 * Set:不允许重复
 * HashSet：存储数据时无序的
 * LinkedHashSet:存储数据，会保持写入顺序
 * TreeSet:存储数据，指定比较规则，进行排序
 * @author fl76
 *
 */
public class SetDemo {

	public static void main(String[] args) {
		DoHashSet();
		System.out.println("--->");
		DoLinkedHashSet();
		System.out.println("--->");
		DoTreeSet();
	}

	private static void DoHashSet() {
		Set<ShouJi> set = new HashSet<ShouJi>();
		set.add(new ShouJi("1","aa"));
		set.add(new ShouJi("2","bb"));
		set.add(new ShouJi("3","cc"));
		set.add(new ShouJi("4","dd"));
		set.add(new ShouJi("4","dd"));
		set.add(new ShouJi("c","dd"));
		
		Iterator<ShouJi> it = set.iterator();
		while(it.hasNext()){
			ShouJi shouji = it.next();
			//删除元素
			if(shouji.getName().equals("cc")){
				it.remove();
			}
			System.out.println(shouji.toString());
		}
		
		List<String> list = set.stream().map(ShouJi::getName).collect(Collectors.toList());
		list.forEach((s) -> System.out.println(s));
		System.out.println(set);
	}
	
	private static void DoLinkedHashSet(){
		Set<ShouJi> set = new LinkedHashSet<ShouJi>();
		set.add(new ShouJi("1","aa"));
		set.add(new ShouJi("2","bb"));
		set.add(new ShouJi("3","cc"));
		set.add(new ShouJi("4","dd"));
		set.add(new ShouJi("4","dd"));
		set.add(new ShouJi("c","dd"));
		
		Iterator<ShouJi> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}
	
	private static void DoTreeSet(){
		SortedSet<ShouJi> set = new TreeSet<ShouJi>();
		set.add(new ShouJi("1","aa"));
		set.add(new ShouJi("2","bb"));
		set.add(new ShouJi("3","cc"));
		set.add(new ShouJi("4","dd"));
		set.add(new ShouJi("4","dd"));
		set.add(new ShouJi("c","dd"));
		
		Iterator<ShouJi> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}

}
