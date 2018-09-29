package com.beans.ko.java.training.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 数据流
 *	1.DataInputStream
 *	2.DataOutputStream
 *	数据流都是高级流，必须要字节流构建对象
 *	问题：
 *		写入到文件中，并的不认识是什么了？认不出来很正常过，java认识就可以
 *		虽然基本数据流写入到文件中，我们不认识，但是可以输入流来读取，读完之后可以直接使用，因为数据本身就是java的基本数据类型
 *	如何循环读取文件中所有的基本数据类型？
 *		使用循环没办法解决，循环是解决大量重复的工作，此时已经不是重复的了，方法的使用有9个
 *	在读取数据的时候必须保证读写的顺序，否则数据会不真实
 */
public class IODataStream {

	public static void main(String[] args) {
		try (FileOutputStream fos = new FileOutputStream("d:/datastream.txt");
				DataOutputStream dos = new DataOutputStream(fos);//高级流
				FileInputStream fis = new FileInputStream("d:/datastream.txt");
				DataInputStream dis = new DataInputStream(fis);) {
			//写操作
			dos.writeBoolean(false);
			dos.writeBoolean(true);
			dos.writeDouble(3.3);
			dos.writeUTF("大家好");
			dos.flush();
			
			//读操作
			boolean b1 = dis.readBoolean();
			boolean b2 = dis.readBoolean();
			double d1 = dis.readDouble();
			System.out.println(dis.readUTF());
			if(b2){
				System.out.println(b2);
			}
			

		} catch (IOException ex) {

		}
	}

}
