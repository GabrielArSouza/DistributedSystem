package ufrn.sgl.client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ufrn.sgl.util.MessageConvert;
import ufrn.sgl.util.PingConnection;

public class UDPClient {

	private MessageConvert msgConvert = MessageConvert.getInstance();
	private DatagramSocket clientSocket;
	private int idServer;
	
	public UDPClient() {
		
		System.out.println("UDP Client Started");
		this.idServer = 0;
		try {
			this.clientSocket = new DatagramSocket();	
			//InetAddress inetAddress = InetAddress.getByName("localhost");	
	
		} catch (IOException ex) { }
	}
	
	public void run () {
		try {
			
			PingConnection ping = new PingConnection(idServer);
			ping.start();
			Thread.sleep(50000);
			ping.interrupt();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("UDP Client Terminating ");
	}
	
	public int getIdServer () { return idServer; }
	public void setIdServer (int id) {this.idServer = id;} 
	
	public static void main(String args[]) { 
		UDPClient client = new UDPClient(); 
		client.run();
	}
	
}
