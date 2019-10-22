package ufrn.sgl.client.tcp;

import java.io.IOException;

import ufrn.sgl.client.AbstractClient;

public class TCPClient extends AbstractClient{

	public TCPClient() throws IOException {
		super(new TCPProtocolClient());
	}

}
