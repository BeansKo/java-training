package com.beans.ko.java.training.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 第四个
 * 流的关闭--低级流 void close() 可以实现关闭流
 *  在finaly语句中关闭资源--合理的关闭流 try catch 新特性 
 *  格式：
 * 		try(里面存放创建硬性资源的对象){代码块}catch{异常处理} 
 * 	作用:
 * 		try中声明的资源连接可以自动关闭，不需要手动关闭 关闭流的意义
 * 		要自动关闭的对象，该类必须实现Closeable
 *  	括号里只能放自定关闭的，实现Closeable接口的子类对象
 * 流是硬性资源，必须手动关闭，否则连接会一直存在，会占用内存资源
 * 
 * @author fl76
 *
 */
public class IOStreamClose {

	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("d:/aaa.txt");
			fos = new FileOutputStream("d:/aaa.txt");
			System.out.println("对象创建完成！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//推荐使用
		try (FileInputStream fiss = new FileInputStream("d:/aaa.txt");
				FileOutputStream foss = new FileOutputStream("d:/aaa.txt");) {
			System.out.println("对象创建完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("end");
	}

}
