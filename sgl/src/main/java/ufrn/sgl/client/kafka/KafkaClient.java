package ufrn.sgl.client.kafka;


import java.util.concurrent.ExecutionException;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.logout.RequestCompanyLogout;
import ufrn.sgl.messages.protocol.logout.RequestUserLogout;
import ufrn.sgl.messages.protocol.read.ReadBiddingSuccessfully;
import ufrn.sgl.messages.protocol.read.ReadCompanySuccessfully;
import ufrn.sgl.messages.protocol.read.ReadUserSuccessfully;
import ufrn.sgl.messages.protocol.read.RequestBiddingRead;
import ufrn.sgl.messages.protocol.read.RequestCompanyRead;
import ufrn.sgl.messages.protocol.read.RequestUserRead;
import ufrn.sgl.messages.protocol.register.RequestBiddingRegistration;
import ufrn.sgl.messages.protocol.register.RequestCompanyRegistration;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.session.RequestCompanySession;
import ufrn.sgl.messages.protocol.session.RequestUserSession;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;
import ufrn.sgl.util.Definitions;

public class KafkaClient {
	
	private final String topic = "testeJava";
	
	private Producer producer = new Producer(Definitions.KAFKA_BROKER);
	private ClientConsumer consumer = new ClientConsumer(Definitions.KAFKA_BROKER, Definitions.KAFKA_GROUP);
	private Thread t;
	
	public KafkaClient () {
		this.t = new Thread(consumer);
		t.start();
	}
	
	public void userRegister (User user) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestUserRegistration(user));
	}
	
	public User userRead (User user) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestUserRead(user));
		while (true) {
			for ( Message msg : consumer.receiveMessages ) {
				if (msg.getClass().equals(ReadUserSuccessfully.class)) {
					ReadUserSuccessfully message = (ReadUserSuccessfully) msg;
					consumer.receiveMessages.remove(msg);
					return message.getUser();
				}
			}
		}
	}
	
	
	public void userLogin (User user) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestUserSession(user));
	}
	
	public void userLogout (User user) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestUserLogout(user));
	}
	
	public void companyRegister (Company company) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestCompanyRegistration(company));
	}
	
	public Company companyRead (Company company) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestCompanyRead(company));
		while (true) {
			for ( Message msg : consumer.receiveMessages ) {
				if (msg.getClass().equals(ReadCompanySuccessfully.class)) {
					ReadCompanySuccessfully message = (ReadCompanySuccessfully) msg;
					consumer.receiveMessages.remove(msg);
					return message.getCompany();
				}
			}
		}
	}
	
	public void companyLogin (Company company) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestCompanySession(company));
	}
	
	public void companyLogout (Company company) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestCompanyLogout(company));
	}
	
	public void registerBidding (Bidding bidding, User user) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestBiddingRegistration(bidding, user));
	}
	
	public Bidding readBidding (Bidding bidding, User user) throws InterruptedException, ExecutionException {
		producer.produce(topic, new RequestBiddingRead(bidding, user));
		while (true) {
			for ( Message msg : consumer.receiveMessages ) {
				if (msg.getClass().equals(ReadBiddingSuccessfully.class)) {
					ReadBiddingSuccessfully message = (ReadBiddingSuccessfully) msg;
					consumer.receiveMessages.remove(msg);
					return message.getBidding();
				}
			}
		}

	}
}
