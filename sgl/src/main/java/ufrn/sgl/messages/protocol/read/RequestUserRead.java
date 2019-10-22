package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.User;

public class RequestUserRead extends RequestRead{

	private static final long serialVersionUID = -453372631458017634L;

	private final User user;
	
	public RequestUserRead( User user) {
		super(user.getId());
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
