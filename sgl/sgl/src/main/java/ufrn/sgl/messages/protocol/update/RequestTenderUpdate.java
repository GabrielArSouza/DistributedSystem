package ufrn.sgl.messages.protocol.update;

import ufrn.sgl.model.Tender;

public class RequestTenderUpdate extends RequestUpdate {

	private static final long serialVersionUID = -6181085668374323675L;

	private final Tender tender;
	private final String token;
	
	public RequestTenderUpdate(Tender tender, String token) {
		super();
		this.tender = tender;
		this.token = token;
	}

	public Tender getTender() {
		return tender;
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
