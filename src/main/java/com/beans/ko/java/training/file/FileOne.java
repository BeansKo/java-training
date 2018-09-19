package com.beans.ko.java.training.file;

import java.io.File;
import java.io.IOException;

/**
 * File学习
 * 文件和目录名的抽象表现形式
 * boolean createNewFile() 仅不存在该File类对象对应的文件，创建一个
 * boolean delete() 删除该File类对应的文件
 * String getName 返回文件名 
 * String getParent() 返回该File类文件的上层目录，没有返回null
 * File getParentFile() 返回该File类对应的父类对象
 * String getPath() 返回路径
 * boolean isDirectory() 是否标准目录
 * boolean isFile() 是否标准文件
 * int fileLength()
 * 创建目录
 * 	boolean mkdir
 *  boolean mkdirs() 如果父目录不存在，创建父目录
 */
public class FileOne {

	public static void main(String[] args) throws IOException {
		String path = "d:/aa/bb/cc/test.jar";
		File file = new File(path);
		file.mkdirs();
		System.out.println(file.exists());
		file.createNewFile();
		file.isDirectory();
		System.out.println(file.getName());
		System.out.println(file.getParent());
		file.delete();
		
		System.out.println(File.pathSeparator);
	}

}
