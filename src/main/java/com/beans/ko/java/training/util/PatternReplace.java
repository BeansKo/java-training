package com.beans.ko.java.training.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternReplace {

	/**
	 * 正则替换
	 */
	public static String exec(String keyPattern, Map<String, String> map) {
		Set<String> columns = new HashSet<String>();// 获取到所有需要匹配的字段名称
		Pattern pattern = Pattern.compile("\\{\\{.+?\\}\\}");
		Matcher matcher = pattern.matcher(keyPattern);
		while (matcher.find()) {
			columns.add(matcher.group().replace("{{", "").replace("}}", "").trim());
		}
		String redisKey = keyPattern;
		for (String cell : map.keySet()) {
			if (columns.contains(cell)) {
				String value = map.get(cell);
				if (value == null) {
					throw new RuntimeException("column " + cell + " value can not be null!");
				}
				redisKey = redisKey.replace("{{" + cell + "}}", value);
			}
		}
		return redisKey.trim();
	}

	/**
	 * 文件路径变量替换
	 * @param filename 文件路径
	 * @param properties 变量集合
	 * @return 替换后的真实文件路径
	 */
	public static String fileRegex(String filename, Map<String, String> properties){
		Pattern pattern = Pattern.compile("[^\\{\\}]+(?=\\})");
		Matcher matcher = pattern.matcher(filename);
		while(matcher.find()){
			String matchWord = matcher.group(0);
			if(matchWord.indexOf("|") != -1){

				String[] tmp = matchWord.split("\\|");
				String type = tmp[0];
				String patten = matchWord.replace(type + "|", "").trim();

				String date = null;
				switch (tmp[0]) {
				case "DATE":
					date = DateUtil.DateToString(new Date(), tmp[1]);
					if(date == null){
						throw new RuntimeException("can not format date by style:" + patten);
					}
					filename = filename.replace("${" + matchWord + "}", date);
					break;
				case "DATE-ISO8601":
					date = DateUtil.DateToStringISO8601(new Date(), tmp[1]);
					if(date == null){
						throw new RuntimeException("can not format date by style:" + patten);
					}
					filename = filename.replace("${" + matchWord + "}", date);
					break;
				}

			}
			String tmp = properties.get(matchWord);
			if(tmp != null){
				filename = filename.replace("${" + matchWord + "}", tmp);
			}
		}
		return filename;
	}

}
