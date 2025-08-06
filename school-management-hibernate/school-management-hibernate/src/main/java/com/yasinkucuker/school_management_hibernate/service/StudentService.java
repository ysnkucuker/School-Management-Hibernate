package com.yasinkucuker.school_management_hibernate.service;

import com.yasinkucuker.school_management_hibernate.model.Course;
import com.yasinkucuker.school_management_hibernate.model.Student;
import com.yasinkucuker.school_management_hibernate.repository.StudentRepository;
import com.yasinkucuker.school_management_hibernate.utils.EntityManagerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;

public class StudentService implements StudentRepository {
    EntityManager em = EntityManagerUtils.getEntityManager("mysql-pu");

    @Override
    public Set<Course> findAllCourseOfStudentByID(long id) {
        TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
        q.setParameter("id", id);
        Student student = q.getSingleResult(); // Lazy loading
        return student.getCourses();
    }
    @Override
    public List<Student> findAll() {
        if(!em.isOpen()){
            em =EntityManagerUtils.getEntityManager("mysql-pu");
        }
        return em.createQuery("FROM Student", Student.class).getResultList();
    }
    @Override
    public Student findById(long id) {
        if(!em.isOpen()){
            em = EntityManagerUtils.getEntityManager("mysql-pu");
        }
        Student student = em.find(Student.class, id);
        return student;
    }
    @Override
    public void saveDatabase(Student object) {
        try{
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            System.out.println("Student saved");
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }
    @Override
    public void deleteFromDatabase(Student object) {
        try{
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
            System.out.println("Stundent deleted");
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }
    @Override
    public void deleteFromDatabase(int id) {

    }

    @Override
    public void updateOnDatabase(Student object) {

    }
}
