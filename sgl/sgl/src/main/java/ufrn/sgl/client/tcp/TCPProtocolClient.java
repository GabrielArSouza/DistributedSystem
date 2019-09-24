package ufrn.sgl.client.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import ufrn.sgl.Exceptions.ConnectionFailureException;
import ufrn.sgl.client.AbstractProtocolClient;
import ufrn.sgl.messages.Message;
import ufrn.sgl.util.Definitions;
import ufrn.sgl.util.PingConnection;
import ufrn.sgl.util.PingConnectionTCP;

public class TCPProtocolClient extends AbstractProtocolClient {

	private Socket socket;
	private final PingConnectionTCP ping;
	
	public TCPProtocolClient() throws IOException {
		
		// start in a random server
		Random rand = new Random();
		int n = rand.nextInt(Definitions.NUMBER_SERVERS);
		this.ping = new PingConnectionTCP(n);
		
		ping.start();
		
	}

	@Override
	public Message requestOperation(Message msg) {
	
		try {
			socket = new Socket(Definitions.SERVERS[ping.getIdServer()], 
					Definitions.SERVER_SEND_PORT);
			socket.setSoTimeout(2000);
			
			// send message to server			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(msg);
			
			// receive server response
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());		
			Message m = (Message) in.readObject();
			socket.close();
			
			return m;
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
			
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	
}
