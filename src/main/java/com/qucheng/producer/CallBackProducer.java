package com.qucheng.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.qucheng.partitioner.MyPartitioner;

public class CallBackProducer {
	public static void main(String[] args) throws InterruptedException {
		
		Properties properties = new Properties();
		
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.221.25.222:9092,10.221.37.50:9092,10.221.12.107:9092");
		
		properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		for (int i=0;i<2;i++) {
			producer.send(new ProducerRecord<String, String>("uatenv-6","producer-local--"+i),new Callback() {
				
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if (exception == null) {
						System.out.println(metadata.partition() + "--" + metadata.offset()); 				
					}else {
						exception.printStackTrace();
					}
				}
			});
		}
		
		//Thread.sleep(2000);
/*		
		for (int i=0;i<5;i++) {
			producer.send(new ProducerRecord<String, String>("uatenv-3",5,"xiaot","producer-local--"+i),new Callback() {
				
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if (exception == null) {
						System.out.println(metadata.partition() + "--" + metadata.offset()); 				
					}else {
						exception.printStackTrace();
					}
				}
			});
		}
*/		
		producer.close();
	}

}
