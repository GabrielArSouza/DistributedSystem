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

	private int idServer;
	private int attemps;
	private InetAddress IPAddress; 
	private Boolean isRunning;	
	
	public PingConnection ( int idServer ) throws UnknownHostException {
		try {
			this.attemps = 0;
			this.idServer = idServer; 
			this.sendSocket = new DatagramSocket();
			this.receiveSocket = new DatagramSocket(Definitions.SERVER_SEND_PORT);

			this.IPAddress = InetAddress.getByName(Definitions.SERVERS[this.idServer]);
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
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ConnectionFailureException e) {
				
				if (this.attemps < 2) {
					this.attemps++;
					continue;
				}else {
					this.attemps = 0;
					this.idServer = ++idServer % Definitions.NUMBER_SERVERS;
					try {
						this.IPAddress = InetAddress.getByName(Definitions.SERVERS[this.idServer]);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//System.out.println(e.getMessage());
			} 
		}
	}
	
	@Override
	public void interrupt () {this.isRunning = false;}
	
	private void sendMessage () throws IOException {
		byte[] sendData = new byte[1024];
		sendData = msgConvert.convertMessageToByteArray(new CheckConnection());
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
				IPAddress, Definitions.SERVER_RECEIVE_PORT);
		sendSocket.send(sendPacket);
		System.out.println("Sending mensage to " + IPAddress + ":" +Definitions.SERVER_RECEIVE_PORT);
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
	
	public int getIdServer () {
		return this.idServer;
	}
}
