package ufrn.sgl.client;

import ufrn.sgl.messages.Message;

public abstract class AbstractProtocolClient {

	public abstract Message requestOperation ( Message msg );
	
}
