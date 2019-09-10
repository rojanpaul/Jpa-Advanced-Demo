package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpa.advanced.jpaadvanceddemo.JpaAdvancedDemoApplication;
import com.jpa.advanced.jpaadvanceddemo.entity.Course;
import com.jpa.advanced.jpaadvanceddemo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaAdvancedDemoApplication.class)
public class JPQLTest {
	
	@Autowired
	EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void jpql_test_basic() {
		List list = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("FindAll createQuery -> {}",list);
	}
	
	@Test
	public void jpql_test_typed() {
	    TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses",Course.class);
		logger.info("FindAll typedQuery -> {}",typedQuery.getResultList());
	}
	
	@Test
	public void jpql_test_condition() {
	    TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses_with_condition",Course.class);
		logger.info("jpql_test_condition typedQuery -> {}",typedQuery.getResultList());
	}
	
	@Test
	public void jpql_courses_without_students() {
		logger.info("jpql_courses_without_students -> {}",
				em.createQuery("select c from Course c where c.students is empty",Course.class).getResultList());
		/**
		 * This is equivalent to SQL query 
		 * SELECT * FROM COURSE where id not in (select course_id from STUDENT_COURSE)
		 */		
	}
	
	@Test
	public void jpql_courses_with_atleast_two_students() {
		logger.info("jpql_courses_with_atleast_two_students -> {}",
				em.createQuery("select c from Course c where size(c.students) >= 2",Course.class).getResultList());
	}
	
	@Test
	public void jpql_courses_ordered_by_no_of_students() {
		logger.info("jpql_courses_ordered_by_no_of_students -> {}",
				em.createQuery("select c from Course c order by size(c.students) desc",Course.class).getResultList());
	}
	
	//JPQL :- like
	//BETWEEN 100 AND 200
	//is NULL
	//upper
	//lower
	//length, trim
	
	@Test	
	public void jpql_students_with_certain_passport_pattern() {
		logger.info("jpql_students_with_certain_passport_pattern -> {}",
				em.createQuery("select s from Student s where s.passport.number like '%123%'",Student.class).getResultList());
	}
	
	//JOIN :- select c,s from Course c JOIN c.students s
	//LEFT JOIN :- select c,s from Course c LEFT JOIN c.students s
	//CROSS JOIN :- select c,s from Course c, Student s 
	// No relation.. Returns all combinations possible 3*3 = 9 rows after cross join.
	@Test	
	public void jpql_join() {
		List<Object[]> resultSet = em.createQuery("select c,s from Course c JOIN c.students s").getResultList();
		logger.info("jpql_join size -> {}",	resultSet.size());
		for(Object[] result : resultSet) {
			logger.info("Course {}",(Course)result[0]);
			logger.info("Student {}",(Student)result[1]);
		}
	}
	
	@Test	
	public void jpql_left_join() {
		List<Object[]> resultSet = em.createQuery("select c,s from Course c LEFT JOIN c.students s").getResultList();
		logger.info("jpql_join size -> {}",	resultSet.size());
		for(Object[] result : resultSet) {
			logger.info("Course {}",(Course)result[0]);
			logger.info("Student {}",(Student)result[1]);
		}
	}
	
	@Test	
	public void jpql_cross_join() {
		List<Object[]> resultSet = em.createQuery("select c,s from Course c, Student s").getResultList();
		logger.info("jpql_join size -> {}",	resultSet.size());
		for(Object[] result : resultSet) {
			logger.info("Course {}",(Course)result[0]);
			logger.info("Student {}",(Student)result[1]);
		}
	}
}
