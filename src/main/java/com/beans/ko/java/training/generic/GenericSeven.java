package com.beans.ko.java.training.generic;

/**
 * 类中的泛型方法
 */
public class GenericSeven {
	class Fruit{

		@Override
		public String toString() {
			return "Fruit";
		}
	}
	class Apple extends Fruit{

		public Apple(){}
		@Override
		public String toString() {
			return "Apple";
		}
	}
	
    class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }
    
    class GenericTest<T>{
    	public void show_1(T t){
    		System.out.println(t.toString());
    	}
    	
    	//在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
    	public <T> void show_2(T t){
    		System.out.println(t.toString());
    	} 
    	
        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t){
            System.out.println(t.toString());
        }
    }
    
    public static void main(String[] args) {
    	Apple apple = new GenericSeven().new Apple();
    	Person person = new GenericSeven().new Person();
    	
    	GenericTest<Fruit> gt = new GenericSeven().new GenericTest<Fruit>();
    	//apple是Fruit的子类，所以这里可以
    	gt.show_1(apple);
    	//编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
    	//gt.show_1(person);
    	
    	//使用这两个方法都可以成功
    	gt.show_2(apple);
    	gt.show_2(person);
    	
    	//使用这两个方法都可以成功
    	gt.show_3(apple);
    	gt.show_3(person);
	}
}