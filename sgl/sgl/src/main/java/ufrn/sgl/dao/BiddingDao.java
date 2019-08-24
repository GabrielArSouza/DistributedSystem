package ufrn.sgl.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.BiddingDaoInterface;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.util.HibernateUtil;

public class BiddingDao implements BiddingDaoInterface {

	private void save(Bidding bidding) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			session.save(bidding);
			
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public long create(Bidding bidding) {
		this.save(bidding);
		return bidding.getId();
		//TODO
		
	}

	@Override
	public Bidding read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Bidding bidding) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bidding bidding) {
		// TODO Auto-generated method stub
		
	}
	
}
