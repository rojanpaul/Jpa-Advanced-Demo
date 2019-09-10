package com.jpa.advanced.jpaadvanceddemo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.advanced.jpaadvanceddemo.entity.Course;
import com.jpa.advanced.jpaadvanceddemo.entity.Passport;
import com.jpa.advanced.jpaadvanceddemo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	@Autowired
//	@PersistenceContext
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student student= findById(id);
		if(null != student)
			em.remove(student);
	}
	
	public void save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		}else {
			em.merge(student);
		}
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123121");
		em.persist(passport);
		Student student = new Student("Seban");
		student.setPassport(passport);
		em.persist(student);
	}
	
//	@Transactional// When transactional added Persistence Context created automatically for that method. Else performed for each method.
	public void retriveAndUpdateStudentAndPassport() {
		/**
		 * Persistence Context created at the begining of transaction and deleted at the end of transaction.
		 */
		//Retrieve Student details
		Student student = em.find(Student.class, 20001L);
		//Persistence Context(Student)
		
		//Retreive Passport details
		Passport passport = student.getPassport();// Need session for retriving details of passport from student. 
		//Session means Persistence Context
		//Persistence Context(Student, Passport)
		
		//Update passport details
		passport.setNumber("Y478173");
		//Persistence Context(Student, Passport++)
		
		//Update student details
		student.setName("Michael");
		//Persistence Context(Student++, Passport++)
		// AT the end of method all transactions are sent to DB. Till then every operation is performed 
		//in the Persistence Context using EntityManager interface
		
//		Student stud = em.find(Student.class, 20001L);
//		logger.info("retrieveStudentAndPassportDetails -> {}",stud);
//		logger.info("PassportDetails -> {}",stud.getPassport());
	}
	
	public void insertStudentAndCourse_hardCoded() {
		Student student = new Student("Seban");
		Course course = new Course("Physiotheraphy");
		em.persist(course);
		em.persist(student);
		student.addCourse(course);
		course.addStudent(student);
	}
	
	public void insertStudentAndCourse(Student student,Course course) {	
		student.addCourse(course);
		course.addStudent(student);
		em.persist(course);
		em.persist(student);
		
	}
}
