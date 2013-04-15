package com.test.es.plugin.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:下午4:50:03
 */
public interface Searchable {
	
	//查询参数分隔符
    public static final String separator = "_";

	public <T> Specification<T> getSpecifications(final Class<T> domainClass);
	
    /**
     * 获取分页和排序信息
     * @return
     */
    public Pageable getPage();

    /**
     * 获取排序信息
     * @return
     */
    public Sort getSort();
}

