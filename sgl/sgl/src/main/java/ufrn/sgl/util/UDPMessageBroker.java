package ufrn.sgl.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import ufrn.sgl.Exceptions.ConnectionFailureException;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;

public class UDPMessageBroker {

	private final MessageConvert msgConvert = MessageConvert.getInstance();
	private final DatagramSocket sendSocket;
	private final DatagramSocket receiveSocket;
	
	public UDPMessageBroker ( DatagramSocket sendSocket, DatagramSocket receiveSocket ) {
		this.sendSocket = sendSocket;
		this.receiveSocket = receiveSocket;
	}
	
	public void sendMessage (InetAddress IPAddress, Integer port) throws IOException {
		byte[] sendData = new byte[1024];
		sendData = msgConvert.convertMessageToByteArray(new CheckConnection());
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
				IPAddress, port);
		sendSocket.send(sendPacket);
		System.out.println("Sending mensage to " + IPAddress + ":" +Definitions.SERVER_RECEIVE_PORT);
	}
	
	public Message receiveMessage () throws IOException, ClassNotFoundException, ConnectionFailureException {
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		
		try { receiveSocket.receive(receivePacket); }
		catch (SocketTimeoutException e) { throw new ConnectionFailureException(); }
		
		// convert message to object
		ObjectInputStream iStream = new ObjectInputStream(
				new ByteArrayInputStream(receivePacket.getData()));
		Message msg = (Message) iStream.readObject();
		msg.setOrigin(receivePacket.getAddress());
		iStream.close();
		
		return msg;
	}
	
}
