package ufrn.sgl.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.UserSessionDaoInterface;
import ufrn.sgl.model.UserSession;
import ufrn.sgl.util.HibernateUtil;

public class UserSessionDao implements UserSessionDaoInterface{

	@Override
	public long create(UserSession userSession) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.save(userSession);
			transaction.commit();
			return userSession.getId();
			
		} catch (Exception e) {
			if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public UserSession read(String token) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = "FROM UserSession U WHERE U.token = :token";
	        Query query = session.createQuery(hql);
	        query.setParameter("token", token);

	        List<?> results = query.getResultList();
	       
	        // commit transaction
	        transaction.commit();
	        
	        if (results != null && !results.isEmpty()) {
	               return (UserSession) results.get(0);
	        }else { return null; }
	        
		} catch (Exception e) {
			e.printStackTrace();
	        if (transaction != null) 
	        	transaction.rollback();
		}
		return null;
		
	}

	@Override
	public void update(UserSession userSession) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(userSession);
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
			UserSession userSession = session.get(UserSession.class, id);
			if (userSession != null) {
				session.delete(userSession);
				System.out.println("User is deleted");
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

}
