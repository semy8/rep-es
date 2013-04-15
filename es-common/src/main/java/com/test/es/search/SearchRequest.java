package com.test.es.search;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.es.enums.SearchOperator;
import com.test.es.exception.SearchException;
import com.test.es.plugin.entity.Searchable;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:下午6:00:16
 */
public final class SearchRequest implements Searchable {

    private final Map<String, SearchFilter> searchFilterMap = Maps.newHashMap();

    private final Pageable page;

    private final Sort sort;

    private boolean converted;
    
    public  SearchRequest(final Map<String,Object> params){
    	this(params,null,null);
    }
    
    public  SearchRequest(){
    	this(null,null,null);
    }
    
    public SearchRequest(Map<String,Object> params,Pageable page){
    	this(params,page,null);
    }
    
    public SearchRequest(Map<String,Object> params,Sort sort){
    	this(params,null,sort);
    }
    
    public SearchRequest(final Map<String,Object> params,final Pageable page,final Sort sort){
    	toSearchFilters(params);
    	if(null == sort){
    		this.sort = page != null?page.getSort() : null ;
    	}else{
    		this.sort = (page != null ? sort.and(page.getSort()) : sort);
    	}
    	
    	   //把排序合并到page中
        if(page != null) {
            this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), this.sort);
        } else {
            this.page = null;
        }
    }
    

    private List<SearchFilter> toSearchFilters(final Map<String, Object> searchParams) throws SearchException {
        List<SearchFilter> searchFilters = Lists.newArrayList();
        if(searchParams == null || searchParams.size() == 0) {
            return searchFilters;
        }
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            String key = entry.getKey();
            Assert.notNull(key, "SearchRequest params key must not null");
            Object value = entry.getValue();

            String[] searchs = StringUtils.split(key, separator);

            if (searchs.length == 0) {
                throw new SearchException("SearchRequest params key format must be : property or property_op");
            }

            String searchProperty = searchs[0];

            SearchOperator operator = null;
            if (searchs.length == 1) {
                operator = SearchOperator.custom;
            } else {
                try {
                    operator = SearchOperator.valueOf(searchs[1]);
                } catch (IllegalArgumentException e) {
                }
            }

            boolean allowBlankValue = isAllowBlankValue(operator);
            boolean isValueBlank = value == null;
            isValueBlank = isValueBlank || (value instanceof String && StringUtils.isBlank((String) value));
            isValueBlank = isValueBlank || (value instanceof List && ((List)value).size() == 0);
            //过滤掉空值，即不参与查询
            if (!allowBlankValue && isValueBlank) {
                continue;
            }
            searchFilters.add(addSearchFilter(key, searchProperty, operator, value));
        }
        return searchFilters;
    }

    /**
     * 操作符是否允许为空
     *
     * @param operator
     * @return
     */
    private boolean isAllowBlankValue(final SearchOperator operator) {
        return operator == SearchOperator.isNotNull || operator == SearchOperator.isNull;
    }


    /**
     * @param searchProperty
     * @param operator
     * @param value
     * @see SearchFilter#SearchFilter(java.lang.String, SearchOperator, java.lang.Object)
     */
    public SearchFilter addSearchFilter(final String key, final String searchProperty, final SearchOperator operator, final Object value) {
        SearchFilter searchFilter = new SearchFilter(searchProperty, operator, value);
        searchFilterMap.put(key, searchFilter);
        return searchFilter;
    }
    

    public SearchFilter addSearchFilter(String key, Object value) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(key, value);
        return toSearchFilters(map).get(0);
    }

    public SearchFilter addSearchFilter(SearchFilter searchFilter) {
        searchFilterMap.put(searchFilter.getSearchProperty() + separator + searchFilter.getOperatorStr(), searchFilter);
        return searchFilter;
    }

    public SearchFilter removeSearchFilter(String key) {
        return getSearchFilterMap().remove(key);
    }

    public SearchFilter addSearchFilter(String searchProperty, SearchOperator operator, Object value) {
        return addSearchFilter(searchProperty + separator + operator.toString(), value);
    }

    public Collection<SearchFilter> getSearchFilters() {
        return Collections.unmodifiableCollection(searchFilterMap.values());
    }

    public Map<String, SearchFilter> getSearchFilterMap() {
        return searchFilterMap;
    }


    
	public <T> Specification<T> getSpecifications(Class<T> domainClass) {
		return null;
	}

	public Pageable getPage() {
		return page;
	}


	public Sort getSort() {
		return sort;
	}
	

    public boolean isConverted() {
        return converted;
    }

    public void markConverted() {
        this.converted = true;
    }

    public boolean hasSearchFilter() {
        return searchFilterMap.size() > 0;
    }

    public boolean hashSort() {
        return this.sort != null && this.sort.iterator().hasNext();
    }

    public boolean hasPageable() {
        return this.page != null && this.page.getPageSize() > 0;
    }

    public boolean containsSearchProperty(String searchProperty) {
        return searchFilterMap.containsKey(searchProperty);
    }

    public Object getValue(String searchProperty) {
        return searchFilterMap.get(searchProperty).getValue();
    }


    @Override
    public String toString() {
        return "SearchRequest{" +
                "searchFilterMap=" + searchFilterMap +
                ", page=" + page +
                ", sort=" + sort +
                '}';
    }

}

