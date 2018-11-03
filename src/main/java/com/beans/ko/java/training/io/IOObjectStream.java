package com.beans.ko.java.training.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 第十二个
 * 对象流   高级流
 *	ObjectInputStream
 *  ObjectOutputStream
 *  1.写入到磁盘，乱码
 *  	没有影响，java虚拟机认识
 *  2.如何循环读取
 *  	1.使用异常处理机制
 *  	2.存入一个无意义的对象作为结尾，例如null
 */
public class IOObjectStream {

	public static void main(String[] args) {

		try(FileOutputStream fos = new FileOutputStream("d:/objectStream.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				FileInputStream fis = new FileInputStream("d:/objectstream.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)){
			oos.writeObject("nihao");
			oos.writeObject("test");
			oos.writeObject("cc");
			oos.writeObject(null);//第二种读取方式：读取时候判断就可以了
			oos.flush();
			
//			Object obj = ois.readObject();
//			System.out.println(obj);
//			System.out.println(obj.toString().length());
			
//			List<Object> list = new ArrayList<Object>();
//			try{
//				while(true){
//					list.add(ois.readObject());
//				}
//				
//			}catch(Exception ex){
//				System.out.println("文件读取完毕！");
//			}
//			for(Object o:list){
//				System.out.println(o);
//			}
			
			Object readObj = ois.readObject();
			while(readObj != null){
				System.out.println(readObj);
				readObj = ois.readObject();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("gg");
	}

}
