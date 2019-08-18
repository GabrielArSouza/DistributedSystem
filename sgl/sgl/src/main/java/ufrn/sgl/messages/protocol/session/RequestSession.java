package ufrn.sgl.messages.protocol.session;

import ufrn.sgl.messages.Message;
import ufrn.sgl.model.User;

public class RequestSession extends Message{

	private static final long serialVersionUID = 8698971442948598285L;
	private final User user;
	
	public RequestSession( User user ) {
		this.user = user;
	}
	
	@Override
	public String getMessage() {
		return "Request session";
	}
	
	public User getUser () {
		return this.user;
	}

}
