package ufrn.sgl.server.udp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import ufrn.sgl.messages.Message;
import ufrn.sgl.server.AbstractServer;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.MessageConvert;


public class UDPServer extends AbstractServer{
	
	private DatagramSocket serverSocket;
	private DatagramSocket sendServerSocket;
	
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
	
//	public void run () throws ClassNotFoundException, IOException {
//		
//		while (true) {
//			Message msg = this.receiveMessage();
//			
//			// check messages
//			if ( msg.getClass().equals(CheckConnection.class) ) { 
//				Message replyMessage = ProtocolServer.connection();
//				sendMessage(replyMessage, msg.getOrigin(), Definitions.PING_PORT);
//			
//			} else if (msg.getClass().getSuperclass().equals(RequestSession.class)) {
//				System.out.println("requestSession");
//				Message reply = ProtocolServer.session( msg );
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//			
//			} else if (msg.getClass().getSuperclass().equals(RequestLogout.class)) {
//				System.out.println("request logout");
//				Message reply = ProtocolServer.logout( msg );
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//		
//			} else if (msg.getClass().getSuperclass().equals(RequestRegistration.class)) {
//				Message reply = ProtocolServer.register(msg);
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//			
//			} else if (msg.getClass().getSuperclass().equals(RequestRemove.class)) {
//				Message reply = ProtocolServer.remove(msg);
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//			
//			} else if (msg.getClass().getSuperclass().equals(RequestRead.class)) {
//				Message reply = ProtocolServer.read(msg);
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//			
//			} else if (msg.getClass().getSuperclass().equals(RequestUpdate.class)) {
//				Message reply = ProtocolServer.update(msg);
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//				
//			} else if (msg.getClass().getSuperclass().equals(RequestList.class)) {
//				Message reply = ProtocolServer.list(msg);
//				sendMessage(reply, msg.getOrigin(), Definitions.SERVER_SEND_PORT);
//			}
//			
//			else continue;
//			
//		}
//	}
	
	@Override
	protected void sendMessage ( Message msg, InetAddress address, int port ) throws IOException {
		
		
		// convert message to a byte array
		byte[] msgToSend = msgConvert.convertMessageToByteArray(msg);
		
		DatagramPacket sendPackage = new DatagramPacket(
				msgToSend, msgToSend.length,
				address, port );
		sendServerSocket.send(sendPackage);
		
		//System.out.println("sending message to" + address);
	}
	
	@Override
	protected Message receiveMessage() throws IOException, ClassNotFoundException {
		
		// create package to receive
		byte[] receiveMessage = new byte[2048];
		DatagramPacket receivePacket = new DatagramPacket(
				receiveMessage, receiveMessage.length);
		
		// receive message
		serverSocket.receive(receivePacket);
		
		// convert message to object
		ObjectInputStream iStream = new ObjectInputStream(
				new ByteArrayInputStream(receivePacket.getData()));
		iStream.close();
		Message msg = (Message) iStream.readObject();
		msg.setOrigin(receivePacket.getAddress());
		
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
		} catch (ClassNotFoundException e) {
			System.out.println("A classe informada não foi reconhecida");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Não consegui converter para uma classe existente");
			e.printStackTrace();
		}
	}	
	
}
