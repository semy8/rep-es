package com.test.es.exception;

import org.springframework.core.NestedRuntimeException;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:обнГ5:48:19
 */
public class SearchException extends NestedRuntimeException {

	public SearchException(String message){
		super(message);
	}
	
	
	public SearchException(String message,Throwable throwable){
		super(message,throwable);
	}
}

