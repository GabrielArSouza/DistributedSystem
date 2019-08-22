package ufrn.sgl.messages.protocol.session;

import ufrn.sgl.messages.Message;

public class SuccessfullyLogin extends Message{

	private static final long serialVersionUID = 882912015497894705L;

	private final String token;
	
	public SuccessfullyLogin(String token) {
		this.token = token;
	}
	
	@Override
	public String getMessage() {
		return token;
	}

}
