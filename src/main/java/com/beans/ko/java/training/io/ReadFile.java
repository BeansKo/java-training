package com.beans.ko.java.training.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 第八个
 *
 */
public class ReadFile {

	public static void main(String[] args) {
		readMethod2();
		System.out.println("end");
	}

	public static void readMethod2() {
		String fileName = "d:/ecpireditem-2.txt";
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			line = in.readLine();
			while (line != null) {
				line = in.readLine();
				if (null != line) {
					String[] str = line.split("\\|");
					if (str != null) {
						int num = str.length;
						if (num == 3) {
							writeMethod2(line);
						}
					}
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeMethod2(String str) {
		String fileName = "d:\\ecpireditem-22.txt";
		try {
			// 使用这个构造函数时，如果存在kuka.txt文件，
			// 则直接往kuka.txt中追加字符串
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
