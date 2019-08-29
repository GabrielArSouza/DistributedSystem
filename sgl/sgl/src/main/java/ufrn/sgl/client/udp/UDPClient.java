package ufrn.sgl.client.udp;

import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.sgl.client.AbstractClient;
import ufrn.sgl.client.udp.UDPProtocolClient;

public class UDPClient extends AbstractClient {

	public UDPClient() throws SocketException, UnknownHostException {
		super(new UDPProtocolClient());
	}

}
