package com.beans.ko.java.training.xm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 操作文档
 *	doc.addElement("person")
 *		在当前节点增加一个元素，此元素是标记
 *		返回值是被增加的元素所对应的对象
 *		如果调用该方法的是文档对象，那么增加的是根节点
 *	element.addAttribute("id","001");
 *		增加属性，参数要求属性名和属性值
 *		属性重复，会覆盖
 *	student1.addText("文本");
 *		增加文本，多次使用该方法是在后面追加文本
 *	att.setValue("1");
 *		修改属性值
 *	student1.setText("张三丰");
 *		修改文本值
 *	student1.detach();
 *		删除
 *	students.remove(student2);
 *		删除
 */
public class XmlFour {

	public static void main(String[] args) {
		Document doc = DocumentHelper.createDocument();
		Element element = doc.addElement("person");
		element.addAttribute("id","001");
		element.addAttribute("tag","abc");
		Element students = element.addElement("students");
		Element student1 = students.addElement("student");
		Element student2 = students.addElement("student");
		student1.addText("文本");
		student1.addText("文本");
		student2.addText("文本2");
		//修改
		Attribute att = element.attribute("id");
		att.setValue("1");
		student1.setText("张三丰");
		
		
		//删除
		student1.detach();
		students.remove(student2);
		
		element.remove(att);
		out(doc);
		System.out.println("end");
	}

	public static void out(Document doc){
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/test.xml");
			//给予漂亮格式
			OutputFormat of = OutputFormat.createPrettyPrint();
			XMLWriter xw = new XMLWriter(fos,of);
			xw.write(doc);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
