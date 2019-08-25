package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.messages.Message;

public abstract class RequestRead extends Message {

	private static final long serialVersionUID = -1944299139901543778L;

	protected final long id;
	
	public RequestRead( long id ) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	
	
}
