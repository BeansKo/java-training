package com.beans.ko.java.kafka.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	/**
	 * 构建JSON数据
	 * @return
	 */
	public static JSONObject generationJsonMessage() {
		JSONObject message = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("Item", "9SIAAW16DK5512");
		data.put("LanguageCode", "zh-cn");
		data.put("AvailableQty", "100");

		message.put("ItemNumber", "9SIAAW16DK5512");
		message.put("data", data);

		return message;
	}
}
