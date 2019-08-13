package ufrn.sgl.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.model.Bidding;
import ufrn.sgl.util.HibernateUtil;

public class BiddingDao {

	public void save(Bidding bidding) {
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
	
}
