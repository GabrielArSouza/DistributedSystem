package ufrn.sgl.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import ufrn.sgl.Exceptions.ConnectionFailureException;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;

public class PingConnection extends Thread{
	
	private DatagramSocket sendSocket;
	private DatagramSocket receiveSocket;
	private MessageConvert msgConvert = MessageConvert.getInstance();
	
	private String server = "localhost";
	private InetAddress IPAddress; 
	private Boolean isRunning;
	
	public PingConnection () throws UnknownHostException {
		try {
		
			this.sendSocket = new DatagramSocket();
			this.receiveSocket = new DatagramSocket(9013);
			this.IPAddress = InetAddress.getByName(server);
			this.isRunning = true;
			this.receiveSocket.setSoTimeout(1000);
			
		} catch (SocketException e) { e.printStackTrace(); }
	}
	
	@Override
	public void run () {
		while (isRunning) {
			try {
				this.sendMessage();
				Message m = this.receiveMessage();
				System.out.println(m.getClass());
				Thread.sleep(1000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConnectionFailureException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	@Override
	public void interrupt () {this.isRunning = false;}
	
	private void sendMessage () throws IOException {
		byte[] sendData = new byte[1024];
		sendData = msgConvert.convertMessageToByteArray(new CheckConnection());
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
				IPAddress, 9003);
		sendSocket.send(sendPacket);
	}
	
	private Message receiveMessage () throws IOException, ClassNotFoundException, ConnectionFailureException {
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
