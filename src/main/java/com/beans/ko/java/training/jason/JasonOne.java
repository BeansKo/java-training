package com.beans.ko.java.training.jason;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JasonOne {

	public static void main(String[] args) {
		new JasonOne().readJson();
		new JasonOne().generateJson();
	}
	
	private void readJson(){
		String json1="{'id':1,'name':'JAVAEE-1703','stus':[{'id':101,'name':'刘一','age':16}]}";
		String json2="['北京','天津','杭州']";
		Grade grade = JSON.parseObject(json1,Grade.class);
		System.out.println(grade);
		
		List<String> list = JSON.parseArray(json2, String.class);
		System.out.println(list);
	}
	
	private void generateJson(){
		ArrayList<Student> list = new ArrayList<>();
		for(int i=0;i<3;i++){
			list.add(new Student(101+i,"aa",20+i));
		}
		Grade grade = new Grade(100001,"bb",list);
		String json = JSON.toJSONString(grade);
		System.out.println(json);
	}
}
