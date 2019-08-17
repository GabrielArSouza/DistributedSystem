package ufrn.sgl.messages.protocol.session;

import ufrn.sgl.messages.Message;

public class RequestSession extends Message{

	private static final long serialVersionUID = 8698971442948598285L;

	@Override
	public String getMessage() {
		return "Request session";
	}

}
