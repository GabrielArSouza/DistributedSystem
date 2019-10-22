package ufrn.sgl.messages.protocol.connection;

import ufrn.sgl.messages.Message;

public class ConfirmConnection extends Message {

	private static final long serialVersionUID = 4656371150130433424L;

	public ConfirmConnection() { /*empty*/ }
	
	@Override
	public String getMessage() {
		return "Confirm connection";
	}

	
	
}
