package com.ocean.service.qihooInvokeHandler.base;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public enum QihooMethodType {
    KEYWORD_SEARCH(1,"keywordSearch"),
    PACKAGE_SEARCH(2,"packageNameSearch"),
    APP_RECOMMEND_SEARCH(3,"appRecommendSearch");
    private int value;
    private String name;
    private QihooMethodType(int value,String name){
    	this.value=value;
    	this.name=name;
    }
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
