package com.beans.ko.java.training.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 第五个
 * 文件复制 推荐使用字节流
 *	1.用字节输入流读取一个文件，读到内存
 *	2.用字节输出流把内存中的数据写入到文件
 *		循环读取文件内容
 *		读一个，写一个
 *	文件的移动如何实现？
 *		循环读取源文件，删除源文件
 *		删除文件确保连接关闭，否则删除失败
 *
 */
public class IOFileCopy {

	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream("d:/111.png");
			FileOutputStream fos = new FileOutputStream("d:/111copy.png");){
			int len = fis.read();
			int length=0;
			while(len != -1){
				fos.write(len);
				len = fis.read();
				length++;
			}
			
			System.out.println("fele size:"+length);
		}catch(IOException ex){
			
		}
		//删除源文件
		File file = new File("d:/111.png");
		if(file.exists()){
			file.delete();
		}
		System.out.println("结束了");
	}

}
