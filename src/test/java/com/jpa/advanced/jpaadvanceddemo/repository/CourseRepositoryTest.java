package com.jpa.advanced.jpaadvanceddemo.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.advanced.jpaadvanceddemo.JpaAdvancedDemoApplication;
import com.jpa.advanced.jpaadvanceddemo.entity.Course;
import com.jpa.advanced.jpaadvanceddemo.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaAdvancedDemoApplication.class)
public class CourseRepositoryTest {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void findById_test_basic() {
		Course course= courseRepository.findById(10001L);
		assertEquals("English", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void deleteById_test_basic() {
		courseRepository.deleteById(10001L);
		assertNull(courseRepository.findById(10001L));
	}
	
	@Test
	@DirtiesContext
	public void save_test_basic() {
		Course course= courseRepository.findById(10003L);
		course.setName("Chemistry");
		courseRepository.save(course);
		
		Course course1= courseRepository.findById(10003L);
		assertEquals("Chemistry", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEM_basic() {
		courseRepository.playWithEntityManager();
	}
	
	@Test
	@Transactional
	public void retrieveReviewsFromCourse() {
		//OneToMany Mapping -- Lazy fetching is active by default
		Course course= courseRepository.findById(10001L);
		logger.info("course.getReviews() -> {}",course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseFromReview() {
		//ManyToOne Mapping -- Eager fetching is active by default
		Review review= em.find(Review.class, 50001L);
		logger.info("review.getCourse() -> {}",review.getCourse());
	}
	
	@Test
	@Transactional
	//FirstLevelCache works because of the Transactional annotation
	//Only one DB query is fired
	public void firstLevelCacheDemo() {
		Course course = em.find(Course.class, 10001L);
		logger.info("firstLevelCacheDemo ; findCourseById() -> {}",course);
		
		Course course1 = em.find(Course.class, 10001L);
		logger.info("firstLevelCacheDemo : findCourseByIdAgain() -> {}",course1);
	}
	
	@Test
	//FirstLevelCache does not work because of the Transactional annotation is missing & so no PersistenceContext
	//Two DB queries are fired
	public void firstLevelCacheDemoNotWorking() {
		Course course = em.find(Course.class, 10001L);
		logger.info("firstLevelCacheDemo ; findCourseById() -> {}",course);
		
		Course course1 = em.find(Course.class, 10001L);
		logger.info("firstLevelCacheDemo : findCourseByIdAgain() -> {}",course1);
	}

}
