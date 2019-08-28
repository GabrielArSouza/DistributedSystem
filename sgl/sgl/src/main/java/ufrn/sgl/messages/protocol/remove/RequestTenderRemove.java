package ufrn.sgl.messages.protocol.remove;

public class RequestTenderRemove extends RequestRemove {

	private static final long serialVersionUID = 5163506073788990709L;

	private final long id;
		
	public RequestTenderRemove(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
