package ufrn.sgl.client.tcp;

import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.sgl.client.AbstractClient;

public class TCPClient extends AbstractClient{

	public TCPClient() throws SocketException, UnknownHostException {
		super(new TCPProtocolClient());
		// TODO Auto-generated constructor stub
	}

}
