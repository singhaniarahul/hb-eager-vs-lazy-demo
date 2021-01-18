package com.rahul.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rahul.hibernate.demo.entity.Course;
import com.rahul.hibernate.demo.entity.Instructor;
import com.rahul.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			Course firstCourse = new Course("Air Guitar! The Ultimate Guide");
			Course secondCourse = new Course("What If Hulk Was Made Of Sherbet?");
			
			instructor.add(firstCourse);
			instructor.add(secondCourse);
			
			session.save(firstCourse);
			session.save(secondCourse);
			
			session.getTransaction().commit();

	}catch(Exception exception) {
		exception.printStackTrace();
	}finally {
		session.close();
		sessionFactory.close();
	}

  }
}
