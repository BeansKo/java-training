package com.beans.ko.java.training.file;

import java.io.File;

/**
 * 删除文件或目录
 * @author fl76
 *
 */
public class FileThree {

	public static void main(String[] args) {
		File file = new File("D:\\aa");
		delete(file);
		System.out.println("end");
	}
	
	public static void delete(File file){
		if(!file.delete()){
			File[] files = file.listFiles();
			for(File f:files){
				if(!f.delete()){
					delete(f);
				}else {
					System.out.println("ok");
				}
			}
		}
		if(file.delete()){
			System.out.println("删除自己成功！");
		}
	}
}
