package com.test.es.search;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.test.es.enums.SearchOperator;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:下午5:39:24
 */
public final class SearchFilter {

	private String searchProperty ;
	
	private Object value ;
	
	private SearchOperator operator ;
	
	private List<SearchFilter> orFilters ;
	
    /**
     * @param searchProperty 属性名
     * @param operator 操作
     * @param value    值
     */
    public SearchFilter(final String searchProperty, final SearchOperator operator, final Object value) {

        this.searchProperty = searchProperty;
        this.operator = operator;
        this.value = value;
    }
    
    /**
     * 目前仅支持一级或操作
     * 或 条件运算
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
     * 获取自定义查询使用的操作符
     * 1、首先获取前台传的
     * 2、获取SearchPropertyMappingInfo中定义的默认的
     * 3、返回空
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
     * 得到实体属性名
     * @return
     */
    public String getEntityProperty() {
        return searchProperty;
    }

    /**
     * 是否是一元过滤 如is null is not null
     * @return
     */
    public boolean isUnaryFilter() {
        String operatorStr = getOperator().getSymbo();
        return operatorStr.startsWith("is");
    }
    
    /**
     * 获取 操作符
     * @return
     */
    public SearchOperator getOperator()  {
        if(operator != null && operator != SearchOperator.custom) {
            return operator;
        }
        return null; 
    }


    
    
}

