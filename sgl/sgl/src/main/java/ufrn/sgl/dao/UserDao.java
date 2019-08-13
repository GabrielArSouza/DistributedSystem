package ufrn.sgl.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.model.User;
import ufrn.sgl.util.HibernateUtil;

public class UserDao {

	public void save ( User user ) {
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
//			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
		}
	}
	
	public void insert () {
		
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
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
}
