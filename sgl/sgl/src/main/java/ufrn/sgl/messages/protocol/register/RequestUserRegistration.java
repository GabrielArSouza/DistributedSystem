package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.User;

public class RequestUserRegistration extends RequestRegistration {

	private static final long serialVersionUID = 990827195931841645L;

	private final User user;
	
	public RequestUserRegistration(User user) {
		this.user = user;
	}
	
	public User getUser () { return this.user; }
	
	@Override
	public String getMessage() {
		return "create new user";
	}

}
