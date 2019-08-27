package ufrn.sgl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.BiddingDaoInterface;
import ufrn.sgl.model.Bidding;
import ufrn.sgl.model.User;
import ufrn.sgl.util.HibernateUtil;

public class BiddingDao implements BiddingDaoInterface {


	@Override
	public long create(Bidding bidding) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			session.save(bidding);
			
			// commit transaction
			transaction.commit();
			return bidding.getId();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public Bidding read(long id) {
		Transaction transaction = null;
		Bidding bidding = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an instructor object
			bidding = session.get(Bidding.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return bidding;
	}

	@Override
	public void update(Bidding bidding) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(bidding);
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
	public void delete(long id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a instructor object
			Bidding bidding = session.get(Bidding.class, id);
			if (bidding != null) {
				session.delete(bidding);
				System.out.println("Bidding is deleted");
			}

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
	public List<Bidding> list() {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = "FROM Bidding";
	        Query query = session.createQuery(hql);

	        List<?> results = query.getResultList();
	       
	        // commit transaction
	        transaction.commit();
	        
	        if ( results != null && !results.isEmpty() ) {
				@SuppressWarnings("unchecked")
				ArrayList<Bidding> results2 = (ArrayList<Bidding>) results;
				return results2;
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
	        if (transaction != null) 
	        	transaction.rollback();
		}
		return null;
		

	}
	
}
