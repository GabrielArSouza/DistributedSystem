package ufrn.sgl.server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class UDPMulticastServer {

	// Definitions
	private final int SERVERPORT = 9003;
	private final String MULTICASTIP = "228.5.6.7";
	private MulticastSocket multicastSocket;
	
	public UDPMulticastServer() {
		System.out.println("UDP Multicast Time Server Started");
		
		try {
			multicastSocket = new MulticastSocket();
			InetAddress inetAddress = InetAddress.getByName(MULTICASTIP);
			multicastSocket.joinGroup(inetAddress);
			
			byte[] data;
			DatagramPacket packet;
			
			while (true) {
				Thread.sleep(1000);
				String message = (new Date()).toString();
				System.out.println("Sending: [" + message + "]");
				data = message.getBytes();
				packet = new DatagramPacket(data, message.length(),
				inetAddress, SERVERPORT);
				multicastSocket.send(packet);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("UDP Multicast Time Server Terminated");
	}
	
	public static void main(String args[]) {
		new UDPMulticastServer();
	}
	
}
