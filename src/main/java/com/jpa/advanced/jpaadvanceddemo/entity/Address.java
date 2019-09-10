package com.jpa.advanced.jpaadvanceddemo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	protected Address() {
		
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Address [line1=%s, line2=%s, city=%s]", line1, line2, city);
	}



	/**
	 * @param line1
	 * @param line2
	 * @param city
	 */
	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}
	
	private String line1;
	private String line2;
	private String city;
	
	
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
