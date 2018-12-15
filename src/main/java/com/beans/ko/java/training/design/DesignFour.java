package com.beans.ko.java.training.design;

/**
 * 单例模式--静态内部类
 *
 */
public class DesignFour {

	public static void main(String[] args) {
		Animal.InnerAnimal.getAnimal().show();
		Animal.InnerAnimal.getAnimal().show();
	}
}

class Animal{
	static class InnerAnimal{
		int w = 20;
		static Animal animal = new Animal();
		public static Animal getAnimal(){
			return animal;
		}
	}
	
	String name;
	private Animal(){System.out.println("new Animal()");}
	public void show(){
		System.out.println("show()");
	}
}