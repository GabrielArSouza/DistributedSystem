package ufrn.sgl.client.udp;

import ufrn.sgl.messages.Message;

public abstract class AbstractProtocolClient {

	public abstract Message requestOperation ( Message msg );
	
}
