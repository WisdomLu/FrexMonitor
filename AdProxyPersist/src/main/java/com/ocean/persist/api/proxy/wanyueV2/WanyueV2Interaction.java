package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Interaction {
	private int type;//	交互类型	number	交互类型:
/*		1.链接跳转
		2.下载
		3.deeplink拉活	是	*/
	private String value;//	交互内容	String	交互内容，如下载地址	是	
	public int getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
