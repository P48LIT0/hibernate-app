package hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class QueryStudentDemo {

	
	public static void main(String[] args) {
		
		//session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {

			//start transaction
			session.beginTransaction();

			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudents(theStudents);
			
			//query students where lastanmy = Kanapka
			theStudents = session.createQuery("from Student s where s.lastName = 'Kanapka'").getResultList();
			displayStudents(theStudents);
			
			//query OR
			theStudents = session.createQuery("from Student s where s.lastName = 'Kanapka' OR s.firstName = 'Kamil'").getResultList();
			displayStudents(theStudents);
			
			//query LIKE
			theStudents = session.createQuery("from Student s where s.email LIKE '%@xx'").getResultList();
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} catch (Exception e) {
			factory.close();
		}
								
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tmpStudent : theStudents)
			System.out.println(tmpStudent);
	}

}
