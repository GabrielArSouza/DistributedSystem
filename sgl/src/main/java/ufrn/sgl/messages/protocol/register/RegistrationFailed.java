package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.messages.Message;

public class RegistrationFailed extends Message{

	private static final long serialVersionUID = -5975388267852208933L;

	@Override
	public String getMessage() {
		return "Error";
	}
	
}
