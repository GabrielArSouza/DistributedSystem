package ufrn.sgl.server.kafka;


import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import ufrn.sgl.messages.Message;
import ufrn.sgl.util.AbstractConsumer;
import ufrn.sgl.util.MessageConvert;



public class ServerConsumer extends AbstractConsumer implements Runnable{
	
	private KafkaProtocolRMI protocol = new KafkaProtocolRMI();
	private static MessageConvert msgConvert = MessageConvert.getInstance();
	
	public ServerConsumer (String brokers, String groupId) {       
        super(brokers, groupId);
	}
	
	public void consume(String topic) {


        // Subscribe to the 'test' topic
        this.consumer.subscribe(Arrays.asList(topic));

        while(true) {
            
        	// Poll for records
            @SuppressWarnings("deprecation")
			ConsumerRecords<String, byte[]> records = consumer.poll(200);
            
           
            // Yes, loop over records
            for(ConsumerRecord<String, byte[]> record: records) {
        		Message msg = msgConvert.convertByteArrayToMessage(record.value());
        		protocol.exec(msg);              	
            }
        }
    }

	@Override
	public void run() {
		this.consume("testeJava");
		
	}
}
