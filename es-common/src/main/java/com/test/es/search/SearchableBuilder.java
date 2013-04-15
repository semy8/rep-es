package com.test.es.search;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.common.collect.Maps;
import com.test.es.enums.SearchOperator;
import com.test.es.plugin.entity.Searchable;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:ÏÂÎç6:04:05
 */
public class SearchableBuilder {

	private Map<String,Object> searchParams ;
	
	private Map<String,SearchFilter> searchFilterMap = Maps.newHashMap();
	
	private Pageable page ;
	
	private Sort sort ;
	
	private SearchableBuilder(){
		
	}
	
	public static SearchableBuilder newInstance(){
		return newInstance(new HashMap<String,Object>());
	}
	
	public static SearchableBuilder newInstance(Map<String,Object> params){
		SearchableBuilder builder = new SearchableBuilder();
		builder.searchParams = params;
		return builder ;
	}
	
	public SearchableBuilder addSearchParam(String key, String[] value) {
        searchParams.put(key, value);
        return this;
    }

    public SearchableBuilder addSearchParam(String key, String value) {
        searchParams.put(key, value);
        return this;
    }

    public SearchableBuilder addSearchFilter(String searchProperty, SearchOperator operator, Object value) {
        this.searchFilterMap.put(searchProperty, new SearchFilter(searchProperty, operator, value));
        return this;
    }

    public SearchableBuilder addSearchFilter(SearchFilter searchFilter) {
        this.searchFilterMap.put(searchFilter.getSearchProperty(), searchFilter);
        return this;
    }
    
    public SearchableBuilder setPage(Pageable page) {
        this.page = page;
        return this;
    }

    public SearchableBuilder setSort(Sort sort) {
        this.sort = sort;
        return this;
    }
	

    public Searchable buildSearchable() {
        SearchRequest search = new SearchRequest(searchParams, page, sort);
        search.getSearchFilterMap().putAll(searchFilterMap);
        return search;
    }

}

