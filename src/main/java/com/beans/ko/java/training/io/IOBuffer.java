package com.beans.ko.java.training.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 第九个
 * 缓冲流学习
 * 字节缓冲流
 * BufferedInputStream
 * BufferedOutputStream
 * 
 * 特性：
 * 	内部会有一个自制的缓冲区，为了解决每次一个一个的读取和写入的问题，提高了效率
 * 	缓冲流是高级流，是处理流，高级流必须套接一个低级流才能构建对象
 * 
 * 
 * BufferedReader
 * BufferedWriter
 * 	void newLine() 写入一个行分隔符
 *  void write(String s)
 *
 */
public class IOBuffer {

	public static void main(String[] args) {
		streamBuffer();
		fielBuffer();
		System.out.println("end");
	}

	private static void streamBuffer(){
		try(FileInputStream fis = new FileInputStream("E:/UploadFiles/141/US_ink.txt");
				BufferedInputStream bis = new BufferedInputStream(fis);
//				BufferedInputStream bis = new BufferedInputStream(fis,2048);
				FileOutputStream fos = new FileOutputStream("d:/aacopy.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				BufferedOutputStream bos = new BufferedOutputStream(fos,2048);
				){
			long action = System.currentTimeMillis();
			byte[] b = new byte[1024];//缓冲流和数组结合使用，复制文件效率最高
			int len = bis.read(b);
			while(len != -1){
				bos.write(b,0,len);
				len = bis.read(b);
			}
			bos.flush();
			long now = System.currentTimeMillis();
			System.out.println("执行时间"+(now-action)+"ms");
		}catch(IOException ex){
			
		}
	}
	
	/*
	 * 字符缓冲流
	 */
	private static void fielBuffer(){
		try(FileReader fr = new FileReader("d:/aa.txt");
				BufferedReader br = new BufferedReader(fr);
				FileWriter fw = new FileWriter("d:/aacopy.txt",true);
				BufferedWriter bw = new BufferedWriter(fw);){
			//一次读取一行数据
			String line = br.readLine();
			while(null != line){
				System.out.println(line);
				bw.write(line);
				bw.newLine();
				line = br.readLine();
			}
		}catch(IOException ex){}
	}
}
