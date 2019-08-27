package ufrn.sgl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.TenderDaoInterface;
import ufrn.sgl.model.Tender;
import ufrn.sgl.model.User;
import ufrn.sgl.util.HibernateUtil;

public class TenderDao implements TenderDaoInterface {

	@Override
	public long create(Tender tender) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.save(tender);
			transaction.commit();
			return tender.getId();
			
		} catch (Exception e) {
			if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Tender read(long id) {
		Transaction transaction = null;
		Tender tender = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an instructor object
			tender = session.get(Tender.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tender;
	}

	@Override
	public void update(Tender tender) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(tender);
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
			Tender tender = session.get(Tender.class, id);
			if (tender != null) {
				session.delete(tender);
				System.out.println("tender is deleted");
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
	public List<Tender> list() {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = "FROM Tender";
	        Query query = session.createQuery(hql);

	        List<?> results = query.getResultList();
	       
	        // commit transaction
	        transaction.commit();
	        
	        if ( results != null && !results.isEmpty() ) {
				@SuppressWarnings("unchecked")
				ArrayList<Tender> results2 = (ArrayList<Tender>) results;
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
