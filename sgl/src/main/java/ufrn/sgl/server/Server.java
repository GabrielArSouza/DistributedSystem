package ufrn.sgl.server;

import java.io.IOException;

import ufrn.sgl.server.udp.UDPServer;

public class Server {

	public static void main(String[] args) { 
		
		
		try {
			UDPServer server = new UDPServer(); 
			server.run();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe informada não foi reconhecida");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Não consegui converter para uma classe existente");
			e.printStackTrace();
		}
	}
	
}
