package com.yasinkucuker.school_management_hibernate;

import com.yasinkucuker.school_management_hibernate.model.*;
import com.yasinkucuker.school_management_hibernate.utils.EntityManagerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

import static com.yasinkucuker.school_management_hibernate.model.Gender.female;
import static com.yasinkucuker.school_management_hibernate.model.Gender.male;

@SpringBootApplication
public class SchoolManagementHibernateApplication {

	public static void main(String[] args) {
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
