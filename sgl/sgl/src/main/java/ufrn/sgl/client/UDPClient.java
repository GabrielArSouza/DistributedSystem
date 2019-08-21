package ufrn.sgl.client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.sgl.Exceptions.ConnectionFailureException;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.register.RequestRegistration;
import ufrn.sgl.messages.protocol.register.RequestUserRegistration;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.User;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.MessageConvert;
import ufrn.sgl.util.PingConnection;
import ufrn.sgl.util.UDPMessageBroker;

public class UDPClient {
	
	private final UDPMessageBroker broker;
	private InetAddress IPAddress;
	private int idServer;
	
	public UDPClient() throws SocketException, UnknownHostException {
		
		System.out.println("UDP Client Started");
		this.idServer = 0;
		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receiveSocket = new DatagramSocket(Definitions.SERVER_SEND_PORT);
		IPAddress = InetAddress.getByName("localhost");
		receiveSocket.setSoTimeout(10000);
		
		this.broker = new UDPMessageBroker(sendSocket, receiveSocket);
		
//		try {
//			this.clientSocket = new DatagramSocket();	
//			InetAddress inetAddress = InetAddress.getByName("localhost");	
//	
//		} catch (IOException ex) { }
	}
	
	public void run () throws ClassNotFoundException, ConnectionFailureException {
//		try {
//			
//			PingConnection ping = new PingConnection(idServer);
//			ping.start();
//			Thread.sleep(50000);
//			ping.interrupt();
//			
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		User user = new User ("pmnat@gmail.com", "123");
//		User userError = new User ("pmat@gmail.com", "123");
        User user = new User("Prefeitura Municipal de Natal", "PMNat", "122342483/0001-23", 
				 new Address("av Pedro Pedrosa", 123, "Candelária", "Natal", "RN"), 
				 "pmnatgrand@gmail.com", "1234567");

		try {
			broker.sendMessage(new RequestUserRegistration(user), IPAddress, Definitions.SERVER_RECEIVE_PORT);
			Message r = broker.receiveMessage();
			System.out.println(r.getMessage());
//			broker.sendMessage(new RequestSession(userError), IPAddress, Definitions.SERVER_RECEIVE_PORT);
//			r = broker.receiveMessage();
//			System.out.println(r.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("UDP Client Terminating ");
	}
	
	public int getIdServer () { return idServer; }
	public void setIdServer (int id) {this.idServer = id;} 
	
	public static void main(String args[]) { 
		
		try {
			UDPClient client = new UDPClient();
			client.run();
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
}
