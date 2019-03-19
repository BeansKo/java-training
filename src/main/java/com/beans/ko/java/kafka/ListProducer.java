package com.beans.ko.java.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.beans.ko.java.kafka.util.ObjectSerializer;

public class ListProducer {

	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		String topic = "test_list";
		new Thread(()->{
			ListProducer producer = new ListProducer();
			producer.produceJsonMessage(topic,bootstrapServer);
		}).start();
	}
	
	/**
	 * 生产Object格式的数据
	 * @param topicName
	 * @param bootstrapServer
	 */
	public void produceJsonMessage(String topicName,String bootstrapServer){
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServer);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", ObjectSerializer.class);
        
        try (Producer<String, Object> producer = new KafkaProducer<String, Object>(props);) {
            for (int i=1;i<=2000;i++) {
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                for(int a=0;a<2;a++){
                	Map<String,Object> map = new HashMap<String,Object>();
                	map.put("ItemNumber", "9SIV06W6CB596"+i+a);
                	map.put("CountryCode", "USA");
                	map.put("CompanyCode", 1003);
                	list.add(map);
                }
                producer.send(new ProducerRecord<String, Object>(topicName, System.currentTimeMillis()+"" ,list));
                if(i%1000 == 0){
                	System.out.println(i);
                }
            }
        }
        
//		try(Producer<String, Object> producer = new KafkaProducer<String, Object>(props)){
//			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("ItemNumber", "9SIAAW16DK5512");
//			map.put("qty", "20");
//			map.put("size", "30");
//			list.add(map);
//			ProducerRecord<String,Object> record = new ProducerRecord<String,Object>(topicName,list);
//			producer.send(record);
//		}
	}
}
