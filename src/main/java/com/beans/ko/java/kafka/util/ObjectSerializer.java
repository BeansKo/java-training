package com.beans.ko.java.kafka.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

public class ObjectSerializer implements Serializer<Object>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Nothing to do
		
	}

	/**
	 * 对象转换为byte[]
	 */
	@Override
	public byte[] serialize(String topic, Object data) {
		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
				ObjectOutputStream outputStream = new ObjectOutputStream(byteArray);) {
			outputStream.writeObject(data);
			outputStream.flush();
			return byteArray.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public void close() {
		// Nothing to do
		
	}

}
