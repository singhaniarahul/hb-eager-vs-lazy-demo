package com.rahul.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.rahul.hibernate.demo.entity.Course;
import com.rahul.hibernate.demo.entity.Instructor;
import com.rahul.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			Query<Instructor> query = 
					session.createQuery("Select i from Instructor i "
							+"JOIN FETCH i.courseList "
							+ "WHERE i.id=:theInstructorId"
							, Instructor.class);
			
			query.setParameter("theInstructorId", 2);
			
			Instructor instructor = query.getSingleResult();
			
			System.out.println("MyCode: Instructor: " + instructor);
			
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
