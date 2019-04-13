package com.ocean.persist.api.proxy.fmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiApp extends AbstractBaseEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5788297785027481780L;
	private String appver;
	private String pkgname;
	private String appId;
	private String appKey;
	public String getAppver() {
		return appver;
	}
	public void setAppver(String appver) {
		this.appver = appver;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
