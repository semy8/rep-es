package com.test.es.search;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.test.es.enums.SearchOperator;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:����5:39:24
 */
public final class SearchFilter {

	private String searchProperty ;
	
	private Object value ;
	
	private SearchOperator operator ;
	
	private List<SearchFilter> orFilters ;
	
    /**
     * @param searchProperty ������
     * @param operator ����
     * @param value    ֵ
     */
    public SearchFilter(final String searchProperty, final SearchOperator operator, final Object value) {

        this.searchProperty = searchProperty;
        this.operator = operator;
        this.value = value;
    }
    
    /**
     * Ŀǰ��֧��һ�������
     * �� ��������
     * @param searchProperty
     * @param operator
     * @param value
     * @return
     */
    public SearchFilter or(final String searchProperty, final SearchOperator operator, final Object value) {
       return or(new SearchFilter(searchProperty, operator, value));
    }
    public SearchFilter or(SearchFilter orSearchFilter) {
        if(orFilters == null) {
            orFilters = Lists.newArrayList();
        }
        orFilters.add(orSearchFilter);
        return this;
    }
    
    public boolean  hasOrSearchFilters() {
        return !CollectionUtils.isEmpty(getOrFilters());
    }
    
    
    public List<SearchFilter> getOrFilters() {
        return orFilters;
    }
    
    public String getSearchProperty() {
        return searchProperty;
    }
    
    /**
     * ��ȡ�Զ����ѯʹ�õĲ�����
     * 1�����Ȼ�ȡǰ̨����
     * 2����ȡSearchPropertyMappingInfo�ж����Ĭ�ϵ�
     * 3�����ؿ�
     * @return
     */
    public String getOperatorStr() {
        if(operator != null && operator != SearchOperator.custom) {
            return operator.getSymbo();
        }
        return "";
    }
    
    public Object getValue() {
        return value;
    }


    public void setValue(final Object value) {
        this.value = value;
    }

    public void setOperator(final SearchOperator operator) {
        this.operator = operator;
    }

    public void setSearchProperty(final String searchProperty) {
        this.searchProperty = searchProperty;
    }
    
    /**
     * �õ�ʵ��������
     * @return
     */
    public String getEntityProperty() {
        return searchProperty;
    }

    /**
     * �Ƿ���һԪ���� ��is null is not null
     * @return
     */
    public boolean isUnaryFilter() {
        String operatorStr = getOperator().getSymbo();
        return operatorStr.startsWith("is");
    }
    
    /**
     * ��ȡ ������
     * @return
     */
    public SearchOperator getOperator()  {
        if(operator != null && operator != SearchOperator.custom) {
            return operator;
        }
        return null; 
    }


    
    
}

