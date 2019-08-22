package ufrn.sgl.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.dao.interfaces.AddressDaoInterface;
import ufrn.sgl.model.Address;
import ufrn.sgl.model.User;
import ufrn.sgl.util.HibernateUtil;

public class AddressDao implements AddressDaoInterface{

	
	private void save ( Address address ) {
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.saveOrUpdate(address);
			transaction.commit();
		
		} catch (Exception e) {
		
			if (transaction != null) { transaction.rollback(); }
			System.out.println("Error: error to save a address in a Database Server");
		}
	}

	@Override
	public void create(Address address) {
		this.save(address);
		Transaction transaction = null;
    	try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "INSERT INTO Address (street, number, neighborhood, "
            		+ "city, state) SELECT street, number, neighborhood, city,"
            		+ "state FROM Address";
           
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error: error to create a new Address in the database server");
        }
		
	}

	@Override
	public Address read(long id) {
		Transaction transaction = null;
		Address selected = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get an user object
	        String hql = " FROM Address A WHERE A.id = :addressId ";
	        Query query = session.createQuery(hql);
	        query.setParameter("addressId", id);

	        List<?> results = query.getResultList();
	        
	        if (results != null && !results.isEmpty()) 
	        	selected = (Address) results.get(0);
          
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
	public void update(Address address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Address address) {
		// TODO Auto-generated method stub
		
	}
	

	
	/**
	 
	  public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // save the student object
            String hql = "UPDATE Student set firstName = :firstName " + "WHERE id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("firstName", student.getFirstName());
            query.setParameter("studentId", 1);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a student object
            Student student = session.get(Student.class, id);
            if (student != null) {
                String hql = "DELETE FROM Student " + "WHERE id = :studentId";
                Query query = session.createQuery(hql);
                query.setParameter("studentId", id);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);
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

    public Student getStudent(int id) {

        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // get an student object
            String hql = " FROM Student S WHERE S.id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                student = (Student) results.get(0);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    public List < Student > getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
	 
	 */
	
	
}
