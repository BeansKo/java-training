package com.beans.ko.java.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.beans.ko.java.kafka.util.ObjectDeserializer;

public class ListConsumer {

	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		String topic="test_list";
		String group="test_group_list1";
		
		new Thread(()->{
			new ListConsumer().consumerData(topic,group, bootstrapServer);
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
        props.put("value.deserializer", ObjectDeserializer.class);
        
        try(Consumer<String,Object> consumer = new KafkaConsumer<String,Object>(props)) {
        	consumer.subscribe(Arrays.asList(topic.split(",")));
        	ConsumerRecords<String, Object> records = consumer.poll(10000);
        	for (final ConsumerRecord<String,Object> record : records){
        		@SuppressWarnings("unchecked")
				List<Map<String, Object>> list = (List<Map<String, Object>>) record.value();
        		for(int i=0;i<list.size();i++){
	        		for (Entry<String,Object> field : list.get(i).entrySet()) {
	            		System.out.println(field.getKey()+"="+field.getValue());
	        		}
	        		System.out.println("----------------------------------------------------");
        		}
        	}
        }
        
	}
}
