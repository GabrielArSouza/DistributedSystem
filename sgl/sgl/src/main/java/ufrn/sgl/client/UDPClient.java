package ufrn.sgl.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import ufrn.sgl.messages.BiddingRegistrationMessage;
import ufrn.sgl.messages.CompanyRegistrationMessage;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.UserRegistrationMessage;
import ufrn.sgl.messages.protocol.connection.CheckConnection;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;
import ufrn.sgl.util.MessageConvert;

public class UDPClient {

	MessageConvert msgConvert = MessageConvert.getInstance();
	
	public UDPClient() {
		
		System.out.println("UDP Client Started");
	
		try {
			
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress inetAddress = InetAddress.getByName("localhost");
			
//			User pmNatal = new User("Prefeitura Municipal de Natal", "PMNATAL", "123/0001-23", 
//					 new Address("rua das roças", 87, "Candelária", "Natal", "RN"), 
//					 "pmnatal@gmail.com");
//			
//			Message msg1 = new CompanyRegistrationMessage(new Company("Teste", "123", "Teste", "Engenharia"));
//			Message msg2 = new UserRegistrationMessage( pmNatal );
//			Message msg3 = new BiddingRegistrationMessage(
//					new Bidding(pmNatal, "Compra de resma de papel para as escolas municipais", 11203));
//			
			
//			byte[] sendMessage1 = convertMessageToByteArray(msg1);
//			DatagramPacket sendPacket1 = new DatagramPacket(
//					sendMessage1, sendMessage1.length,
//					inetAddress, 9003);
//			clientSocket.send(sendPacket1);
//			
//			byte[] sendMessage2 = convertMessageToByteArray(msg2);
//			DatagramPacket sendPacket2 = new DatagramPacket(
//					sendMessage2, sendMessage2.length,
//					inetAddress, 9003);
//			clientSocket.send(sendPacket2);
//			
//			byte[] sendMessage3 = convertMessageToByteArray(msg3);
//			DatagramPacket sendPacket3 = new DatagramPacket(
//					sendMessage3, sendMessage3.length,
//					inetAddress, 9003);
//			clientSocket.send(sendPacket3);
//			
			
			try {
				this.pingConnection(clientSocket, inetAddress);
				wait(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//clientSocket.close();
			
		} catch (IOException ex) { }
		System.out.println("UDP Client Terminating ");
	}
	
	private void pingConnection (DatagramSocket clientSocket, InetAddress server ) throws IOException, ClassNotFoundException {
		
		while (true) {
			byte[] ping = msgConvert.convertMessageToByteArray(new CheckConnection());
			DatagramPacket sendPingPacket = new DatagramPacket(
					ping, ping.length, server, 9003);
			clientSocket.send(sendPingPacket);
				
			DatagramPacket receivePingPacket = new DatagramPacket(
					ping, ping.length, server, 9003);
			clientSocket.receive(receivePingPacket);
			// convert message to object
			ObjectInputStream iStream = new ObjectInputStream(
					new ByteArrayInputStream(receivePingPacket.getData()));
			Message msg = (Message) iStream.readObject();
			msg.setOrigin(receivePingPacket.getAddress());
			iStream.close();
		}
	}
	
			
	public static void main(String args[]) { new UDPClient(); }
	
}
