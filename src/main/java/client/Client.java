package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static void main (String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("127.0.0.1", 9900);
		System.out.println("O cliente se conectou no servidor");
		
		Scanner keybord = new Scanner(System.in);
		PrintStream out = new PrintStream(client.getOutputStream());
		
		while (keybord.hasNextLine()) {
			out.println(keybord.nextLine());
		}
		
		out.close();
		keybord.close();
		client.close();
	}
	
}
	
	
