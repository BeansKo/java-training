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

}
