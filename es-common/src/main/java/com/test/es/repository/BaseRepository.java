package com.test.es.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.test.es.entity.AbstractEntity;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:обнГ1:11:21
 */
public interface BaseRepository<T extends AbstractEntity,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

}

