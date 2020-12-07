package com.qucheng.producer;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class InterceptorProducer {
	
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.221.25.222:9092");
		
		properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		//Ìí¼ÓÀ¹½ØÆ÷
		ArrayList<String> interceptors = new ArrayList<>();
		interceptors.add("com.qucheng.interceptor.TimeInterceptor");
		interceptors.add("com.qucheng.interceptor.CounterInterceptor");
		properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		for (int i=0;i<5;i++) {
			producer.send(new ProducerRecord<String, String>("uatenv-3","xiaot","producer-local--"+i),new Callback() {
				
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
