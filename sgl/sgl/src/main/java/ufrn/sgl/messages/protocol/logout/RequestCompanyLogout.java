package ufrn.sgl.messages.protocol.logout;

public class RequestCompanyLogout extends RequestLogout{

	private static final long serialVersionUID = -7201561474696139018L;

	private final String token;
	
	public RequestCompanyLogout(String token) {
		this.token = token;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSession() {
		return token;
	}
	
	

}
