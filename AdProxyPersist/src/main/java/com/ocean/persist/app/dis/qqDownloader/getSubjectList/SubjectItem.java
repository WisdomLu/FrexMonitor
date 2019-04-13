package com.ocean.persist.app.dis.qqDownloader.getSubjectList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月18日 
      @version 1.0 
 */
public class SubjectItem      implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5111902715060493079L;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private int id;
	private String name;
	private String desc;
	private String iconUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
    
}
