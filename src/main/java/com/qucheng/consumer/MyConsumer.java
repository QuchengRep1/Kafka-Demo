package com.qucheng.consumer;


import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyConsumer {
	

	public static void main(String[] args) {
		
		Properties properties = new Properties();
		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.221.25.222:9092,10.221.37.50:9092,10.221.12.107:9092");
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "uatenv-21-group");
//		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest);
		
		
		KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(properties); 
		
		consumer.subscribe(Arrays.asList("uatenv-6"));
		
		while (true) {
			
			//System.out.println("nothing available...");
			
			ConsumerRecords<String,String> consumerRecords = consumer.poll(100);
			
			for (ConsumerRecord<String, String> consumerRecord :consumerRecords) {
			
				System.out.println(consumerRecord.key() + "--" + consumerRecord.value());
			}
		}
	}
}
