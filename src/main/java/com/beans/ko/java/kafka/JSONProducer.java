package com.beans.ko.java.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.alibaba.fastjson.JSONObject;

public class JSONProducer {

	public static void main(String[] args) {
		String bootstrapServer = "10.16.238.101:8092,10.16.238.102:8092";
		String topic = "test_json";
		new Thread(()->{
			JSONProducer producer = new JSONProducer();
			producer.produceJsonMessage(topic,bootstrapServer);
		}).start();
	}
	
	/**
	 * 生产JSON格式的数据
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
        props.put("value.serializer", StringSerializer.class);
        
		try(Producer<String, String> producer = new KafkaProducer<String, String>(props)){
			JSONObject message = SerializeTool.generationJsonMessage();
			/*构建record
			 * topicName：写入的topic名称；(String) 
			 * message.get("ItemNumber")：record的主键key，可以为null
			 * message.toString():record的值value
			 */
			ProducerRecord<String,String> record = new ProducerRecord<String,String>(topicName,(String) message.get("ItemNumber"),message.toString());
			producer.send(record);
		}
	}
}
