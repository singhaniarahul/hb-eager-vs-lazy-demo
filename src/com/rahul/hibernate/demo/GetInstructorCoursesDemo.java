package com.rahul.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.hibernate.demo.entity.Course;
import com.rahul.hibernate.demo.entity.Instructor;
import com.rahul.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

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
			
			Instructor instructor = session.get(Instructor.class, 1);
			
			System.out.println("Instructor: " + instructor);
			
			System.out.println("Courses: " + instructor.getCourseList());
			
			session.getTransaction().commit();

	}catch(Exception exception) {
		exception.printStackTrace();
	}finally {
		session.close();
		sessionFactory.close();
	}

  }
}
