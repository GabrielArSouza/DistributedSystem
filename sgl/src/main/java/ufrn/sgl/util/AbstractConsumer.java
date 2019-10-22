package ufrn.sgl.util;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;


public abstract class AbstractConsumer {

	protected final KafkaConsumer<String, byte[]> consumer;
	
	public AbstractConsumer(String brokers, String groupId) {       
        // Configure the consumer
        Properties properties = new Properties();
        
        // Point it to the brokers
        properties.setProperty("bootstrap.servers", brokers);
        
        // Set the consumer group (all consumers must belong to a group).
        properties.setProperty("group.id", groupId);
        
        // Set how to serialize key/value pairs
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.ByteArrayDeserializer");
        
        // When a group is first created, it has no offset stored to start reading from. This tells it to start
        // with the earliest record in the stream.
        properties.setProperty("auto.offset.reset","earliest");

        this.consumer = new KafkaConsumer<>(properties);
	}
	
	public abstract void consume (String topic);
	
}
