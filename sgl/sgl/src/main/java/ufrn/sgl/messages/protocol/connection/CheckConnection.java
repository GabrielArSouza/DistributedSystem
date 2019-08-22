package ufrn.sgl.messages.protocol.connection;

import ufrn.sgl.messages.Message;

public class CheckConnection extends Message{

	private static final long serialVersionUID = -2811290172119445850L;

	public CheckConnection() { /*empty*/ }
	
	@Override
	public String getMessage() {
		return "Check Connection";
	}
	
}
