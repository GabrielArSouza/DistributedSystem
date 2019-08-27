package ufrn.sgl.messages.protocol.session;

import ufrn.sgl.messages.Message;

public class FailSession extends Message {
	
	private static final long serialVersionUID = 787571376122225165L;

	@Override
	public String getMessage() {
		return "Error: Username or password wrong";
	}

}
