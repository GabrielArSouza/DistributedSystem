package ufrn.sgl.client.udp;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.sgl.Exceptions.ConnectionFailureException;
import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.OperationFailed;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.PingConnection;
import ufrn.sgl.util.UDPMessageBroker;

public class UDPProtocolClient {

	private final UDPMessageBroker broker;
	private final int maxAttemps = 3;
	private final PingConnection ping;
	
	public UDPProtocolClient () throws SocketException, UnknownHostException {
		
		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receiveSocket = new DatagramSocket(Definitions.SERVER_SEND_PORT);
		receiveSocket.setSoTimeout(5000);
		
		this.broker = new UDPMessageBroker(sendSocket, receiveSocket);
		this.ping = new PingConnection(0);
		ping.start();
	}
	
	public Message requestOperation ( Message msg ) {
		int attemps = 0;
		
		while ( attemps < maxAttemps ) {
			try {
				broker.sendMessage(	msg, getActualIP(), Definitions.SERVER_RECEIVE_PORT);
				Message m = broker.receiveMessage();
				return m; 
			} catch (ConnectionFailureException e) {
				System.out.println("Timeout: " + attemps);
				attemps++;
				continue;
			} catch (UnknownHostException e) {
				return new OperationFailed("Server configuration error - Unknown Host");
			} catch (Exception e) {
				return new OperationFailed("Exception when trying execute operation - Try again");
			}
		}
		return new OperationFailed("Error - check the internet connection");
		
	}

	private InetAddress getActualIP () throws UnknownHostException {
		return InetAddress.getByName(Definitions.SERVERS[ping.getIdServer()]);
	}
	
}
