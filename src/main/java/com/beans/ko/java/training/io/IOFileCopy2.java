package com.beans.ko.java.training.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 第六个
 * 文件复制加速
 *	影响复制的因素,增大一次操作文件的字节数
 */
public class IOFileCopy2 {

	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream("E:/UploadFiles/141/US_ink.txt");
			FileOutputStream fos = new FileOutputStream("E:/UploadFiles/141/US_ink_copy.txt");){
			//开始时间
			long action = System.currentTimeMillis();
			byte[] b = new byte[1024*64];//64kb
			int len = fis.read(b);//返回读到的字节数
			int con = len;
			while(len >0 ){//判断数组是否有正确数据
				fos.write(b,0,len);//把数据写到文件，为了便面多写数据使用该方法
				len = fis.read(b);
				con +=len;
			}
			fos.flush();
			System.out.println("文件大小"+con+"kb");
			//结束时间
			long now = System.currentTimeMillis();
			System.out.println("执行时间"+(now-action)+"ms");
			
		}catch(IOException ex){
			
		}
		System.out.println("end");
	}
}