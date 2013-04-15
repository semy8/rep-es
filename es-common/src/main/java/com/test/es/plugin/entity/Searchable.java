package com.test.es.plugin.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:����4:50:03
 */
public interface Searchable {
	
	//��ѯ�����ָ���
    public static final String separator = "_";

	public <T> Specification<T> getSpecifications(final Class<T> domainClass);
	
    /**
     * ��ȡ��ҳ��������Ϣ
     * @return
     */
    public Pageable getPage();

    /**
     * ��ȡ������Ϣ
     * @return
     */
    public Sort getSort();
}

