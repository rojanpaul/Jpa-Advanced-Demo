package com.jpa.advanced.jpaadvanceddemo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.advanced.jpaadvanceddemo.entity.Course;
import com.jpa.advanced.jpaadvanceddemo.entity.Employee;
import com.jpa.advanced.jpaadvanceddemo.entity.FullTimeEmployee;
import com.jpa.advanced.jpaadvanceddemo.entity.PartTimeEmployee;
import com.jpa.advanced.jpaadvanceddemo.entity.Review;
import com.jpa.advanced.jpaadvanceddemo.entity.Student;
import com.jpa.advanced.jpaadvanceddemo.repository.CourseRepository;
import com.jpa.advanced.jpaadvanceddemo.repository.EmployeeRepository;
import com.jpa.advanced.jpaadvanceddemo.repository.StudentRepository;

@SpringBootApplication
public class JpaAdvancedDemoApplication implements CommandLineRunner{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaAdvancedDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		logger.info("{}",courseRepository.findById(10001L));
//		courseRepository.deleteById(10002L);
//		courseRepository.save(new Course("Biology"));
//		courseRepository.playWithEntityManager();
//		studentRepository.saveStudentWithPassport();
//		List<Review> reviews = new ArrayList<Review>();
//		reviews.add(new Review("3", "Good"));
//		reviews.add(new Review("4", "Good with lots of Hands On"));
//		courseRepository.addReviewsToCourseGeneric(10003L, reviews);
//		courseRepository.addReviewsToCourse();//ManyTOOne Testing
//		Student student = new Student("Seban");
//		Course course = new Course("Physiotheraphy");
//		studentRepository.insertStudentAndCourse(student, course);
//		employeeRepository.insert(new PartTimeEmployee("Jill",new BigDecimal(50)));
//		employeeRepository.insert(new FullTimeEmployee("Jack",new BigDecimal(10000)));
//		logger.info("{}",employeeRepository.retrieveAllPartTimeEmployees());
//		logger.info("{}",employeeRepository.retrieveAllFullTimeEmployees());
	}

}
