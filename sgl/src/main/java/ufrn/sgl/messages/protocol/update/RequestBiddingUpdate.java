package ufrn.sgl.messages.protocol.update;

import ufrn.sgl.model.Bidding;

public class RequestBiddingUpdate extends RequestUpdate {

	private static final long serialVersionUID = 3658084084685553770L;

	private final Bidding bidding;
	private final String token;
		
	public RequestBiddingUpdate(Bidding bidding, String token) {
		super();
		this.bidding = bidding;
		this.token = token;
	}

	public Bidding getBidding() {
		return bidding;
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
