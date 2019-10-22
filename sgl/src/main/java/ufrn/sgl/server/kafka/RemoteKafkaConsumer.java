package ufrn.sgl.server.kafka;

import ufrn.sgl.util.Definitions;

public class RemoteKafkaConsumer {

	public static void main (String args[]) {
		Thread t = new Thread(
				new ServerConsumer(
					Definitions.KAFKA_BROKER,
					Definitions.KAFKA_GROUP));
		t.start();
	}
	
}
