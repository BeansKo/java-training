package com.beans.ko.java.training.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模拟dir操作
 * @author fl76
 *
 */
public class FileTwo {

	public static void main(String[] args) {
		File file = new File("E:\\GitProject\\DataExport\\AZKABAN\\AZKABAN_V2\\datafeed_test");
		show(file);
	}
	
	public static void show(File file){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		if(file.exists()){
			if(file.isDirectory()){
				File[] files = file.listFiles();
				for(File f:files){
					long l = f.lastModified();
					String date = sdf.format(new Date(l));
					System.out.printf("%-20s", date);
					//以上得到最后修改时间
					String st = null;
					String str = null;
					if(f.isDirectory()){
						st="<DIR>";
						str="	";
					}
					if(f.isFile()){
						st="	";
						str=f.length()+"";
					}
					System.out.printf("%-10s", st);
					System.out.printf("%-10s", str);
					System.out.printf("%-10s", f.getName());
					System.out.println();
				}
			}else {
				System.out.println("不是目录");
			}
		} else {
			System.out.println("目录不存在");
		}
	}
}
