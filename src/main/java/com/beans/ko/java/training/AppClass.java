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
//		String[] arr = str.split("\\|");
//		System.out.println(arr.length);
//		
//		str = "{{PREFIX_NAME}}_datafeed_increment_im_shippingcharge";
//		String rs = str.replace("{{PREFIX_NAME}}", "pre");
//		System.out.println(rs);
		System.out.println(str.hashCode());
//		A a = new A();
//		Object obj = null;
//		System.out.println(a.hashCode());
//		System.out.println(a);
//		System.out.println(System.currentTimeMillis());
		
//		String url = "https://www.newegg.com/{{urlkeywords}}/p/{{parentItem}}?Item={{itemnumber}}";
//		System.out.println(url.contains("{{URLkeywords}}"));
//		String formatUrl = "https://www.newegg.com?Item={{itemnumber}}";
//		String itemNumber="9SI820147702";
//		String parentItem = "N82E16820147702";
//		String urlKeywords = "KEYWOD";
//		String url = formatProductUrl(itemNumber,parentItem,urlKeywords,formatUrl);
//		System.out.println(url);
		
		String str1= "   ";
		System.out.println(str1.trim().equals(""));
	}
	
	public static String formatProductUrl(String itemNumber, String parentItem, String urlKeywords, String formatUrl){
		//formatUrl = "https://www.newegg.com/{{urlkeywords}}/p/{{parentItem}}?Item={{itemnumber}}";
		if(null == itemNumber || itemNumber.equals("")) {
			return "";
		}
		
		if(null == formatUrl || formatUrl.equals("")) {
			return "";
		}

		formatUrl = formatUrl.toLowerCase();
		itemNumber = itemNumber.toUpperCase();
		
		if(!formatUrl.contains("{{urlkeywords}}")) {
			//如果没有配置urlkeywords，采用原来的URL方式
			return "AA";
		} else {
			parentItem = parentItem==null? "" : parentItem.toUpperCase();
			//如果urlkeywords不为空，则构建urlkeywords
			if(null != urlKeywords && !urlKeywords.equals("")) {
				formatUrl = formatUrl.replace("{{urlkeywords}}", urlKeywords);
			} else {
				formatUrl = formatUrl.replace("/{{urlkeywords}}", "");
			}
			//如果是9SI的Item则判断是否有Parent,否则直接构建p/itemnumber
			if(itemNumber.startsWith("9SI")) {
				if(!parentItem.equals("") && !parentItem.equalsIgnoreCase(itemNumber)) {
					formatUrl = formatUrl.replace("{{parentitem}}", parentItem).replace("{{itemnumber}}", itemNumber);
				} else {
					formatUrl = formatUrl.replace("{{parentitem}}?item={{itemnumber}}", itemNumber);
				}
			} else {
				formatUrl = formatUrl.replace("{{parentitem}}?item={{itemnumber}}", itemNumber);
			}
	
			return formatUrl;
		}
	} 

}

class A {
	
}
class B extends A {
	
}
