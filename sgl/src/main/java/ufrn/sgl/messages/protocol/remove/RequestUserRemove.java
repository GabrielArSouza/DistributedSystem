package ufrn.sgl.messages.protocol.remove;

public class RequestUserRemove extends RequestRemove{

	private static final long serialVersionUID = -8819018464659766896L;

	private final long id;
	
	public RequestUserRemove(long id) {
		this.id = id;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getId() {
		return id;
	}


}
