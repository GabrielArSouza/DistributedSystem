package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main (String[] args) throws IOException { 
		
		/**
		 * initiate the server
		 */
		
		int PORT = 9900;
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("The port " + PORT + " is open");
		
		/**
		 * accept a connection with a client
		 */
		Socket client = server.accept();
		System.out.println("A new connections has been established"
				+ " with the client " +
				client.getInetAddress().getHostAddress());
		
		/**
		 * listening the client
		 */
		
		Scanner scanner = new Scanner(client.getInputStream());
		while ( scanner.hasNextLine() ) {
			System.out.println(scanner.nextLine());
		}
		
		/**
		 * close the connection
		 */
		scanner.close();
		client.close();
		server.close();
	}
}
