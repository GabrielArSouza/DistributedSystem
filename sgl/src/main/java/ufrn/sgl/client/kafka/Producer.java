package ufrn.sgl.client.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import ufrn.sgl.messages.Message;
import ufrn.sgl.util.MessageConvert;

public class Producer {
	
	private final KafkaProducer<String, byte[]> producer;
	private final MessageConvert converter = MessageConvert.getInstance();
	
	public Producer (String brokers) {
		// Set properties used to configure the producer
        Properties properties = new Properties();
        
        // Set the brokers (bootstrap servers)
        properties.setProperty("bootstrap.servers", brokers);
        
        // Set how to serialize key/value pairs
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.ByteArraySerializer");
        
        this.producer = new KafkaProducer<>(properties);
	}

	public void produce(String topic, Message msg) throws InterruptedException, ExecutionException
    {
		// get the byte array of the object
		byte[] byteMessage = converter.convertMessageToByteArray(msg);
		producer.send(new ProducerRecord<String, byte[]>(topic, byteMessage)).get();
        
    }
	
}
