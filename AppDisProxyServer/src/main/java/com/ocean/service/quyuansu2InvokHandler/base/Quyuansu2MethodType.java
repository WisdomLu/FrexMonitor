package com.ocean.service.quyuansu2InvokHandler.base;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public enum Quyuansu2MethodType {
	APP_LIST_SEARCH(1,"appListSearch"),
	PACKAGE_SEARCH(2,"packageSearch"),
	KEYWORD_SEARCH(3,"keywordSearch"),
	APP_RECOMMEND_SEARCH(4,"appRecommendSearch"),
	HOTWORD_SEARCH(5,"hotWordSearch"),
	RANDOM_SEARCH(6,"randomSearch");
    private int value;
    private String name;
    private Quyuansu2MethodType(int value,String name){
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
