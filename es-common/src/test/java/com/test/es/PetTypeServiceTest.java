package com.test.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.es.service.impl.PetTypeService;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:ÏÂÎç5:07:03
 */
@ContextConfiguration(locations = {"classpath:spring-datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PetTypeServiceTest {

	@Autowired
	private PetTypeService petTypeService;
	
	@Test
	public void testFindAll(){
		System.out.println("petType count :"+petTypeService.findAll());
	}
}

