package com.beans.ko.java.training.generic;

/**
 * 泛型类
 */
public class GenericOne<T> {

	private T key;
	
	public GenericOne(T key){
		this.key = key;
	}
	
	public T getKey(){
		return key;
	}
	
	
	public static void main(String[] args) {
		GenericOne<Integer> genericOne1 = new GenericOne<Integer>(1);
		GenericOne<String> genericOne2 = new GenericOne<String>("aa");
		
		System.out.println("泛型测试,key is " + genericOne1.getKey());
		System.out.println("泛型测试,key is " + genericOne2.getKey());
	}

}
