package ufrn.sgl.messages.protocol.read;

import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.User;

public class RequestBiddingRead extends RequestRead{

	private static final long serialVersionUID = -8888192275661265870L;

	private final Bidding bidding;
	private String token;
	private User user;

	public RequestBiddingRead( Bidding bidding, String token) {
		super(bidding.getId());
		this.bidding = bidding;
		this.token = token;
	}
	
	public RequestBiddingRead(Bidding bidding, User user) {
		super(bidding.getId());
		this.bidding = bidding;
		this.user = user;
	}

	public Bidding getBidding() {
		return bidding;
	}

	public User getUser () {return user;} 
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToken() {
		return token;
	}

}
