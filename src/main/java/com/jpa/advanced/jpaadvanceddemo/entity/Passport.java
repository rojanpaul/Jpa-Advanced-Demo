package com.jpa.advanced.jpaadvanceddemo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Passport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String number;
	
	@CreationTimestamp//No need of getters n setters
	private LocalDateTime createdDate;
	
	//To avoid duplication of column of data in DB use 'mappedBy' keyword.
	@OneToOne(fetch=FetchType.LAZY,mappedBy="passport")//MappedBy defined in the non owning side of relationship.
	private Student student;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	protected Passport() {
	}

	public Passport(String number) {
		super();
		this.number = number;
	}	

	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	
	
	@Override
	public String toString() {
		return String.format("Passport [id=%s, name=%s]", id, number);
	}
	
	
}
