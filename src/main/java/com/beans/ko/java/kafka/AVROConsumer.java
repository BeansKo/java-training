package com.beans.ko.java.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.beans.ko.java.kafka.util.AvroUtil;

public class AVROConsumer {

	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		String topic="test_avro";
		String group="test_group_avro1";
		
		new Thread(()->{
			new AVROConsumer().consumerData(topic,group, bootstrapServer);
		}).start();
	}
	
	private void consumerData(String topic, String group, String bootstrapServer) {
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServer);
        props.put("group.id", group);
        props.put("auto.commit.enable", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("enable.auto.commit", "true");
        props.put("rebalance.backoff.ms", "2000");
        props.put("rebalance.max.retries", "3");
        props.put("max.poll.records", "1000");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", ByteArrayDeserializer.class);
        
        try(Consumer<String,byte[]> consumer = new KafkaConsumer<String,byte[]>(props)) {
        	consumer.subscribe(Arrays.asList(topic.split(",")));
        	ConsumerRecords<String, byte[]> records = consumer.poll(10000);
        	for (final ConsumerRecord<String,byte[]> record : records){
        		GenericRecord genericRecord = AvroUtil.deserialize(record.value(), AvroUtil.getSchema(KafKaConstant.AVRO_SCHEMA));
        		System.out.println(record.key()+"============="+genericRecord.get("date"));
        		System.out.println(record.key()+"============="+genericRecord.get("message"));
        	}
        }
        
	}
}
