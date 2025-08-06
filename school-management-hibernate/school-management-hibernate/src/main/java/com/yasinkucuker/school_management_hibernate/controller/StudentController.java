package com.yasinkucuker.school_management_hibernate.controller;

import com.yasinkucuker.school_management_hibernate.model.Course;
import com.yasinkucuker.school_management_hibernate.model.Student;
import com.yasinkucuker.school_management_hibernate.service.StudentService;

import java.util.List;
import java.util.Set;

public class StudentController {
    private StudentService studentService = new StudentService();

    public List<Student> findAllStudent(){
        return studentService.findAll();
    }

    public Student findStudent(long id){
        return studentService.findById(id);
    }

    public void saveStudent(Student student){
        studentService.saveDatabase(student);
    }

    public void deleteStudent(Student student){
        studentService.deleteFromDatabase(student);
    }

    public Set<Course> findCourseOfStudentByID(long id){
        return studentService.findAllCourseOfStudentByID(id);
    }
}
