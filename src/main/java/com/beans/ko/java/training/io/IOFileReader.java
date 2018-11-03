package com.beans.ko.java.training.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 第七个
 * 字符输入流 FileReader
 * 	字节流去读写汉子，产生乱码
 * 	原因：汉子作为字符存在，被分割成两个字节读取，故造成了乱码
 * 	解决：使用字符流去读写汉子，和中文状态下的标点符号就没有问题
 * 
 * 字符输出流FileWriter
 * 使用字符流读取数据发现多一个字符
 * 	使用工具选择无BOM编码UTF-8就OK了
 * 
 * 注意：复制不要使用字符流，就会出问题，只有在纯文本的情况下才要使用字符流
 *
 */
public class IOFileReader {

	public static void main(String[] args) {
		try(FileReader fr = new FileReader("d:/aa.txt");
				FileWriter fw = new FileWriter("d:/aacopy.txt")){
			int len = fr.read();
			while(len > 0){
				System.out.println((char)len);
				fw.write(len);
				len = fr.read();
			}
			fw.flush();
		}catch (IOException ex){
			
		}

	}

}
