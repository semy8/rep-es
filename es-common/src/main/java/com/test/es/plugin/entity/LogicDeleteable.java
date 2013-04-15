package com.test.es.plugin.entity;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:обнГ4:41:25
 */
public interface LogicDeleteable {

	public  boolean getDeleted();
	
	public  void setDeleted(boolean deleted);
	
	public void markDeleted();
}

