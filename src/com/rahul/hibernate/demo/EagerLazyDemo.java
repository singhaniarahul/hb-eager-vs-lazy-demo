package com.rahul.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.hibernate.demo.entity.Course;
import com.rahul.hibernate.demo.entity.Instructor;
import com.rahul.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Instructor instructor = session.get(Instructor.class, 2);
			
			System.out.println("MyCode: Instructor: " + instructor);
			System.out.println("MyCode: Courses: " + instructor.getCourseList());
			
			session.getTransaction().commit();
			session.close();
			
			System.out.println("Session Closed.\n\n");
			
			System.out.println("MyCode: Courses: " + instructor.getCourseList());

	}catch(Exception exception) {
		exception.printStackTrace();
	}finally {
		
		sessionFactory.close();
	}

  }
}
