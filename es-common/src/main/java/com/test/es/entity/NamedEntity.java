package com.test.es.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * User:xinmingmao<br>
 * Date:2013-4-15<br>
 * Time:ионГ11:34:52
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity<Integer> {
	@Column(name = "name")
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
