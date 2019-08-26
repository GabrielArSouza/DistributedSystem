package ufrn.sgl.messages.protocol.logout;

import ufrn.sgl.messages.Message;
import ufrn.sgl.model.User;

public class RequestUserLogout extends Message {

	private static final long serialVersionUID = -7970473100411301076L;
	private final User user;
	private final String token;
	
	public RequestUserLogout(User user, String token) {
		this.user = user;
		this.token = token;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}
	
	

}
