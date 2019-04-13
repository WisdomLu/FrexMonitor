package com.ocean.persist.api.proxy.ruangaoyun;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunApp    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4131898382010352148L;
    private String id;
    private String name;
    private String ver;
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
