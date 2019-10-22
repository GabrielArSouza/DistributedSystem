package ufrn.sgl.messages.protocol.read;


public class RequestUserReadForID extends RequestRead{

	private static final long serialVersionUID = -453372631458017634L;
	
	public RequestUserReadForID(long id) {
		super(id);
	}
	
	@Override
	public String getMessage() {
		return "read a user";
	}

	
	

}
