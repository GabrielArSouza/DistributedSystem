package ufrn.sgl.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.CompanyDaoInterface;
import ufrn.sgl.model.Company;
import ufrn.sgl.util.HibernateUtil;

public class CompanyDao implements CompanyDaoInterface{

	
	private void save(Company company) {
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

	@Override
	public void create(Company company) {
		this.save(company);
		// TODO
		
	}

	@Override
	public Company read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub
		
	}
	
}
