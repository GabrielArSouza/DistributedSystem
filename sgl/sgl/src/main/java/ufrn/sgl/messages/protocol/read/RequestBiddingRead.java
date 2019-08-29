package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Bidding;

public class RequestBiddingRead extends RequestRead{

	private static final long serialVersionUID = -8888192275661265870L;

	private final Bidding bidding;
	private final String token;

	public RequestBiddingRead( Bidding bidding, String token) {
		super(bidding.getId());
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
