package ufrn.sgl.messages.protocol.logout;

public class RequestUserLogout extends RequestLogout {

	private static final long serialVersionUID = 4514604454500413176L;

	private final String token;
	
	public RequestUserLogout( String token ) {
		this.token = token;
	}
	
	@Override
	public String getMessage() {
		return null;
	}

	public String getUserSession() {
		return token;
	}
	
	

}
