package com.beans.ko.java.training.xm;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlSix {

	public static void main(String[] args) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputStream input = XmlSix.class.getClassLoader().getResourceAsStream("book.xml");
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList)xPath.compile("/class/student").evaluate(doc,XPathConstants.NODESET);
			for(int i=0;i<nodeList.getLength();i++){
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element)node;
					System.out.println("rollno:"+element.getAttribute("rollno"));
					System.out.println(node.getNodeValue());
					System.out.println(element.getElementsByTagName("firstname").item(0).getTextContent());
				}
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
