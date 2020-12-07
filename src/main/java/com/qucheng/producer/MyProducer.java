package com.qucheng.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;


public class MyProducer {
	public static void main(String[] args) throws InterruptedException {
		
		// 1. Create Kafka producer config
		Properties properties = new Properties();
		
		properties.put("bootstrap.servers", "10.221.25.222:9092");
		
		//properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.221.25.222:9092");
		
		properties.put("acks","all");
		//properties.put(ProducerConfig.ACKS_CONFIG, "all");
		
		properties.put("retries","3");
		//properties.put(ProducerConfig.RETRIES_CONFIG, "3");
		
		properties.put("batch.size","16384");
		
		properties.put("linger.ms","1");
		
		properties.put("buffer.memory","33554432"); 
		
		properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		for (int i=0;i<25;i++) {
			producer.send(new ProducerRecord<String, String>("uatenv", "producer-local--"+i));
			}
		
		producer.close();
		
	}
}
