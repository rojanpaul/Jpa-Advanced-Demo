package com.jpa.advanced.jpaadvanceddemo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Course")
@NamedQueries(value= {
		@NamedQuery(name="query_get_all_courses", query ="select c from Course c"),
		@NamedQuery(name="query_get_all_courses_join_fetch", query ="select c from Course c JOIN fetch c.students s"),
		@NamedQuery(name="query_get_all_courses_with_condition", query ="select c from Course c where c.name like '%Science%'")
})
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@Cacheable//Annotation for enabling second level ehcache for this Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)//(strategy=GenerationType.SEQUENCE,generator="course_seq_generator")
//	@SequenceGenerator(name="course_seq_generator",sequenceName="course_seq" )
	private Long id;
	
	@Column(name ="name", nullable=false)
	private String name;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy="courses")
	@JsonIgnore//Spring Data Rest exposure
	private List<Student> students = new ArrayList<>();
	
	@CreationTimestamp//No need of getters n setters
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	private boolean isDeleted;
	
	/**
	 * Setting pre remove method as remove is the 
	 * real operation performed while deleting
	 */
	@PreRemove
	public void preRemove() {
		this.isDeleted=true;
	}
	
	protected Course() {
	}

	public Course(String name) {
		super();
		this.name = name;
	}
	
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return String.format("Course [id=%s, name=%s]", id, name);
	}
	
	
}
