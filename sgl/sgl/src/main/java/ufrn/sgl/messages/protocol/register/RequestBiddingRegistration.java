package ufrn.sgl.messages.protocol.register;

import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.User;

public class RequestBiddingRegistration extends RequestRegistration{

	private static final long serialVersionUID = 6856211036018954567L;

	private final Bidding bidding;
	private final User user;
	private final String token;
	
	public RequestBiddingRegistration( 
			Bidding bidding,
			User user, String token )
	{
		this.bidding = bidding;
		this.user = user;
		this.token = token;
	}
	
	@Override
	public String getMessage() {
		return "create a new Bidding";
	}

	public Bidding getBidding() {
		return bidding;
	}

	public User getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}	

}
