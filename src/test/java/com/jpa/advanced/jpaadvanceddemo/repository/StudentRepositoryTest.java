package com.jpa.advanced.jpaadvanceddemo.repository;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.advanced.jpaadvanceddemo.JpaAdvancedDemoApplication;
import com.jpa.advanced.jpaadvanceddemo.entity.Address;
import com.jpa.advanced.jpaadvanceddemo.entity.Course;
import com.jpa.advanced.jpaadvanceddemo.entity.Passport;
import com.jpa.advanced.jpaadvanceddemo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaAdvancedDemoApplication.class)
public class StudentRepositoryTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepository;

	// Entity Manager : Session & session manager
	// Persistence Context
	// Transaction

	@Autowired
	EntityManager em;

	@Test
	public void retriveAndUpdateStudentAndPassport() {
		studentRepository.retriveAndUpdateStudentAndPassport();
	}

	@Test
	@Transactional // For enabling lazy fetching
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("retrieveStudentAndPassportDetails -> {}", student);
		// Should be in the session scope for passport details to be fetched.
		// So need to make this entire method in transactional scope.
		logger.info("PassportDetails -> {}", student.getPassport());
	}
	
	@Test
	@Transactional // For enabling lazy fetching
	public void retrievePassportAndAssociatedStudentDetails() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("retrievePassportAndAssociatedStudentDetails -> {}", passport);
		// Should be in the session scope for passport details to be fetched.
		// So need to make this entire method in transactional scope.
		logger.info("StudentDetails -> {}", passport.getStudent());
	}

	@Test
	@Transactional // For enabling lazy fetching
	public void retrieveStudentAndAllCourses() {
		Student student = em.find(Student.class, 20001L);
		logger.info("retrieveStudentAndAllCourses() -> {}", student);
		// Should be in the session scope for passport details to be fetched.
		// So need to make this entire method in transactional scope.
		logger.info("AllCourses of Student -> {}", student.getCourses());
	}
	
	@Test
	@Transactional // For enabling lazy fetching
	public void retrieveCourseAndAllStudents() {
		Course course = em.find(Course.class, 10001L);
		logger.info("retrieveCourseAndAllStudents() -> {}", course);
		// Should be in the session scope for passport details to be fetched.
		// So need to make this entire method in transactional scope.
		logger.info("AllStudents of Course -> {}", course.getStudents());
	}
	
	@Test
	@Transactional // For enabling lazy fetching
	public void retrieveStudentAndAllDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("retrieveStudentAndPassportDetails -> {}", student);
		student.setAddress(new Address("line1", "line2", "city"));
		em.flush();
		// Should be in the session scope for passport details to be fetched.
		// So need to make this entire method in transactional scope.
		logger.info("PassportDetails -> {}", student.getPassport());
		logger.info("AddressDetails -> {}", student.getAddress());
	}
}
