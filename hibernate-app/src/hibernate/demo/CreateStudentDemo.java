package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class CreateStudentDemo {

	
	public static void main(String[] args) {
		
		//session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create student object
			System.out.println("Creating new student object..");
			Student tempStudent = new Student("Pawel", "Kowalski", "pawel.kowalski@xx");
			//start transaction
			session.beginTransaction();
			//save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} catch (Exception e) {
			factory.close();
		}
								
	}

}
