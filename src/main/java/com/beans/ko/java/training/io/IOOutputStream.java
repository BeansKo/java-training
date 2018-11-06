package com.beans.ko.java.training.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 第三个
 *  字节输出流
 *  注意：如果指定的输出文件没有存在，那么会创建一个指定的文件
 *  void write(int b) 将指定字节写入文件输出流
 *  	1.参数要求是int,其实要的是字符型数据
 *  	2.目前是覆盖输出，覆盖源文件
 *  void write(byte[] b) 将指定byte数组写入此输出流
 *  void write(byte[] b,int off,int len) 将指定的byte数组从偏移量off开始的len个字节写入此文件输出流
 *  	参数解析：b:数据来源数组，off:起始索引，len:数量
 *  文件有时候不能删除，如何解决？关闭对文件的访问，关闭连接
 *  	我们创建对象的时候就打开了连接，使用close方法关闭连接
 *  void flush() 刷新此输出流并强制写出所有的缓冲流，立即写入
 *  
 *  输出到源文件，而不是覆盖文件？
 *  	构建对象时，选择合适的构造器
 *  	FileOutputStream（String name,boolean append）
 *  	文件不存在，会创建
 */
public class IOOutputStream {

	public static void main(String[] args) {
		File file = new File("d:/aa.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(97);
			fos.write('b');
			fos.write('c');
			for(int i='A';i<'z';i++){
				fos.write(i);
			}
			
			byte[] b = new byte[20];
			for(int i=0;i<b.length;i++){
				b[i]=(byte)(i+61);
			}
			fos.write(b);
			fos.write(b,3,4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			FileOutputStream fos = new FileOutputStream("d:/bb.txt",true);
			fos.write('a');
			fos.write('b');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("end");
	}

}
