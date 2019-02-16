package com.beans.ko.java.kafka.util;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

public class ObjectDeserializer implements Deserializer<Object>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}

	@Override
	public Object deserialize(String topic, byte[] data) {
		return null;
	}

	@Override
	public void close() {
		
	}

}
