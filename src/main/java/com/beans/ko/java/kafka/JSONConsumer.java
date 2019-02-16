package com.beans.ko.java.kafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.alibaba.fastjson.JSONObject;

public class JSONConsumer {

	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		String topic="test_json";
		String group="test_group_json1";
		
		new Thread(()->{
			new JSONConsumer().consumerData(topic,group, bootstrapServer);
		}).start();
		
		System.out.println("Consumer finish.");
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
        props.put("value.deserializer", StringDeserializer.class);
        
        TreeMap<String, String> data = new TreeMap<>();
        try(Consumer<String,String> consumer = new KafkaConsumer<String,String>(props)) {
        	consumer.subscribe(Arrays.asList(topic.split(",")));
        	ConsumerRecords<String, String> records = consumer.poll(10000);
        	for (final ConsumerRecord<String,String> record : records){
        		System.out.println(record.key()+"============="+record.value());
        		data = serializableJson(record.value().toString());
        	}
        }
        
        data.forEach((k,v) -> {System.out.println(k+"============="+v);});
	}
	
	private static TreeMap<String, String> serializableJson(String jsonMsgString) {
		JSONObject jsonMsg = JSONObject.parseObject(jsonMsgString);
		TreeMap<String, String> decodedDataMap = new TreeMap<>();
		jsonMsg.getJSONObject("data").forEach((k,v) -> {
			decodedDataMap.put(k.toString(), v.toString());
		});
		
		return decodedDataMap;
	}
}
