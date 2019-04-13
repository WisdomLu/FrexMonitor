package com.ocean.persist.api.proxy.yidianzx;

public class YidianzxApp {
	private String id ;//string 推荐当前exchange 下app 的唯一标识
	private String name ;// string 否app 名称
	private String ver ;// ;//string 否app 版本号
	//publisher object 否媒体属性对象，见Publisher
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
}
