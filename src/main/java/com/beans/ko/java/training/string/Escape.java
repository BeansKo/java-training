package com.beans.ko.java.training.string;

import org.apache.commons.lang.StringEscapeUtils;

public class Escape {

	public static void main(String[] args) {
		String str = "hi di'n \n like \"tt\" a	b";
			
		String result = StringEscapeUtils.escapeJava(str);
		System.out.println(result);
		result = StringEscapeUtils.unescapeJava(result);
		System.out.println(result);
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
