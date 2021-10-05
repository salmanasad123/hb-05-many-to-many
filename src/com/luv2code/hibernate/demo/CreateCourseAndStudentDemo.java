package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

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

            // create course object
            Course course = new Course("Pacman - how to score one million points");

            // save the course, and we have cascade Type ALL so it will save the course and reviews
            // saving course object to database, hibernate inserts items into database
            System.out.println("\nSaving the course...");
            session.save(course);
            System.out.println("Saved the course: " + course);

            // create the students
            Student student1 = new Student("Chad", "Darby", "chad@luv2code.com");
            Student student2 = new Student("Susan", "Public", "susan@luv2code.com");

            // add the students to the course
            course.addStudent(student1);
            course.addStudent(student2);

            // save the student
            System.out.println("\nSaving students...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved the students: " + course.getStudents());

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
