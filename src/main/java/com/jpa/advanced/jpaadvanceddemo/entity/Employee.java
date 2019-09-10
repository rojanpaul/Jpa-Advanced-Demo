package com.jpa.advanced.jpaadvanceddemo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
/**
 * @Inheritance(strategy)
 * InheritanceType.SINGLE_TABLE --> high performance as no join involved. But lot of null values(no data integrity)
 * @DiscriminatorColumn(name="employee_type") applicable for above
 * Best performance
 *  
 * InheritanceType.JOINED --> Separate table for common(parent) n individual classes(child). Best DB design
 * When retrieving join is performed on three tables. But performance effected. No duplication. 
 * Best Data Integrity & Column design
 * 
 * InheritanceType.TABLE_PER_CLASS --> a table per entity class. Duplicate column structure.
 * Union operation involved when retrieving values (even though less performance impact).
 * 
 * @MappedSuperclass --> Do not use @Inheritance , instead use @MappedSuperclass as the inheritance
 * (Inheritance relation removed) strategy. @Entity annotation to be removed.
 * It is not an entity class anymore. Only subclass entities will be existing. So cannot use JPQL for Employee anymore.
 * But only of subclasses. Employee class just works like a place for defining common definitions.
 * 
 * @author rojan
 *
 */

@MappedSuperclass
//@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name="employee_type")
public abstract class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name ="name", nullable=false)
	private String name;
	
	
	protected Employee() {
	}

	public Employee(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return String.format("Employee [id=%s, name=%s]", id, name);
	}
	
	
}
