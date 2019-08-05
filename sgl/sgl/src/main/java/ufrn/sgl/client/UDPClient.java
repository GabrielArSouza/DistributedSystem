package ufrn.sgl.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import ufrn.sgl.messages.CompanyRegistrationMessage;
import ufrn.sgl.messages.Message;

public class UDPClient {

	public UDPClient() {
		
		System.out.println("UDP Client Started");
	
		try {
			
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress inetAddress = InetAddress.getByName("3.16.149.25");
			
			Message msg = new CompanyRegistrationMessage("Teste", "123", "Teste", "Engenharia");
			byte[] sendMessage = convertMessageToByteArray(msg);
			DatagramPacket sendPacket = new DatagramPacket(
					sendMessage, sendMessage.length,
					inetAddress, 9003);
					clientSocket.send(sendPacket);
			
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
