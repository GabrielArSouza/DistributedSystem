package ufrn.sgl.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.model.Company;
import ufrn.sgl.util.HibernateUtil;

public class CompanyDao {

	
	public void save(Company company) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			session.save(company);
			
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
