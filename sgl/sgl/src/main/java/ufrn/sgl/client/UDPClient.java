package ufrn.sgl.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import ufrn.sgl.messages.BiddingRegistrationMessage;
import ufrn.sgl.messages.CompanyRegistrationMessage;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.UserRegistrationMessage;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.Company;
import ufrn.sgl.model.User;

public class UDPClient {

	public UDPClient() {
		
		System.out.println("UDP Client Started");
	
		try {
			
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress inetAddress = InetAddress.getByName("localhost");
			
			User pmNatal = new User("Prefeitura Municipal de Natal", "PMNATAL", "123/0001-23", 
					 new Address("rua das roças", 87, "Candelária", "Natal", "RN"), 
					 "pmnatal@gmail.com");
			
			Message msg1 = new CompanyRegistrationMessage(new Company("Teste", "123", "Teste", "Engenharia"));
			Message msg2 = new UserRegistrationMessage( pmNatal );
			Message msg3 = new BiddingRegistrationMessage(
					new Bidding(pmNatal, "Compra de resma de papel para as escolas municipais", 11203));
			
			
			byte[] sendMessage1 = convertMessageToByteArray(msg1);
			DatagramPacket sendPacket1 = new DatagramPacket(
					sendMessage1, sendMessage1.length,
					inetAddress, 9003);
			clientSocket.send(sendPacket1);
			
			byte[] sendMessage2 = convertMessageToByteArray(msg2);
			DatagramPacket sendPacket2 = new DatagramPacket(
					sendMessage2, sendMessage2.length,
					inetAddress, 9003);
			clientSocket.send(sendPacket2);
			
			byte[] sendMessage3 = convertMessageToByteArray(msg3);
			DatagramPacket sendPacket3 = new DatagramPacket(
					sendMessage3, sendMessage3.length,
					inetAddress, 9003);
			clientSocket.send(sendPacket3);
			
			clientSocket.close();
			
		} catch (IOException ex) { }
		System.out.println("UDP Client Terminating ");
	}
	
	public byte[] convertMessageToByteArray (Message obj) {
		
		try {
	
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bStream);
			oo.writeObject(obj);
			oo.flush();
			
			// get the byte array of the object
			byte[] byteMessage = bStream.toByteArray();
			
			oo.close();
			
			return byteMessage;
		
		} catch (IOException e) {
			System.out.println("ERROR - could not serialize object");
			e.printStackTrace();
		} 
		
		return null;
	}
			
	public static void main(String args[]) { new UDPClient(); }
	
}
