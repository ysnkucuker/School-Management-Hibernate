package com.yasinkucuker.school_management_hibernate.repository;

import com.yasinkucuker.school_management_hibernate.model.Course;
import com.yasinkucuker.school_management_hibernate.model.Student;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends CrudRepository<Student>{
    Set<Course> findAllCourseOfStudentByID(long id);
}
