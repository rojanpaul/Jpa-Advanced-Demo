package com.jpa.advanced.jpaadvanceddemo.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
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
public class PerformmanceTuningTest {
	
	
	@Autowired
	EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	@Transactional
	public void creatingNPlusOneProblem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses",Course.class).getResultList();
		for(Course course : courses) {
			logger.info("Course -> {} , Students -> {} ",course,course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_EntityGraph() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> addSubgraph = entityGraph.addSubgraph("students");
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses",Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		for(Course course : courses) {
			logger.info("Course -> {} , Students -> {} ",course,course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses_join_fetch",Course.class)
				.getResultList();
		for(Course course : courses) {
			logger.info("Course -> {} , Students -> {} ",course,course.getStudents());
		}
	}
}
