package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpa.advanced.jpaadvanceddemo.JpaAdvancedDemoApplication;
import com.jpa.advanced.jpaadvanceddemo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaAdvancedDemoApplication.class)
public class SpringDataCourseRepositoryTest {
	
	@Autowired
	SpringDataCourseRepository springDataCourseRepository;	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void findById_test_basic() {
		Optional<Course> courseOptional = springDataCourseRepository.findById(10001L);
		logger.info("courseOptional.isPresent() --> {}",courseOptional.isPresent());
		logger.info("findById_test_basic --> {}",courseOptional.get());
	}
	
	@Test
	public void findAll_test_basic() {
		List<Course> courseList = springDataCourseRepository.findAll();
		logger.info("findAll_test_basic --> {}",courseList);
	}
	
	@Test
	public void save_test_basic() {
		Course course  = springDataCourseRepository.save(new Course("History 1.0"));
		logger.info("created Course --> {}",course);
		course.setName("History 2.0");
		Course updatedCourse = springDataCourseRepository.save(course);
		logger.info("updatedCourse --> {}",updatedCourse);
	}
	
	@Test
	public void findAll_and_sort_test_basic() {
		Sort sortDesc = new Sort(Direction.DESC, "name");
		List<Course> courseListDesc = springDataCourseRepository.findAll(sortDesc);
		logger.info("findAll_sortDesc --> {}",courseListDesc);
		
		Sort sort = new Sort(Direction.ASC, "name");
		List<Course> courseList = springDataCourseRepository.findAll(sort);
		logger.info("findAll_Direction.ASC --> {}",courseList);
	}
	
	@Test
	public void pagination_spring_data_repo() {
		
//		PageRequest pageRequest = PageRequest.of(0, 4);
//		Page<Course> firstPage = springDataCourseRepository.findAll(pageRequest);
//		logger.info("findAll_firstPage --> {}",firstPage.getContent());
		
//		Pageable secondPageable = firstPage.nextPageable();
//		Page<Course> secondPage = springDataCourseRepository.findAll(secondPageable);
//		logger.info("findAll_secondPage --> {}",secondPage.getContent());
//		
//		Page<Course> thirdPage = null;
//		if(secondPage.hasNext()) {
//			Pageable thirdPageable = secondPage.nextPageable();
//			thirdPage = springDataCourseRepository.findAll(thirdPageable);
//			logger.info("findAll_thirdPage --> {}",thirdPage.getContent());
//		}
//		
//		if(thirdPage != null && thirdPage.hasNext()) {
//			Pageable fourthPageable = thirdPage.nextPageable();
//			Page<Course> fourthPage = springDataCourseRepository.findAll(fourthPageable);
//			logger.info("findAll_fourthPage --> {}",fourthPage.getContent());
//		}
		PageRequest pageRequest = PageRequest.of(0, 4);
		Page<Course> page = springDataCourseRepository.findAll(pageRequest);
		logger.info("findAll_firstPage --> {}",page.getContent());
		
		while(page!= null && page.hasNext()) {
			Pageable nextPageable = page.nextPageable();
			page = springDataCourseRepository.findAll(nextPageable);
			logger.info("findAll_nextPage --> {}",page.getContent());
		}
		
		
	}
	
	@Test
	public void findUsingName() {
		List<Course> courseList = springDataCourseRepository.findByName("Science 2.0");
		logger.info("findByName --> {}",courseList);
	}
	
	@Test
	public void findUsingCustomJPQLQuery() {
		List<Course> courseList = springDataCourseRepository.courseWithNameAsScience();
		logger.info("courseWithNameAsScience --> {}",courseList);
	}
	
	@Test
	public void findUsingCustomNativeQuery() {
		List<Course> courseList = springDataCourseRepository.courseWithNameAsScienceNativeQuery();
		logger.info("courseWithNameAsScience --> {}",courseList);
	}
	
	@Test
	public void findUsingCustomNamedQuery() {
		List<Course> courseList = springDataCourseRepository.courseWithNameAsScienceNamedQuery();
		logger.info("findUsingCustomNamedQuery --> {}",courseList);
	}

}
