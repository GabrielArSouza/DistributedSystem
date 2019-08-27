package ufrn.sgl.messages.protocol.update;

import ufrn.sgl.model.User;

public class RequestUserUpdate extends RequestUpdate{

	private static final long serialVersionUID = 5280182055466535377L;

	private final User user;


	public RequestUserUpdate(User user) {
		super();
		this.user = user;
	}
	
	
	public User getUser() {
		return user;
	}


	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
