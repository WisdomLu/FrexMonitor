package com.ocean.persist.api.proxy.adwo;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月13日 
      @version 1.0 
 */
public class AdwoApp    extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3078084975109360686L;
	private String name;// N String App 名字
	private String pn ;//Y String Android 为包名,iOS 为Bundle ID
	private String ver ;//N String App 的版本
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
}
