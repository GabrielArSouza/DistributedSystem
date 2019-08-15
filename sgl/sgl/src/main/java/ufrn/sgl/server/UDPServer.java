package ufrn.sgl.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;
import ufrn.sgl.messages.protocol.connection.ConfirmConnection;
import ufrn.sgl.util.MessageConvert;

public class UDPServer {

	// Definitions
	private final int SERVERPORT = 9003;
	private final int SIZEUDPDATAGRAM = 1024;
	private MessageConvert msgConvert; 
	
	private DatagramSocket serverSocket;

	public UDPServer() {
			
		try {
			this.setServerSocket(new DatagramSocket(SERVERPORT));
			this.msgConvert = MessageConvert.getInstance();
			System.out.println("UDP Server - Successful Initialization");
		} catch (SocketException e1) {
			System.out.println("ERROR - Failed to initialize server on port " + SERVERPORT);
			e1.printStackTrace();
		}
	
	}
	
	public void run () throws ClassNotFoundException, IOException {
		
		
		while (true) {
						
			Message msg = this.receiveMessage();
			
			// check messages
			if ( msg.getClass().equals(CheckConnection.class) ) { 					
				sendMessage(new ConfirmConnection(), msg.getOrigin());
			}
			
			System.out.println(msg.getClass());
			System.out.println(msg.getMessage() + "\nFROM: " + msg.getOrigin());
				

		}
	}
	
	private void sendMessage ( Message msg, InetAddress address ) throws IOException {
		byte[] msgToSend = msgConvert.convertMessageToByteArray(msg);
		
		DatagramPacket sendPackage = new DatagramPacket(
				msgToSend, msgToSend.length,
				address, 9003);
		serverSocket.send(sendPackage);
		System.out.println("sending message to" + address);
	}
	
	private Message receiveMessage() throws IOException, ClassNotFoundException {
		
		// create package to receive
		byte[] receiveMessage = new byte[SIZEUDPDATAGRAM];
		DatagramPacket receivePacket = new DatagramPacket(
				receiveMessage, receiveMessage.length);
		
		// receive message
		serverSocket.receive(receivePacket);

		// convert message to object
		ObjectInputStream iStream = new ObjectInputStream(
				new ByteArrayInputStream(receivePacket.getData()));
		Message msg = (Message) iStream.readObject();
		msg.setOrigin(receivePacket.getAddress());
		iStream.close();
		
		return msg;
	}
	
	public static void main(String[] args) { 
		UDPServer server = new UDPServer(); 
		try {
			server.run();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
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
