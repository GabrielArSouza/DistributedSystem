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
import java.net.UnknownHostException;
import java.sql.Time;

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
import ufrn.sgl.util.PingConnection;

public class UDPClient {

	MessageConvert msgConvert = MessageConvert.getInstance();
	DatagramSocket clientSocket;
	
	public UDPClient() {
		
		System.out.println("UDP Client Started");
	
		try {
			
			this.clientSocket = new DatagramSocket();
			
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
			
	
		} catch (IOException ex) { }
		
		try {
			PingConnection ping = new PingConnection();
			ping.start();
			Thread.sleep(15000);
			ping.interrupt();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("UDP Client Terminating ");
	}
			
	public static void main(String args[]) { new UDPClient(); }
	
}
