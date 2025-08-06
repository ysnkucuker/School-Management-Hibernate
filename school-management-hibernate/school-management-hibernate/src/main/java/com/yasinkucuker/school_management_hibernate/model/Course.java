package com.yasinkucuker.school_management_hibernate.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseName;
    private int courseCode;
    private float creditScore;

    // Owner is student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> studentSet = new HashSet<>();

    @ManyToOne
    private Instructor instructor;

    public Course() {
    }

    public Course(String courseName, int courseCode, float creditScore) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public float getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(float creditScore) {
        this.creditScore = creditScore;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseCode == course.courseCode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                ", creditScore=" + creditScore +
                '}';
    }
}
