package ufrn.sgl.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ufrn.sgl.model.Address;
import ufrn.sgl.util.HibernateUtil;

public class AddressDao{

	
	public void save ( Address address ) {
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(address);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) { transaction.rollback(); }
			e.printStackTrace();
		}
	}
	
	public void insert () {
		
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
            e.printStackTrace();
        }
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
