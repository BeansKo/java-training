package com.beans.ko.java.kafka;

public class KafKaConstant {
	public static final String AVRO_SCHEMA = "{"
			+ "\"namespace\": \"com.beans.ecbd.avro\","
			+ "\"type\": \"record\"," + "\"name\": \"Message\","
			+ "\"fields\": [" + "{" + "\"name\": \"date\","
			+ "\"type\": \"string\"" + "}," + "{" + "\"name\": \"message\","
			+ "\"type\": \"string\"" + "}" + "]" + "}";
}
