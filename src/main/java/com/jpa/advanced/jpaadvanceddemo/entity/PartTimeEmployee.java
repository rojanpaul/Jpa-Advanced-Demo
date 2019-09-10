package com.jpa.advanced.jpaadvanceddemo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee{
	protected PartTimeEmployee() {
		
	}
	
	public PartTimeEmployee(String name,BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage=hourlyWage;
	}
	
	private BigDecimal hourlyWage;
	
}
