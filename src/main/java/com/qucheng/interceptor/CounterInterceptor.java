package com.qucheng.interceptor;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CounterInterceptor implements ProducerInterceptor<String, String>{

	int SUCCESS;
	int ERROR;
	
	public void configure(Map<String, ?> configs) {
		
	}

	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		return record;
	}
	
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		
		if(metadata != null) {
			SUCCESS++;
		} else {
			ERROR++;
		}	
	}

	public void close() {
		System.out.println("success: " + SUCCESS);
		System.out.println("error: " + ERROR);
	}
	
}
