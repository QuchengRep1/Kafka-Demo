package com.qucheng.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class PartitionProducer {
	
	public static void main(String[] args) {
		
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
		
		properties.put("partitioner.class", "com.qucheng.partitioner.MyPartitioner");
		
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		for (int i=0;i<25;i++) {
			producer.send(new ProducerRecord<String, String>("uatenv", "producer-local--"+i),new Callback() {		
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					
						if (exception == null) {
							System.out.println(metadata.partition() + "--" + metadata.offset()); 				
						}else {
							exception.printStackTrace();
						}
					}

			});
			}
		
		producer.close();
	}
}
