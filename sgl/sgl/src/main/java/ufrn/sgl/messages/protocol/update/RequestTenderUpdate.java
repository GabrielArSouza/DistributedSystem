package ufrn.sgl.messages.protocol.update;

import ufrn.sgl.model.Tender;

public class RequestTenderUpdate extends RequestUpdate {

	private static final long serialVersionUID = -6181085668374323675L;

	private final Tender tender;
	
	public RequestTenderUpdate(Tender tender) {
		super();
		this.tender = tender;
	}

	public Tender getTender() {
		return tender;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
