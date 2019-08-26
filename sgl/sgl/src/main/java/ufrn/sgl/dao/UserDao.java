package ufrn.sgl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.UserDaoInterface;
import ufrn.sgl.model.User;
import ufrn.sgl.util.HibernateUtil;

public class UserDao implements UserDaoInterface {
	
	@Override
	public long create ( User user ) {
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			return user.getId();
			
		} catch (Exception e) {
			if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public User read(long id) {
		Transaction transaction = null;
		User userGet = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an instructor object
			userGet = session.get(User.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return userGet;
	}
	
	@Override
	public void update(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(user);
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
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
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

	@Override
	public List<User> list() {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = "FROM User";
	        Query query = session.createQuery(hql);

	        List<?> results = query.getResultList();
	       
	        // commit transaction
	        transaction.commit();
	        
	        if ( results != null && !results.isEmpty() ) {
				@SuppressWarnings("unchecked")
				ArrayList<User> results2 = (ArrayList<User>) results;
				return results2;
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
	        if (transaction != null) 
	        	transaction.rollback();
		}
		return null;
		

	}

	@Override
	public User read(User user) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = "FROM User U WHERE U.email = :email and U.password = :password";
	        Query query = session.createQuery(hql);
	        query.setParameter("email", user.getEmail());
	        query.setParameter("password", user.getPassword());
	        
	        List<?> results = query.getResultList();
	       
	        // commit transaction
	        transaction.commit();
	        
	        if (results != null && !results.isEmpty()) {
               return (User) results.get(0);
            }else { return null; }
	        
		} catch (Exception e) {
			e.printStackTrace();
	        if (transaction != null) 
	        	transaction.rollback();
		}
		return null;
	}
	
}
