package com.beans.ko.java.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.beans.ko.java.kafka.util.ObjectSerializer;

public class QuickMapProducer {

	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		//用于ShippingCharge测试
		String topic = "EC_ShippingCharge_Data";
		new Thread(()->{
			QuickMapProducer producer = new QuickMapProducer();
			producer.produceJsonMessage(topic,bootstrapServer);
		}).start();
	}
	
	/**
	 * 生产QckMapu格式的数据
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
        
		try(Producer<String, Object> producer = new KafkaProducer<String, Object>(props)){
			for(int i=0;i<2000;i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("9SIAAW16DK5"+i+"|USA|1003", buildMessage());
				ProducerRecord<String,Object> record = new ProducerRecord<String,Object>(topicName,map);
				producer.send(record);
			}
		}
	}
	
	private Map<String, Object> buildMessage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ShippingCharge", 0.56);
		map.put("IntlShippingCharge", 0.99);
		return map;
	}
}
