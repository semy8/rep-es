/**
 * 
 */
package com.test.es.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author xinmingmao
 * 
 * Date 2013-4-14
 */
@MappedSuperclass
public class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private ID id;

	/**
	 * @return the id
	 */
	public ID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ID id) {
		this.id = id;
	}
	
	

}
