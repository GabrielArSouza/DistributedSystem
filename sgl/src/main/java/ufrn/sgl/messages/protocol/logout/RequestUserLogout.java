package ufrn.sgl.messages.protocol.logout;

import ufrn.sgl.model.User;

public class RequestUserLogout extends RequestLogout {

	private static final long serialVersionUID = 4514604454500413176L;

	private String token;
	private User user;
	
	public RequestUserLogout( String token ) {
		this.token = token;
		user = new User();
	}
	
	public RequestUserLogout(User user) {
		this.user = user;
	}
	
	@Override
	public String getMessage() {
		return null;
	}

	public String getUserSession() {
		return token;
	}
	
	public User getUser() {
		return user;
	}
	

}
