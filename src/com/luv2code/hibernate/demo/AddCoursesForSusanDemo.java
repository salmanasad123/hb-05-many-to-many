package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForSusanDemo {

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

            // create more courses
            Course course1 = new Course("Java - the ultimate guide");
            Course course2 = new Course("Rubik's Cube - how to speed cube");

            // add students to those courses
            course1.addStudent(student);
            course2.addStudent(student);

            // save courses
            System.out.println("Saving the courses...");
            session.save(course1);
            session.save(course2);

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
