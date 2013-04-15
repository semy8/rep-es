package com.test.es.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.es.entity.PetType;
import com.test.es.repository.PetTypeRepository;
import com.test.es.service.BaseService;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:обнГ4:59:57
 */
@Service("petTypeService")
public class PetTypeService extends BaseService<PetType, Integer> {
	private PetTypeRepository petTypeRepository;
	@Autowired
	public  PetTypeService(PetTypeRepository petTypeRepository){
		super.setBaseRepository(petTypeRepository);
		this.petTypeRepository = petTypeRepository;
	}
}

