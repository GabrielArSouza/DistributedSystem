package ufrn.sgl.messages.protocol.update;

import ufrn.sgl.model.Bidding;

public class RequestBiddingUpdate extends RequestUpdate {

	private static final long serialVersionUID = 3658084084685553770L;

	private final Bidding bidding;
		
	public RequestBiddingUpdate(Bidding bidding) {
		super();
		this.bidding = bidding;
	}

	public Bidding getBidding() {
		return bidding;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
