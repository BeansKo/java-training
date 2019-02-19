package com.beans.ko.java.training.resource;

/**
 * Class.getResource(String path)
 * path不以’/'开头时，默认是从此类所在的包下取资源；
 * path  以’/'开头时，则是从ClassPath根下获取；
 * Class.getResource和Class.getResourceAsStream在使用时，路径选择上是一样的。
 * Class.getClassLoader（）.getResource(String path)
 * path不能以’/'开头时；
 * path是从ClassPath根下获取；
 * Class.getClassLoader（）.getResource和Class.getClassLoader（）.getResourceAsStream在使用时，路径选择上也是一样的。
 */
public class Demo1 {

	public static void main(String[] args) {
		Demo1 demo1 = new Demo1();
		demo1.getResource();
		demo1.getClassLoaderResource();

	}
	
	private void getClassLoaderResource(){
		Demo1 t = new Demo1();
        System.out.println(t.getClass());
        System.out.println(t.getClass().getClassLoader());
        System.out.println(t.getClass().getClassLoader().getResource(""));
        System.out.println(t.getClass().getClassLoader().getResource("/"));//null
	}
	
	private void getResource(){
        // 当前类(class)所在的包目录
        System.out.println(Demo1.class.getResource(""));
        // class path根目录
        System.out.println(Demo1.class.getResource("/"));
        
        // TestMain.class在<bin>/testpackage包中
        // 2.properties  在<bin>/testpackage包中
        System.out.println(Demo1.class.getResource("2.properties"));
        
        // TestMain.class在<bin>/testpackage包中
        // 3.properties  在<bin>/testpackage.subpackage包中
        System.out.println(Demo1.class.getResource("subpackage/3.properties"));
        
        // TestMain.class在<bin>/testpackage包中
        // 1.properties  在bin目录（class根目录）
        System.out.println(Demo1.class.getResource("/1.properties"));
	}

}
