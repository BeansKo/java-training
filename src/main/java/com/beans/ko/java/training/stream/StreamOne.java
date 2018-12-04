package com.beans.ko.java.training.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamOne {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("abc","","bc","abcd","","jkl");
		//filter 方法用于通过设置的条件过滤出元素
		List<String> filtered = list.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		
		//forEach
		filtered.forEach(System.out::println);
		
		//map, 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取对应的平方数
		List<Integer> sequaresList = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
		sequaresList.forEach(System.out::println);
		
		//limit sorted
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);
	}

}
