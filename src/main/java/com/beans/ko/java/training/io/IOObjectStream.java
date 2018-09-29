package com.beans.ko.java.training.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象流   高级流
 *	ObjectInputStream
 *  ObjectOutputStream
 */
public class IOObjectStream {

	public static void main(String[] args) {

		try(FileOutputStream fos = new FileOutputStream("d:/objectStream.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				FileInputStream fis = new FileInputStream("d:/objectstream.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)){
			
			
		}catch(IOException ex){
			
		}
	}

}
