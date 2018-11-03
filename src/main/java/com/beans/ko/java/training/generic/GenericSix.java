package com.beans.ko.java.training.generic;

/**
 * 泛型方法的基本用法
 */
public class GenericSix {
	public class Generic<T>{
		private T key;
		
		public Generic(T key){
			this.key = key;
		}
		
		/**
		 * 我想说的其实是这个，虽然在方法中使用了泛型，但是这并不是一个泛型方法。
         * 这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
         * 所以在这个方法中才可以继续使用 T 这个泛型。
		 */
		public T getKey(){
			return key;
		}
	}
	
    /** 
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * 泛型的数量也可以为任意多个 
     *    如：public <T,K> K showKeyName(Generic<T> container){
     *        ...
     *        }
     */
	public <T> T showKeyName(Generic<T> container){
		T test = container.getKey();
		//当然这个例子举的不太合适，只是为了说明泛型方法的特性。
		return test;
	}
	
	//这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
	public void showKeyValue(Generic<Number> obj){
		System.out.println(obj.getKey());
	}
	
    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //同时这也印证了泛型通配符章节所描述的，?是一种类型实参，可以看做为Number等所有类的父类
	public void showKeyValue1(Generic<?> obj){
		System.out.println(obj.getKey());
	}
}
