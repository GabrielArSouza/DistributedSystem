package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.Tender;

public class RequestTenderRegistration extends RequestRegistration{

	private static final long serialVersionUID = -7269226673774419160L;

	private final Tender tender;
	private final String token;
	
	public RequestTenderRegistration(Tender tender, String token) {
		super();
		this.tender = tender;
		this.token = token;
	}
	
	public Tender getTender() {
		return tender;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
