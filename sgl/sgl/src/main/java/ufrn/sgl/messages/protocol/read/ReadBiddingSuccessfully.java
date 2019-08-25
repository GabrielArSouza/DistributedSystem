package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Bidding;

public class ReadBiddingSuccessfully extends ReadSuccessfully {

	private static final long serialVersionUID = 3140054153751653868L;

	private final Bidding bidding;
	
	public ReadBiddingSuccessfully(Bidding bidding) {
		this.bidding = bidding;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bidding getBidding() {
		return bidding;
	}
	
	

	
	
}
