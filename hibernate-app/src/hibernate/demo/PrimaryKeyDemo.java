package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 students objects
			System.out.println("Creating new 3 students object..");
			Student tempStudent = new Student("Pawel", "Kowalski", "pawel.kowalski@xx");
			Student tempStudent1 = new Student("Jan", "Bosj", "jan.boss@xx");
			Student tempStudent2 = new Student("Kamil", "Oppp", "kamil.oppp@xx");
			//start transaction
			session.beginTransaction();
			//save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent);
			session.save(tempStudent1);
			session.save(tempStudent2);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} catch (Exception e) {
			factory.close();
		}
								
	}

}
