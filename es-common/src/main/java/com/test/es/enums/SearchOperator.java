package com.test.es.enums;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.test.es.exception.SearchException;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:����5:40:36
 */
public enum SearchOperator {
    eq("����", "="), notEq("������", "!="),
    gt("����", ">"), gte("���ڵ���", ">="), lt("С��", "<"), lte("С�ڵ���", "<="),
    prefixLike("ǰ׺ģ��ƥ��", "like"), prefixNotLike("ǰ׺ģ����ƥ��", "not like"),
    suffixLike("��׺ģ��ƥ��", "like"), suffixNotLike("��׺ģ����ƥ��", "not like"),
    like("ģ��ƥ��", "like"), notLike("��ƥ��", "not like"),
    isNull("��", "is null"), isNotNull("�ǿ�", "is not null"),
    in("����", "in"), notIn("������", "not in"), custom("�Զ���Ĭ�ϵ�", null);
    
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

