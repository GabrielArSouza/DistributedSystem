package ufrn.sgl.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;
import ufrn.sgl.messages.protocol.logout.RequestLogout;
import ufrn.sgl.messages.protocol.register.RequestRegistration;
import ufrn.sgl.messages.protocol.session.RequestSession;
import ufrn.sgl.messages.protocol.session.RequestUserSession;
import ufrn.sgl.model.User;
import ufrn.sgl.service.UserService;
import ufrn.sgl.service.interfaces.UserServiceInterface;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.MessageConvert;


public class UDPServer {

	// Definitions
	private MessageConvert msgConvert; 
	
	private DatagramSocket serverSocket;
	private DatagramSocket sendServerSocket;
	private static final UserServiceInterface service = new UserService();
	
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
		
		this.startDatabase();
	
	}
	
	private void startDatabase () {
		// Start database connection
		System.out.println("Starting the databse connection...");
		User user = new User("init", "init");
		service.create(user);
		user = service.read(user);
		service.delete(user.getId());
		System.out.println("Done!");
	}
	
	public void run () throws ClassNotFoundException, IOException {
		
		while (true) {
			Message msg = this.receiveMessage();
			
			// check messages
			if ( msg.getClass().equals(CheckConnection.class) ) { 
				Message replyMessage = UDPProtocolServer.connection();
				sendMessage(replyMessage, msg.getOrigin(), Definitions.PING_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestSession.class)) {
				System.out.println("requestSession");
				Message replyMessage = UDPProtocolServer.session( msg );
				sendMessage(replyMessage, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			
			} else if (msg.getClass().getSuperclass().equals(RequestLogout.class)) {
				System.out.println("request logout");
				Message replyMessage = UDPProtocolServer.logout( msg );
				sendMessage(replyMessage, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
		
			} else if (msg.getClass().getSuperclass().equals(RequestRegistration.class)) {
				Message replyMessage = UDPProtocolServer.register(msg);
				sendMessage(replyMessage, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
			
			} 
			
		}
	}
	
	private void sendMessage ( Message msg, InetAddress address, int port ) throws IOException {
		
		
		// convert message to a byte array
		byte[] msgToSend = msgConvert.convertMessageToByteArray(msg);
		
		DatagramPacket sendPackage = new DatagramPacket(
				msgToSend, msgToSend.length,
				address, port );
		sendServerSocket.send(sendPackage);
		
		//System.out.println("sending message to" + address);
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
		//System.out.println(msg.getMessage() + "\nFROM: " + msg.getOrigin());
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
