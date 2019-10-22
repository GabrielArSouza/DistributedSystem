package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.User;

public class RequestBiddingRegistration extends RequestRegistration{


	private static final long serialVersionUID = -7180446579802639969L;
	private final Bidding bidding;
	private String token;
	private User user;
	
	public RequestBiddingRegistration( 
			Bidding bidding,
			String token )
	{
		this.bidding = bidding;
		this.token = token;
	}
	
	public RequestBiddingRegistration(Bidding bidding, User user) {
		this.bidding = bidding;
		this.user = user;
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
	
	public User getUser () {return this.user;}

}
