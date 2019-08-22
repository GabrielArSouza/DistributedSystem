package ufrn.sgl.service;

import ufrn.sgl.dao.BiddingDao;
import ufrn.sgl.dao.interfaces.BiddingDaoInterface;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.service.interfaces.BiddingServiceInterface;

public class BiddingService implements BiddingServiceInterface{

	private BiddingDaoInterface dao = new BiddingDao();
	
	@Override
	public void create(Bidding bidding) {
		dao.create(bidding);
	}

	@Override
	public Bidding read(long id) {
		return dao.read(id);
	}

	@Override
	public void update(Bidding bidding) {
		dao.update(bidding);
	}

	@Override
	public void delete(Bidding bidding) {
		dao.delete(bidding);
	}
	
}
