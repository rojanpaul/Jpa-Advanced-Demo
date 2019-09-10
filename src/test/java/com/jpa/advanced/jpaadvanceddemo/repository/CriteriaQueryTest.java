package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpa.advanced.jpaadvanceddemo.JpaAdvancedDemoApplication;
import com.jpa.advanced.jpaadvanceddemo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaAdvancedDemoApplication.class)
public class CriteriaQueryTest {
	
	@Autowired
	EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void criteria_query_all_courses() {
		//select c from Course c
		//1. Use criteria builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		//3. Define predicates etc using criteria builder
		//4. Add predicates etc to criteria query
		
		//5. Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		logger.info("FindAll criteria_query_test_basic -> {}",resultSet);
	}
	
	@Test
	public void criteria_query_where_predicate() {
		//select c from Course c where name like '%2.0%'
		//1. Use criteria builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define predicates etc using criteria builder
		Predicate likePredicate = cb.like(courseRoot.get("name"), "%2.0%");
		
		//4. Add predicates etc to criteria query
		cq.where(likePredicate);
		
		//5. Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		logger.info("criteria_query_where_predicate -> {}",resultSet);
	}
	
	@Test
	public void criteria_query_all_courses_without_students() {
		//select c from Course c where c.students is empty
		//1. Use criteria builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define predicates etc using criteria builder
		Predicate isEmptyPredicate = cb.isEmpty(courseRoot.get("students"));
		
		//4. Add predicates etc to criteria query
		cq.where(isEmptyPredicate);
		
		//5. Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		logger.info("criteria_query_all_courses_without_students -> {}",resultSet);
	}
	
	@Test
	public void criteria_query_join() {
		//select c from Course c join c.students s
		//1. Use criteria builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define predicates etc using criteria builder
		
		
		//4. Add predicates etc to criteria query
		Join<Object, Object> join = courseRoot.join("students");
		
		//5. Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		logger.info("criteria_query_join -> {}",resultSet);
	}
	
	@Test
	public void criteria_query_left_join() {
		//select c from Course c left join c.students s
		//1. Use criteria builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define predicates etc using criteria builder
		
		
		//4. Add predicates etc to criteria query
		Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);
		
		//5. Build TypedQuery using entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		logger.info("criteria_query_left_join -> {}",resultSet);
	}
}
