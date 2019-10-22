package ufrn.sgl.client.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import ufrn.sgl.messages.Message;
import ufrn.sgl.util.AbstractConsumer;
import ufrn.sgl.util.MessageConvert;

public class ClientConsumer extends AbstractConsumer implements Runnable {

	private static MessageConvert msgConvert = MessageConvert.getInstance();
	private String ip;
	public ArrayList<Message> receiveMessages; 
	
	public ClientConsumer(String brokers, String groupId) {
		super(brokers, groupId);
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.receiveMessages = new ArrayList<Message>();
		
	}
	
	public boolean isForMe (Message msg) {
		if (msg.getAddressee().equals(this.ip))
			return true;
		else return false;
	}

	@Override
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
            	if (isForMe(msg)) receiveMessages.add(msg);
            }
            
        }		
	}

	@Override
	public void run() {
		this.consume("result");
	}

}
