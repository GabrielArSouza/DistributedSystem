package ufrn.sgl.messages.protocol.list;

public class RequestTenderList extends RequestList {

	private static final long serialVersionUID = -3760204004054072523L;

	private final String token;
	
	public RequestTenderList(String token) {
		super();
		this.token = token;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToken() {
		return token;
	}

}
