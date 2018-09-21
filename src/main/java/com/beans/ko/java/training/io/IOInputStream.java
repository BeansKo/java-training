package com.beans.ko.java.training.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 字节流学习
 * 	FileInputStream(File file)
 * 		通过打开一个实际文件的连接来创建一个FileInputStream，该文件通过系统中的File对象file指定。
 * 	int read() 从输入流中读取一个数据字节
 * 		从文件中读取数据，返回值是int，返回的是据在unicode中的编码值
 * 		我们想让他展示原来的样子应该转型char类型
 * 	int read(byte[] b) 从输入流中将最多b.length个字节的数据读入一个byte数组中
 * 		读取数据，如果说数据足够，那么一次读取的数量为数组的长度
 * 		返回值就是读取的数量，如果数据不够，那么就会空余一些数组的空间
 * 	int read(byte[] b,int off,int len) 从输入流中最多len个字节的数据读入一个byte数组中
 * 		参数解析：b-读取数据写入到数组，off-数组的起始索引，len-最多读多少个字节
 * 	关于读取到数组，此时应注意，有时候是最多读取的个数，如果个数不足，不会出问题，返回的是实际读取的个数。
 * 	int available()
 * 		返回流中，尚未读取的字节个数
 * 	long skip(long n) 从输入流中跳过并丢弃n个字节的数据
 * 		从流中跳过n个数据不读，从之后开始读取
 */
public class IOInputStream {

	public static void main(String[] args) {
		try {
			File file = new File("d:/wordcount-input.txt");
			FileInputStream fis = new FileInputStream(file);
			int pos = fis.read();
			System.out.println("读取的内容为："+(char)pos);
			byte[] by = new byte[5];
			int len = fis.read(by, 0, 5);
			System.out.println(len);
			for(byte b:by){
				System.out.print((char)b);
			}
			int con = fis.available();
			System.out.println("\n剩余字节数:"+con);
			fis.skip(3);
			int result = fis.read();
			System.out.println((char)result);
		} catch (IOException e) {

		}
	}

}
