package com.beans.ko.java.kafka.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

public class ObjectDeserializer implements Deserializer<Object>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		//nothing to do
	}

	/**
	 * 字节数组转为Object对象
	 */
	@Override
	public Object deserialize(String topic, byte[] data) {
		if (data == null) {
			return null;
		}
		
		ByteArrayInputStream bin = new ByteArrayInputStream(data);
		try {
			ObjectInputStream in = new ObjectInputStream(bin);
			return in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
		//nothing to do
	}
}
