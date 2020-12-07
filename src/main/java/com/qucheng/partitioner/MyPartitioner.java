package com.qucheng.partitioner;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class MyPartitioner implements Partitioner{

	public void configure(Map<String, ?> configs) {

		
	}

	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		
		//Integer integer = cluster.partitionCountForTopic(topic);
	
		return 0;
		//return key.toString().hashCode() % integer;
	}

	public void close() {
		
		
	}

}
 


