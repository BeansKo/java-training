package com.beans.ko.java.training.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * 第十个
 * 转换流
 * 	1.InputStreamReader 的作用是将字节输入流转换成字符输入流
 * 	2.OutputStreamWriter 字节输出流转换成字符输出流
 *  构造方法要得到一个字节流的参数对象，返回的是字符流的对象
 * 转换流特点
 * 	1.高级流
 * 	2.实现了字节和字符的转换
 * 
 * 案例
 * 	1.实现键盘录入，一次录入一行
 * 	2.把内存中的数据输出到控制台
 * 	以上两个操作，不使用Scanner 和 System
 * System 字段学习
 * 	in 标准输入流
 *  out 标准输出流 打印流 字节输出流
 *  err 错误输出流 打印流 字节输出流
 *  
 *
 */
public class IOSreamConvert {
	  private static final String FileName = "file.txt";
	  private static final String CharsetName = "utf-8";

	public static void main(String[] args) throws IOException {
		
		//实现键盘录入，一次录入一行
		//使用转换流，封装一个字节输入流的对象，得到一个字符输入流的对象
		Reader reader = new InputStreamReader(System.in);
		//使用缓冲字符输入流，可以读一行
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
		//使用流的方式把line字符串输出到控制台。要指明输出的地方 System.out封装到控制台对象
		//使用转换流，通过字节输出流对象构建缓冲字符输出流
		Writer writer = new OutputStreamWriter(System.out);
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write(line);
		bw.flush();
	}
	
	public static void myTest(){
		try (Reader reader = new InputStreamReader(new FileInputStream("d:/aa.txt"),"UTF-16");
				BufferedReader fr = new BufferedReader(reader);
				Writer writer = new OutputStreamWriter(new FileOutputStream("d:/aa-16"),"UTF-16");
				BufferedWriter bw = new BufferedWriter(writer);){
			String line = fr.readLine();
			bw.write(line);
			bw.newLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testWrite() {
		try { 
			// 创建文件“file.txt”对应File对象
			File file = new File(FileName);
			// 创建FileOutputStream对应OutputStreamWriter：将字节流转换为字符流，即写入out1的数据会自动由字节转换为字符。
			OutputStreamWriter out1 = new OutputStreamWriter(
					new FileOutputStream(file), CharsetName);
			// 写入10个汉字
			out1.write("字节流转为字符流示例");
			// 向“文件中”写入"0123456789"+换行符
			out1.write("0123456789\n");
			out1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testRead() {
		try { 
			// 方法1：新建FileInputStream对象
			// 新建文件“file.txt”对应File对象
			File file = new File(FileName);
			InputStreamReader in1 = new InputStreamReader(new FileInputStream(file), CharsetName);
			// 测试read()，从中读取一个字符
			char c1 = (char) in1.read();
			System.out.println("c1=" + c1);
			// 测试skip(long byteCount)，跳过4个字符
			in1.skip(6);
			// 测试read(char[] cbuf, int off, int len)
			char[] buf = new char[10];
			in1.read(buf, 0, buf.length);
			System.out.println("buf=" + (new String(buf)));
			in1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
