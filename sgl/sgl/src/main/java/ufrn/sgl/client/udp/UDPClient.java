package ufrn.sgl.client.udp;

import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient extends AbstractClient {

	public UDPClient() throws SocketException, UnknownHostException {
		super(new UDPProtocolClient());
	}

}
