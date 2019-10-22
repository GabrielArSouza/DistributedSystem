package ufrn.sgl.server.kafka;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import ufrn.sgl.client.kafka.Producer;
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
import ufrn.sgl.server.rmi.RMIInterface;
import ufrn.sgl.util.Definitions;

public class KafkaProtocolRMI {
	
	private RMIInterface server1;
	private RMIInterface server2;
	private RMIInterface lookUp;
	private HashMap<String, String> activeClients;
	Producer producer = new Producer(Definitions.KAFKA_BROKER);
	private int attemps;
	private final int maxAttemps = 3;
	
	public KafkaProtocolRMI () {
		this.activeClients = new HashMap<String, String>();
		try {
			server1 = (RMIInterface) Naming.lookup(Definitions.SERVERS[0]);
			server2 = (RMIInterface) Naming.lookup(Definitions.SERVERS[1]);
			lookUp = server1;
			attemps = 0;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void reply (Message response) {
		try {
			producer.produce("result", response);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void exec (Message msg) {
		attemps = 0;
		while (attemps < maxAttemps) {
		try {
			if (msg.getClass().equals(RequestUserRegistration.class)) {
				RequestUserRegistration msgUser = (RequestUserRegistration) msg;
				lookUp.registerUser(msgUser.getUser());
			
			}else if (msg.getClass().equals(RequestUserRead.class)) {
				RequestUserRead msgUser = (RequestUserRead) msg;
				User u = lookUp.readUser(msgUser.getUser());
				ReadUserSuccessfully response = new ReadUserSuccessfully(u);
				response.setAddressee(msgUser.getIP());
				this.reply(response);
			
			}else if (msg.getClass().equals(RequestUserSession.class)) {
				RequestUserSession msgUser = (RequestUserSession) msg;
				String token = lookUp.userLogin(msgUser.getUser());
				activeClients.put(msgUser.getUser().getEmail(), token);
			
			}else if (msg.getClass().equals(RequestUserLogout.class)) {
				RequestUserLogout msgUser = (RequestUserLogout) msg;
				String email = msgUser.getUser().getEmail();
				lookUp.userLogout(this.activeClients.get(email));
			
			}else if (msg.getClass().equals(RequestCompanyRegistration.class)) {
				RequestCompanyRegistration msgCompany = (RequestCompanyRegistration) msg;
				lookUp.createCompany(msgCompany.getCompany());
		
			}else if (msg.getClass().equals(RequestCompanyRead.class)){
				RequestCompanyRead msgCompany = (RequestCompanyRead) msg;
				Company c = lookUp.readCompany(msgCompany.getCompany());
				ReadCompanySuccessfully response = new ReadCompanySuccessfully(c);
				response.setAddressee(msgCompany.getIP());
				this.reply(response);
				
			}else if (msg.getClass().equals(RequestCompanySession.class)) {
				RequestCompanySession msgCompany = (RequestCompanySession) msg;
				String token = lookUp.companyLogin(msgCompany.getCompany());
				activeClients.put(msgCompany.getCompany().getEmail(), token);
		
			}else if (msg.getClass().equals(RequestCompanyLogout.class)) {
				RequestCompanyLogout msgCompany = (RequestCompanyLogout) msg;
				String email = msgCompany.getCompany().getEmail();
				lookUp.companyLogout(this.activeClients.get(email));
		
			}else if (msg.getClass().equals(RequestBiddingRegistration.class)) {
				RequestBiddingRegistration msgBidding = (RequestBiddingRegistration) msg;
				String email = msgBidding.getUser().getEmail();
				lookUp.createBidding(msgBidding.getBidding(), activeClients.get(email));
				
			}else if (msg.getClass().equals(RequestBiddingRead.class)) {
				RequestBiddingRead msgBidding = (RequestBiddingRead) msg;
				String email = msgBidding.getUser().getEmail();
				Bidding b = lookUp.readBidding(msgBidding.getBidding(), activeClients.get(email));
				ReadBiddingSuccessfully response = new ReadBiddingSuccessfully(b);
				response.setAddressee(msgBidding.getIP());
				this.reply(response);
			}
			return;
		}catch (Exception e) {
			System.out.println("Erro de conexão - realizando uma nova tentativa");
			if (attemps%2 == 0) lookUp = server2;
			else lookUp = server1;
			attemps++;
		}
		}
	}
	
}
