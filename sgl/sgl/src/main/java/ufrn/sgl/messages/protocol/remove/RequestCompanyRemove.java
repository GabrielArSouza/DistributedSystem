package ufrn.sgl.messages.protocol.remove;

public class RequestCompanyRemove extends RequestRemove {

	private static final long serialVersionUID = 1043166721699065690L;

	private final long id;
	
	public RequestCompanyRemove(long id) {
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
