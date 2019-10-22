package ufrn.sgl.messages.protocol.remove;

public class RequestTenderRemove extends RequestRemove {

	private static final long serialVersionUID = 5163506073788990709L;

	private final long id;
	private final String token;
		
	public RequestTenderRemove(long id, String token) {
		super();
		this.id = id;
		this.token = token;
	}

	public long getId() {
		return id;
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
