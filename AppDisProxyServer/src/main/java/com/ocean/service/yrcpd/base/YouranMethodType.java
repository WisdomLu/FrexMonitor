package com.ocean.service.yrcpd.base;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public enum YouranMethodType {
	APP_LIST_ASYN_SEARCH(1,"appListAsynSearch"),
	PKG_SEARCH(2,"pkgSearch");
    private int value;
    private String name;
    private YouranMethodType(int value,String name){
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
