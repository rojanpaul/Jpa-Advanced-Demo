package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jpa.advanced.jpaadvanceddemo.entity.Course;

/**
 * When creating repositories for all entities, there is code duplication and
 * only the entity names changes. Rest everything is same across respositories.
 * To solve this problem introducing SpringDataJPA as an interface Repository.
 * Added as default like Hibernate when we are adding JPA in spring Boot starter
 * project.
 * 
 * @author rojan
 *
 */
//To enable spring REST Data, so that the methods exposed as REST webservices 
//automatically with the path provided here. Not recommended for production. 
//Just for prototyping
@RepositoryRestResource(path="courses")
public interface SpringDataCourseRepository extends JpaRepository<Course, Long> {
	/**
	 * Below methods will be internally automatically intelligently mapped by 
	 * Spring DATA JPA based on the naming convention
	 * @param name
	 * @return
	 */
	List<Course> findByName(String name);// Custom method declaration is done.
	List<Course> findByNameAndId(String name,Long id);//Two parameters
	List<Course> countByName(String name);//To find count
	List<Course> findByNameOrderById(String name);//Sorting
	List<Course> deleteByName(String name);
	
	/**
	 * If some methods cannot be defined using the above methods we can write
	 *  custom query and methods as shown below
	 *  
	 */
	//Using JPQL Custom Query
	@Query("select c from Course c where c.name like '%Science%'")
	List<Course> courseWithNameAsScience();
	
	//Using NativeQueries or normal SQL queries
	@Query(value = "select * from Course where name like '%Science%'"
			,nativeQuery=true)
	List<Course> courseWithNameAsScienceNativeQuery();
	
	//Using Named Custom Query
	@Query(name = "query_get_all_courses_with_condition")
	List<Course> courseWithNameAsScienceNamedQuery();
}
