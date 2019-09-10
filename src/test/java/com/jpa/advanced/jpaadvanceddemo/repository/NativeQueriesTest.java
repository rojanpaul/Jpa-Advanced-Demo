package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.advanced.jpaadvanceddemo.JpaAdvancedDemoApplication;
import com.jpa.advanced.jpaadvanceddemo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaAdvancedDemoApplication.class)
public class NativeQueriesTest {
	
	@Autowired
	EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void native_query_test_basic() {
		List list = em.createNativeQuery("select * from course").getResultList();
		logger.info("select * from course -> {}",list);
	}
	
	@Test
	public void native_query_test_typed() {
		List<Course> list = em.createNativeQuery("select * from course", Course.class).getResultList();
		logger.info("select * from course -> {}",list);
	}

	@Test
	public void native_query_test_parameters() {
		Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List<Course> list = query.getResultList();
		logger.info("select * from course where id = ? -> {}",list);
	}
	
	@Test
	public void native_query_test_parameters_object() {
		Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List<Course> list = query.getResultList();
		logger.info("select * from course where id = ? -> {}",list);
	}
	
	@Test
	@Transactional// To perform update/delete the method or class should be transactional
	public void native_query_test_to_update() {
		Query query = em.createNativeQuery("update course set last_updated_date = sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("update course set last_updated_date = sysdate() : noOfRowsUpdated -> {}",noOfRowsUpdated);
	}
	
}
