package com.test.es.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *User:xinmingmao 
 *Date:2013-4-15
 *Time:ÉÏÎç11:23:46
 */
@MappedSuperclass
public class Person extends BaseEntity<Integer> {
	@Column(name="first_name")
	@NotEmpty
	private String firstName ;
	@Column(name="last_name")
	@NotEmpty
	private String lastName ;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}

