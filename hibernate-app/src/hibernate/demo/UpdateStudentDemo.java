package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	
	public static void main(String[] args) {
		
		//session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting student with Id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			myStudent.setFirstName("Amanda");
			
			//commit transaction
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} catch (Exception e) {
			factory.close();
		}
								
	}

}
