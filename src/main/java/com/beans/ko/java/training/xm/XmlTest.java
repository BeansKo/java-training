package com.beans.ko.java.training.xm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlTest {

	public static void main(String[] args)  {
		String emails = "11,22,33";
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><string>aa;bb;cc</string>";
		Document document =null;
		try {
			document = DocumentHelper.parseText(str);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		String encryptEmail = root.getStringValue();
		String[] encryptEmailArr = encryptEmail.split(";");
		String[] emailArr = emails.split(",");
		if(encryptEmailArr.length != emailArr.length) {
			System.out.println("error");
			System.exit(1);
		}
		for(int i=0;i<encryptEmailArr.length;i++) {
			System.out.println(emailArr[i]);
			System.out.println(encryptEmailArr[i]);
		}
		
		try (FileWriter fw = new FileWriter("",true);
				BufferedWriter bw = new BufferedWriter(fw);){
			bw.write("aa");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(root.getStringValue());

	}

}
