package com.beans.ko.java.training.optional;

import java.util.Optional;

import com.beans.ko.java.training.entity.ShouJi;

public class OptionalDemo {

	public static void main(String[] args) {
		//创建Optional对象，如果参数为空直接抛出异常
		Optional<String> str = Optional.of("a");
		//获取Optional中的数据,如果不存在，则抛出异常
		System.out.println(str.get());
		//optional中是否存在数据
		System.out.println(str.isPresent());
		str = Optional.ofNullable(null);
		//获取Optional中的值，如果值不存在，返回参数指定的值
		System.out.println(str.orElse("b"));
		//获取Optional中的值，如果值不存在，返回lambda表达式的结果
		System.out.println(str.orElseGet(()->{return new ShouJi("1","aa").toString();}));
		//获取Optional中的值，如果值不存在，抛出指定的异常
		try{
			System.out.println(str.orElseThrow(() -> new RuntimeException("orElseThrow")));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
