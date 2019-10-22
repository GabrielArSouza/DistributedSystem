package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.messages.Message;

public class RegistrationSuccessfully extends Message{

	private static final long serialVersionUID = -3664846015687357763L;

	@Override
	public String getMessage() {
		return "Success";
	}

}
