package ufrn.sgl.messages.protocol.list;

public class RequestBiddingList extends RequestList{

	private static final long serialVersionUID = -696516590717769537L;

	private final String token;
	
	public RequestBiddingList(String token) {
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
