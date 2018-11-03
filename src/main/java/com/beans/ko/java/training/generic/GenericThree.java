package com.beans.ko.java.training.generic;

/**
 * 通配符？的使用
 */
public class GenericThree {
	public void showKeyValue1(GenericOne<Number> obj){
		System.out.println("泛型测试key value is " + obj.getKey());
	}
	
	//使用通配符
	public void showKeyValue(GenericOne<?> obj){
		System.out.println("泛型测试key value is " + obj.getKey());
	}
	
	public static void main(String[] args) {
		GenericOne<Integer> go1 = new GenericOne<Integer>(1);
		GenericOne<Number> go2 = new GenericOne<Number>(2);
		
		GenericThree gt = new GenericThree();
		//通过提示信息我们可以看到Generic<Integer>不能被看作为`Generic<Number>的子类。由此可以看出:同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
        //回到上面的例子，如何解决上面的问题？总不能为了定义一个新的方法来处理Generic<Integer>类型的类，这显然与java中的多台理念相违背。
		//因此我们需要一个在逻辑上可以表示同时是Generic<Integer>和Generic<Number>父类的引用类型。
		//由此类型通配符应运而生。
		//gt.showKeyValue1(go1);
		gt.showKeyValue1(go2);
		
		//类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。重要说三遍！此处’？’是类型实参，而不是类型形参 ！ 此处’？’是类型实参，而不是类型形参 ！再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。是一种真实的类型。
		//可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
		GenericOne<String> go3 = new GenericOne<String>("frank");
		gt.showKeyValue(go1);
		gt.showKeyValue(go2);
		gt.showKeyValue(go3);
		
	}
}
