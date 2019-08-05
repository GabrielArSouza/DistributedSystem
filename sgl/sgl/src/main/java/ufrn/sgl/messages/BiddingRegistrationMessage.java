package ufrn.sgl.messages;

import ufrn.sgl.model.Bidding;

public class BiddingRegistrationMessage extends Message {

	private static final long serialVersionUID = -66333853925655251L;

	private final Bidding bidding;
	
	public BiddingRegistrationMessage(Bidding bidding) {
		super();
		this.bidding = bidding;
	}

	public Bidding getBidding () {
		return this.bidding;
	}

	@Override
	public String getMessage() {
		
		String msg = "{ \"type\": \"Bidding Registration\",\n"
				+ this.bidding.toString() + "\n}";
			
			return msg;
	}
	


}
