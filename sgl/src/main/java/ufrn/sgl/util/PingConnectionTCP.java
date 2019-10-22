package ufrn.sgl.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ufrn.sgl.messages.Message;
import ufrn.sgl.messages.protocol.connection.CheckConnection;

public class PingConnectionTCP extends Thread{

	private int idServer;
	private Socket socket;
	private boolean isRunning;
	private int attemps = 0;
	
	public PingConnectionTCP(int idServer) {
		this.idServer = idServer;
		try {
			this.socket = new Socket(Definitions.SERVERS[idServer], 
								Definitions.SERVER_SEND_PORT);
			this.isRunning = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run () {
		while (isRunning) {
			
			try {
				Message m = testConnection();
				System.out.println(m.getMessage());
			} catch (ClassNotFoundException | IOException e) {
				if (attemps > 2) isRunning = true;
				this.attemps++;
				this.idServer = ++idServer % Definitions.NUMBER_SERVERS;
				continue;
			}
		
		}
	}
	
	private Message testConnection () throws IOException, ClassNotFoundException {
		this.socket = new Socket(Definitions.SERVERS[idServer], 
				Definitions.SERVER_SEND_PORT);
		this.socket.setSoTimeout(1000);
		
		System.out.println("Sending message to: " + socket.getInetAddress().getHostName());
		// send message to server			
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(new CheckConnection());
		socket.close();
		
		this.socket = new Socket(Definitions.SERVERS[idServer], 
				Definitions.PING_PORT);
		this.socket.setSoTimeout(1000);
		// receive server response
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());		
		Message m = (Message) in.readObject();
		System.out.println(m.getClass());
		socket.close();
		return m;
	}

	public int getIdServer() {
		return idServer;
	}

	public void setIdServer(int idServer) {
		this.idServer = idServer;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
	
	
	
}
