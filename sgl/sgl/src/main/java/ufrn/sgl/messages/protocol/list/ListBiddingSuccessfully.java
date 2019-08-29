package ufrn.sgl.messages.protocol.list;

import java.util.List;

import ufrn.sgl.model.Bidding;

public class ListBiddingSuccessfully extends ListSuccessfully{

	private static final long serialVersionUID = -2485572440525082513L;

	private final List<Bidding> biddings;
	
	public ListBiddingSuccessfully(List<Bidding> biddings) {
		super();
		this.biddings = biddings;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Bidding> getBiddings() {
		return biddings;
	}

}
