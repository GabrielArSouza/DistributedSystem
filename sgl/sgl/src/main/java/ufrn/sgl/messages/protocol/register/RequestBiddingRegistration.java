package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.Bidding;

public class RequestBiddingRegistration extends RequestRegistration{

	private static final long serialVersionUID = 6856211036018954567L;

	private final Bidding bidding;
	
	public RequestBiddingRegistration( Bidding bidding) {
		this.bidding = bidding;
	}
	
	@Override
	public String getMessage() {
		return "create a new Bidding";
	}

	public Bidding getBidding() {
		return bidding;
	}
	
	
	
	
	
	

}
