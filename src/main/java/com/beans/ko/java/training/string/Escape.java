package com.beans.ko.java.training.string;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class Escape {

	public static void main(String[] args) {
//		String str = "hi di'n \n like \"tt\" a	b";
//			
//		String result = StringEscapeUtils.escapeJava(str);
//		System.out.println(result);
//		result = StringEscapeUtils.unescapeJava(result);
//		System.out.println(result);
		
		Set<String> keySet = new HashSet<String>();
		String str = "schema:struct<itemnumber:string,itemgroupid:string,upccode:string,sellerid:string,ic_02:string,dt:string>";
		String subStr = str.substring(str.indexOf("<")+1,str.length()-1);
		String[] strArr = subStr.split(",");
		for(String s : strArr){
			keySet.add(s.split(":")[0]);
		}
		keySet.remove("dt");
		for(String key : keySet){
			System.out.println(key);
		}
		
				
		System.out.println(str.substring(str.indexOf("<")+1,str.length()-1));
		
		String strr="a,b,c,";
		String[] arry = strr.split(",");
		System.out.println(arry.length);
	}
	
	public static String GetSubString(String oldStr, int length) {
		try {
			if (oldStr == null) {
				return "";
			}
			oldStr = oldStr.trim();
			if (oldStr.length() > length) {
				String spt = oldStr.substring(length, length + 1);
				oldStr = oldStr.substring(0, length);
				if (!spt.equals(" ") && oldStr.lastIndexOf(" ") > 0) {
					oldStr = oldStr.substring(0, oldStr.lastIndexOf(" ") + 1);
				}
			}
			return oldStr.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oldStr.trim();
	}

}
