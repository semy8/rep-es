package com.test.es;

import javax.persistence.Temporal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.es.repository.PetTypeRepository;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:ÏÂÎç2:29:38
 */
@ContextConfiguration(locations = {"classpath:spring-datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PetTypeRepositoryTest {
	@Autowired
	private PetTypeRepository petTypeRepository;
	
	@Test
	public void testFindAll(){
		System.out.println(petTypeRepository.findAll().size());
	}
}

