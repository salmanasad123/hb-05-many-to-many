package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForSusanDemo {

    public static void main(String[] args) {


        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)  // tell hibernate about our entity classes
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            // start a session
            session.beginTransaction();

            // find the student susan from database
            int studentId = 2;
            Student student = session.get(Student.class, studentId);

            System.out.println("Loaded Student: " + student);
            System.out.println("Courses: " + student.getCourses());
            
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
