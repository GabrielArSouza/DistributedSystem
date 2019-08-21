package ufrn.sgl.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.UserDaoInterface;
import ufrn.sgl.model.User;
import ufrn.sgl.util.HibernateUtil;

public class UserDao implements UserDaoInterface {

	private void save ( User user ) {
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			transaction.commit();
		} catch (Exception e) {
			//if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
		}
	}
	

	@Override
	public void create(User user) {
		System.out.println("message from user dao");
		this.save(user);
		
		Transaction transaction = null;
		try ( Session session = HibernateUtil.getSessionFactory().openSession() ){
			
			// start a transaction
			transaction = session.beginTransaction();
			
			String hgl = "INSERT INTO  User( jusrisctionalName, jurisdictionalCode,"
					+ "cnpj, email, addressId ) SELECT jusrisctionalName, "
					+ "jurisdictionalCode, cnpj, email, address FROM User";
		
			Query query = session.createQuery(hgl);
			int result = query.executeUpdate();
			
			System.out.println("Rows affected: " + result);
			
			// Commit transaction
			transaction.commit();
			
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			e.printStackTrace();
		}
		
	}

	@Override
	public User read(User user) {
		Transaction transaction = null;
		User selected = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = " FROM User U WHERE U.email = :userEmail and U.password = :userPassword";
	        Query query = session.createQuery(hql);
	        query.setParameter("userEmail", user.getEmail());
	        query.setParameter("userPassword", user.getPassword());
	        List<?> results = query.getResultList();
	        
	        if (results != null && !results.isEmpty()) 
	        	selected = (User) results.get(0);
          
	        // commit transaction
	        transaction.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
	        if (transaction != null) 
	        	transaction.rollback();
		}
		
		return selected;
	}
	
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}
	
}
