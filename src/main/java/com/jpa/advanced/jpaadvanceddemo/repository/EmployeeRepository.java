package com.jpa.advanced.jpaadvanceddemo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.advanced.jpaadvanceddemo.entity.Employee;
import com.jpa.advanced.jpaadvanceddemo.entity.FullTimeEmployee;
import com.jpa.advanced.jpaadvanceddemo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
//	public void insert(PartTimeEmployee employee) {
//		em.persist(employee);
//	}
//	
//	public void insert(FullTimeEmployee employee) {
//		em.persist(employee);
//	}
	
//	public List<Employee> retrieveAllEmployees(){
//		return em.createQuery("select e from Employee e").getResultList();
//	}
	
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
		return em.createQuery("select e from PartTimeEmployee e",PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
		return em.createQuery("select e from FullTimeEmployee e",FullTimeEmployee.class).getResultList();
	}
	
}
