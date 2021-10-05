package com.luv2code.hibernate.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // set up the relationship /mapping between instructor detail and the instructor
    // @OneToOne is used for 1-1 relation
    // cascade Type is set to ALL so all operations applied on instructor will cascade to instuctor detail like
    // delete, fetch, persisting (is instructor is saved, instructor detail will also be saved)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id") // this is the column to join on
    private InstructorDetail instructorDetail;

    // oneToMany because an instructor can teach many courses
    // mapped by refers to the instructor property in the course class because the course class has an
    // instructor field so we are referring to that field
    // setting fetch type to eager this will load the instructor and also load the courses at the same time,
    // kind of like one stop deal
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor() {
    }

    // we removed instructor detail we want to manually pass it in not via constructor
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }

    // add convenience method for bi-directional relationship
    public void addCourse(Course course) {
        if (course == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);

        // setup bi-directional relationship, hey course this is your new instructor
        course.setInstructor(this);
    }
}
