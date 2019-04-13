package com.ocean.persist.api.proxy.yitong;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
public class YitongDict  extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5900819754833544546L;
	private String picture;//": "resource", //图片是资源resource
	private String txt;//": "resource1" //标题是资源resource1
	private String title;//":"resource1", 
	private String summary;//":"resource2"}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
