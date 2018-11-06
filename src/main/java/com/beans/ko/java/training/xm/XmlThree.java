package com.beans.ko.java.training.xm;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 写出XML
 * 写出文档对象
 * XML写出的步骤
 * 	1.得到输出流对象
 * 	2.
 * 如何得到文档对象？
 * 	1.读一个文档
 *		Document doc = sr.read(new File(""));
 *	2.自定义生成文档对象
 *		DocumentHelper.createDocument();
 */
public class XmlThree {

	public static void main(String[] args) throws Exception {
		SAXReader sr = new SAXReader();
		Document doc = sr.read(new File("E:\\github\\java-training\\persons.xml"));
		
//		Document doct = DocumentHelper.createDocument();
//		System.out.println(doct.asXML());
		FileOutputStream fos = new FileOutputStream("E:/aaa.xml");
		XMLWriter xw = new XMLWriter(fos);
		xw.write(doc);
		System.out.println("end");
		fos.close();
	}

}
