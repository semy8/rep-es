package com.test.es.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * User:xinmingmao<br>
 * Date:2013-4-15<br>
 * Time:ÉÏÎç11:43:35
 */
@Entity
@Table(name= "visits")
public class Visit extends BaseEntity<Long> {

	@Column(name = "visit_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private DateTime date;
	
    @NotEmpty
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

	/**
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the pet
	 */
	public Pet getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
	}
    
    
}
