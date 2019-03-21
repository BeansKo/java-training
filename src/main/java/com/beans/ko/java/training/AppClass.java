package com.beans.ko.java.training;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AppClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Map<String,Object> map = new HashMap<String,Object>();
//		Map<String,String> vMmap = new HashMap<String,String>();
//		for(int i=0;i<10;i++){
//			vMmap.put(i+"", "a"+i);
//		}
//		map.put("key", vMmap);
//		
//		for(Entry<String,Object> entry : map.entrySet()){
//			Object value = entry.getValue();
//			System.out.println(value instanceof Map);
//		}
		
		String str="2S7-000C-067T5|USA|1003";
		String[] arr = str.split("\\|");
		System.out.println(arr.length);
		
		str = "{{PREFIX_NAME}}_datafeed_increment_im_shippingcharge";
		String rs = str.replace("{{PREFIX_NAME}}", "pre");
		System.out.println(rs);
	}

}

class A {
	
}
class B extends A {
	
}
