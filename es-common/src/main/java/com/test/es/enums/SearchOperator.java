package com.test.es.enums;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.test.es.exception.SearchException;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:下午5:40:36
 */
public enum SearchOperator {
    eq("等于", "="), notEq("不等于", "!="),
    gt("大于", ">"), gte("大于等于", ">="), lt("小于", "<"), lte("小于等于", "<="),
    prefixLike("前缀模糊匹配", "like"), prefixNotLike("前缀模糊不匹配", "not like"),
    suffixLike("后缀模糊匹配", "like"), suffixNotLike("后缀模糊不匹配", "not like"),
    like("模糊匹配", "like"), notLike("不匹配", "not like"),
    isNull("空", "is null"), isNotNull("非空", "is not null"),
    in("包含", "in"), notIn("不包含", "not in"), custom("自定义默认的", null);
    
    private String info ;
    private String symbo ;
    SearchOperator(String info , String symbo){
    	this.info = info ;
    	this.symbo = symbo ;
    }
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the symbo
	 */
	public String getSymbo() {
		return symbo;
	}
	/**
	 * @param symbo the symbo to set
	 */
	public void setSymbo(String symbo) {
		this.symbo = symbo;
	}
	
	public static String  toStringAllOperator(){
		return Arrays.toString(SearchOperator.values());
	}
	
	public static SearchOperator valueBySymbo(String symbo){
		symbo = formatSymbol(symbo);
		for(SearchOperator operator : values()){
			if(symbo.equals(operator.getSymbo())){
				return operator;
			}
		}

		throw new SearchException("SearchOperator not method search operator symbol : " + symbo);

	}
 
	private static String formatSymbol(String symbol) {
        if(StringUtils.isBlank(symbol)) {
            return symbol;
        }
        return symbol.trim().toLowerCase().replace("  ", " ");
    }
    
}

