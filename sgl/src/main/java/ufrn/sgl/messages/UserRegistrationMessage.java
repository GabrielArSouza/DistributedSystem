package ufrn.sgl.messages;

import ufrn.sgl.model.User;

public class UserRegistrationMessage extends Message {

	private static final long serialVersionUID = 3L;
	
	private final User user;
	
	public UserRegistrationMessage(User user) 
	{ this.user = user; }

	public User getUser () {
		return this.user;
	}
	
	@Override
	public String getMessage() {
		
		String msg = "{ \"type\": \"User Registration\",\n" 
				+ this.user.toString() + "\n}";
		
		return msg;
	}
	
	
	
	
}
