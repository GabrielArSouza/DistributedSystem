package server;

import java.net.Socket;

public class TreatmentClass extends Thread {

	private final Socket client;
	
	public TreatmentClass (Socket client) {
		this.client = client;
	}
	
	public void run () {
		//empty
	}
	
}
