package ufrn.sgl.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;
import ufrn.sgl.messages.protocol.connection.ConfirmConnection;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.messages.protocol.session.SuccessfullyLogin;
import ufrn.sgl.model.User;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.MessageConvert;
import ufrn.sgl.util.TokenGenerator;

public class UDPServer {

	// Definitions
	private MessageConvert msgConvert; 
	
	private DatagramSocket serverSocket;
	private DatagramSocket sendServerSocket;

	private Map<String, User> activeSessions;
	
	public UDPServer() {
			
		try {
			this.setServerSocket(new DatagramSocket(Definitions.SERVER_RECEIVE_PORT));
			this.setSendServerSocket(new DatagramSocket());
			this.msgConvert = MessageConvert.getInstance();
			System.out.println("UDP Server - Successful Initialization");
		} catch (SocketException e1) {
			System.out.println("ERROR - Failed to initialize server on port " + Definitions.SERVER_RECEIVE_PORT);
			e1.printStackTrace();
		}
	
	}
	
	public void run () throws ClassNotFoundException, IOException {
		
		while (true) {
			Message msg = this.receiveMessage();
			
			// check messages
			if ( msg.getClass().equals(CheckConnection.class) ) { 					
				sendMessage(new ConfirmConnection(), msg.getOrigin());
			} else if (msg.getClass().equals(RequestSession.class)) {
				// TODO: Check if this a registered user
				
				String token = TokenGenerator.getToken();
				
				// TODO: save the new generated session
				
				sendMessage(new SuccessfullyLogin(token), msg.getOrigin());
			}
			
		}
	}
	
	private void sendMessage ( Message msg, InetAddress address ) throws IOException {
		
		// convert message to a byte array
		byte[] msgToSend = msgConvert.convertMessageToByteArray(msg);
		
		DatagramPacket sendPackage = new DatagramPacket(
				msgToSend, msgToSend.length,
				address, Definitions.SERVER_SEND_PORT);
		sendServerSocket.send(sendPackage);
		
		System.out.println("sending message to" + address);
	}
	
	private Message receiveMessage() throws IOException, ClassNotFoundException {
		
		// create package to receive
		byte[] receiveMessage = new byte[1024];
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
		System.out.println(msg.getMessage() + "\nFROM: " + msg.getOrigin());
		return msg;
	}
	
	public DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(DatagramSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public DatagramSocket getSendServerSocket() {
		return sendServerSocket;
	}

	public void setSendServerSocket(DatagramSocket sendServerSocket) {
		this.sendServerSocket = sendServerSocket;
	}
	
	public static void main(String[] args) { 
		
		UDPServer server = new UDPServer(); 
		try {
			server.run();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}	
	
}
