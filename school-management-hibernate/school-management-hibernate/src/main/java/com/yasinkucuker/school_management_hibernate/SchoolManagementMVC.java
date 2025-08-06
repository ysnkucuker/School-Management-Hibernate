package com.yasinkucuker.school_management_hibernate;

import com.yasinkucuker.school_management_hibernate.controller.StudentController;
import com.yasinkucuker.school_management_hibernate.model.*;
import com.yasinkucuker.school_management_hibernate.utils.EntityManagerUtils;
import com.yasinkucuker.school_management_hibernate.utils.LogUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.yasinkucuker.school_management_hibernate.model.Gender.female;
import static com.yasinkucuker.school_management_hibernate.model.Gender.male;

public class SchoolManagementMVC {
    private static EntityManager entityManager = EntityManagerUtils.getEntityManager("mysql-pu");
    private static StudentController studenController = new StudentController();
    private static Student newStudent = new Student("Yaso Küço", LocalDate.of(1993, Month.DECEMBER, 22), "İzmir", Gender.male);

    public static void main(String[] args){
        if(checkData()){
            System.out.println("Test data not exist! Will be persisted....");
            persistTestData();
        }

        try{
            // executes test methods
            findAllStudent();
            persistNewStudent();
            updateStudent();
            findStudent();
            deleteStudent();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void findStudent(){
        LogUtils.logMethodStart(new Object(){
        }
                        .getClass()
                        .getEnclosingMethod()
                        .getName());
        System.out.println(studenController.findStudent(newStudent.getId()));
    }
    private static void deleteStudent(){
        LogUtils.logMethodStart(new Object(){
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        studenController.deleteStudent(newStudent);
    }
    private static void findAllStudent(){
        LogUtils.logMethodStart(new Object(){
        }
            .getClass()
            .getEnclosingMethod()
            .getName());
        List<Student> studentList = studenController.findAllStudent();
        for(int i=1; i<=studentList.size(); i++){
            System.out.println(i + " --> " + studentList.get(i-1));
        }
    }
    private static void persistNewStudent(){
        LogUtils.logMethodStart(new Object(){
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        studenController.saveStudent(newStudent);
        findAllStudent();
    }
    private static void updateStudent(){
        LogUtils.logMethodStart(new Object(){
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        newStudent.setAddress("New York");
        studenController.saveStudent(newStudent);
    }

    private static boolean checkData(){
        return entityManager.createQuery("from Student", Student.class).getResultList().size() == 0;
    }
    private static void persistTestData(){
        System.out.println("START.....");

        Student student1 = new Student("Yasin Küçüker", LocalDate.of(2001, Month.AUGUST,05),"İzmir", male);
        Student student2 = new Student("Yasin Büyüker", LocalDate.of(2000, Month.APRIL,11),"Karaman", male);
        Student student3 = new Student("Ayşe Ufaker", LocalDate.of(1995, Month.FEBRUARY,07),"Konya", female);

        Course course1 = new Course("Mathematics", 125623, 2.85f);
        Course course2 = new Course("English", 213523, 1.15f);
        Course course3 = new Course("Music", 21312, 0.88f);
        Course course4 = new Course("Art", 21312231, 0.12f);
        Course course5 = new Course("Photography", 643543, 5.18f);
        Course course6 = new Course("Social Science", 907655, 9.88f);
        Course course7 = new Course("Science", 875564, 10.12f);

        Instructor permanentInstructor1 = new PermanentResearcher("Fatma Falan", "İstanbul", "213123123", 100.0f);
        Instructor permanentInstructor2 = new PermanentResearcher("Koray Hoca", "İzmir", "2312313123", 99.9f);
        Instructor permanentInstructor3 = new PermanentResearcher("Falan Filan", "Konya", "231312312", 150.25f);

        Instructor visitingResearcher1 = new VisitingResearcher("Visiting Hoca", "Muğla", "514654646", 123.6f);
        Instructor visitingResearcher2 = new VisitingResearcher("Visiting Hoca1", "Maraş",  "12321312", 99.9f);

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);
        student1.getCourses().add(course6);

        student2.getCourses().add(course7);
        student2.getCourses().add(course4);


        course1.setInstructor(permanentInstructor1);
        course2.setInstructor(permanentInstructor2);
        course3.setInstructor(permanentInstructor1);
        course4.setInstructor(visitingResearcher1);
        course5.setInstructor(visitingResearcher1);
        course6.setInstructor(visitingResearcher2);
        course7.setInstructor(permanentInstructor1);

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysql-pu");

        try{
            entityManager.getTransaction().begin();

            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);

            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);
            entityManager.persist(course4);
            entityManager.persist(course5);
            entityManager.persist(course6);
            entityManager.persist(course7);

            entityManager.persist(permanentInstructor1);
            entityManager.persist(permanentInstructor2);
            entityManager.persist(permanentInstructor3);

            entityManager.persist(visitingResearcher1);
            entityManager.persist(visitingResearcher2);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            EntityManagerUtils.closeEntityManager(entityManager);
        }

        System.out.println("FINISH.....");
    }
}
