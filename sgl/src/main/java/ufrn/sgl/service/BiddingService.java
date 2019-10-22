package ufrn.sgl.service;

import java.util.List;

import ufrn.sgl.dao.BiddingDao;
import ufrn.sgl.dao.interfaces.BiddingDaoInterface;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.service.interfaces.BiddingServiceInterface;

public class BiddingService implements BiddingServiceInterface{

	private BiddingDaoInterface dao = new BiddingDao();
	
	@Override
	public long create(Bidding bidding) {
		return dao.create(bidding);
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
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public Bidding read(Bidding bidding) {
		return dao.read(bidding);
	}

	@Override
	public List<Bidding> list() {
		return dao.list();
	}
	
}
