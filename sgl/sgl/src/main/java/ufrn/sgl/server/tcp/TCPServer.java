package ufrn.sgl.server.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import ufrn.sgl.messages.Message;
import ufrn.sgl.server.AbstractServer;
import ufrn.sgl.util.Definitions;

public class TCPServer extends AbstractServer{

	private ServerSocket server;
	private Socket connection;
	
	public TCPServer() throws IOException {
		this.server = new ServerSocket(Definitions.SERVER_SEND_PORT);
		System.out.println("TCP Server - Successful Initialization");
		this.startDatabase();
	}
	
	@Override
	protected void sendMessage(Message msg, InetAddress address, int port) throws IOException {
		this.sendMessageTCP(msg);
	}

	private void sendMessageTCP (Message msg) throws IOException {
		System.out.println("Server: Sending message to: " + this.server.getInetAddress().getHostAddress());
		ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
		out.writeObject(msg);
	}
	
	@Override
	protected Message receiveMessage() throws IOException, ClassNotFoundException {
		this.connection = server.accept();
		ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
		Message msg = (Message) input.readObject();
		msg.setOrigin(connection.getInetAddress());
		return msg;
	}

}
