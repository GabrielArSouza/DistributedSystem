package ufrn.sgl.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.CompanySessionDaoInterface;
import ufrn.sgl.model.CompanySession;
import ufrn.sgl.util.HibernateUtil;

public class CompanySessionDao implements CompanySessionDaoInterface{

	@Override
	public long create(CompanySession companySession) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.save(companySession);
			transaction.commit();
			return companySession.getId();
			
		} catch (Exception e) {
			if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public CompanySession read(String token) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = "FROM CompanySession C WHERE C.token = :token";
	        Query query = session.createQuery(hql);
	        query.setParameter("token", token);

	        List<?> results = query.getResultList();
	       
	        // commit transaction
	        transaction.commit();
	        
	        if (results != null && !results.isEmpty()) {
	               return (CompanySession) results.get(0);
	        }else { return null; }
	        
		} catch (Exception e) {
			e.printStackTrace();
	        if (transaction != null) 
	        	transaction.rollback();
		}
		return null;
		
	}

	@Override
	public void update(CompanySession companySession) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(companySession);
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
			CompanySession companySession = session.get(CompanySession.class, id);
			if (companySession != null) {
				session.delete(companySession);
				System.out.println("Company Session is deleted");
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
