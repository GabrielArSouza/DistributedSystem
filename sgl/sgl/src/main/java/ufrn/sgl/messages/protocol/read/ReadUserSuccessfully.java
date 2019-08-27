package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.User;

public class ReadUserSuccessfully extends ReadSuccessfully{

	private static final long serialVersionUID = -8783776239768180694L;

	private final User user;
	
	public ReadUserSuccessfully(User user) {
		this.user = user;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUser() {
		return user;
	}
	
	

	
	
}
