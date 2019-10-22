package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Tender;

public class RequestTenderRead extends RequestRead{

	private static final long serialVersionUID = 4479904492135974979L;

	private final Tender tender;
	private final String token;
	
	public RequestTenderRead(Tender tender, String token) {
		super(tender.getId());
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
