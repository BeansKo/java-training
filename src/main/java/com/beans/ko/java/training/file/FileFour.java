package com.beans.ko.java.training.file;

import java.io.File;
import java.util.regex.Pattern;

public class FileFour {

	public static void main(String[] args) {
		fileFilter();

	}
	
	public static void fileFilter(){
		String path = "d:/";
		Pattern filePattern = Pattern.compile("[p][a-zA-Z]+.txt");
		File[] files = new File(path).listFiles((dir,name) -> filePattern.matcher(name).matches());
		for(File file : files){
			System.out.println(file.getName());
		}
	}

}
