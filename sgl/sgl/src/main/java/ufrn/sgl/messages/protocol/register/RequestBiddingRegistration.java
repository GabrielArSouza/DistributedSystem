package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.Bidding;

public class RequestBiddingRegistration extends RequestRegistration{


	private static final long serialVersionUID = -7180446579802639969L;
	private final Bidding bidding;
	private final String token;
	
	public RequestBiddingRegistration( 
			Bidding bidding,
			String token )
	{
		this.bidding = bidding;
		this.token = token;
	}
	
	@Override
	public String getMessage() {
		return "create a new Bidding";
	}

	public Bidding getBidding() {
		return bidding;
	}

	public String getToken() {
		return token;
	}	

}
