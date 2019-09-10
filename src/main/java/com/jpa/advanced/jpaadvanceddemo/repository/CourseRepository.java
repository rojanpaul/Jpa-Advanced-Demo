package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.advanced.jpaadvanceddemo.entity.Course;
import com.jpa.advanced.jpaadvanceddemo.entity.Review;
import com.jpa.advanced.jpaadvanceddemo.entity.ReviewRating;

@Repository
@Transactional
public class CourseRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
//	@PersistenceContext
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course= findById(id);
		if(null != course)
			em.remove(course);
	}
	
	public void save(Course course) {
		if(course.getId() == null) {
			em.persist(course);
		}else {
			em.merge(course);
		}
	}
	
	public void playWithEntityManager() {
//		Course course1 = new Course("Chemistry 1.0");
//		em.persist(course1);
//		Course course2 = new Course("Physics 1.0");
//		em.persist(course2);
//		Course course3 = new Course("Bio 1.0");
//		em.persist(course3);
//		em.flush();// the chnages made so far are saved to database.
//		
//		course3.setName("Bio 2.0");
//		em.refresh(course3);//Replace with original data in database using select query. Bio 2.0 update will be ignored. 
//		
//		course1.setName("Chemistry 2.0");	
//		em.detach(course1);//The object is ignored from tracking by EM once it is detached.
//		course1.setName("Chemistry 3.0");	
//		
//		em.clear();//clear all the chnages once this command is executed.
//		course2.setName("Physics 2.0");
		
		Course course1 = new Course("Chemistry 1.0");
		em.persist(course1);
		Course course2 = findById(10001L);
		course2.setName("Let US C");
		em.merge(course2);
		
	}

	public void addReviewsToCourse() {
		//Get Course
		Course course= findById(10003L);
		logger.info("course.getReviews() -> {}",course.getReviews());
		//Create Reviews
		Review review1 = new Review(ReviewRating.THREE, "Good");
		Review review2 = new Review(ReviewRating.FOUR, "Good with lots of Hands On");
		/**
		 * Setting up Many to One Relationship
		 */
		//Add reviews to Course
		course.addReview(review1);
		course.addReview(review2);
		//Add course to reviews
		review1.setCourse(course);
		review2.setCourse(course);
		//Save reviews
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsToCourseGeneric(Long courseId, List<Review> reviews) {
		//Get Course
		Course course= findById(courseId);
		logger.info("course.getReviews() -> {}",course.getReviews());
		for(Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}
	
}
