package com.qucheng.interceptor;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class TimeInterceptor implements ProducerInterceptor<String, String>{
	
	public void configure(Map<String, ?> configs) {
		
	}

	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		
		String value = record.value();
		return new ProducerRecord<String, String>(record.topic(), record.partition(),record.key(),System.currentTimeMillis() + "," + value);
		
	}

	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		
	}

	public void close() {
		
	}


}
