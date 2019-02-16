package com.beans.ko.java.kafka;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

import com.alibaba.fastjson.JSONObject;

public class SerializeTool {

	/**
	 * 构建JSON数据
	 * 
	 * @return
	 */
	static JSONObject generationJsonMessage() {
		JSONObject message = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("Item", "9SIAAW16DK5512");
		data.put("LanguageCode", "zh-cn");
		data.put("AvailableQty", "100");

		message.put("ItemNumber", "9SIAAW16DK5512");
		message.put("data", data);

		return message;
	}


	public static Schema getSchema(String schema) {
		return new Schema.Parser().parse(schema);
	}

	public static byte[] serialize(GenericRecord record, Schema schema) {
		byte[] result = null;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
			DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
			writer.write(record, encoder);
			encoder.flush();
			result = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static GenericRecord deserialize(byte[] value, Schema schema) {
		GenericRecord record = null;
		DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(
				schema);
		try {
			Decoder decoder = DecoderFactory.get().binaryDecoder(value, null);
			record = datumReader.read(null, decoder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
}
