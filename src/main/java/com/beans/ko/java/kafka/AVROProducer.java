package com.beans.ko.java.kafka;

import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.beans.ko.java.kafka.util.JsonUtil;
import com.beans.ko.java.kafka.util.AvroUtil;

public class AVROProducer {
	
	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		String topicName = "test_avro";
		
		Runnable r = new Runnable(){
			@Override
			public void run() {
				AVROProducer avroProducer = new AVROProducer();
				avroProducer.produceAVROMessage(topicName, bootstrapServer);
			}};
			
		new Thread(r).start();
	}
	
	/**
	 * 生产AVRO格式的数据
	 * @param topicName
	 * @param bootstrapServer
	 */
	public void produceAVROMessage(String topicName,String bootstrapServer){
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServer);
	    props.put("acks", "all");
	    props.put("retries", 0);
	    props.put("batch.size", 16384);
	    props.put("linger.ms", 1);
	    props.put("buffer.memory", 33554432);
	    props.put("key.serializer", StringSerializer.class);
	    props.put("value.serializer", ByteArraySerializer.class);
	    
		try(Producer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props)){
			GenericRecord genericRecord = new GenericRecordBuilder(AvroUtil.getSchema(KafKaConstant.AVRO_SCHEMA))
				.set("date", System.currentTimeMillis()+"").set("message", JsonUtil.generationJsonMessage().toJSONString()).build();
			ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<String, byte[]>(topicName, 
					AvroUtil.serialize(genericRecord, AvroUtil.getSchema(KafKaConstant.AVRO_SCHEMA)));
			producer.send(producerRecord);
		}
	}
}
