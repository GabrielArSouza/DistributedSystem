package ufrn.sgl.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import ufrn.sgl.messages.Message;

public class UDPServer {

	// Definitions
	private final int SERVERPORT = 9003;
	private final int SIZEUDPDATAGRAM = 1024;
	
	private DatagramSocket serverSocket;

	public UDPServer() {
		
		
		try {
			this.setServerSocket(new DatagramSocket(SERVERPORT));
			System.out.println("UDP Server - Successful Initialization");
		} catch (SocketException e1) {
			System.out.println("ERROR - Failed to initialize server on port " + SERVERPORT);
			e1.printStackTrace();
		}
	}
	
	public void run () throws ClassNotFoundException {
		while (true) {
			
			byte[] receiveMessage = new byte[SIZEUDPDATAGRAM];
			DatagramPacket receivePacket = new DatagramPacket(
					receiveMessage, receiveMessage.length);
				
			try {
			
				serverSocket.receive(receivePacket);

				ObjectInputStream iStream = new ObjectInputStream(
						new ByteArrayInputStream(receivePacket.getData()));
				Message msg = (Message) iStream.readObject();
				iStream.close();
				System.out.println(msg.getMessage() + "\nFROM: " + receivePacket.getAddress());
				
			} catch (IOException e) {
				System.out.println("ERROR - Could not read received message");
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) { 
		UDPServer server = new UDPServer(); 
		try {
			server.run();
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR - Class Message not Found");
			e.printStackTrace();
		}
	}

	public DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(DatagramSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	
}
