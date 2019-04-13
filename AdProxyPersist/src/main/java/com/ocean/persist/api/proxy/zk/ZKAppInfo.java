package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/**
 * SDK所在的应用的应用相关信息. 然并卵
 * 
 * @author dell
 *
 */
public class ZKAppInfo    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4107398594394126014L;

	private String app_id;
    private String channel_id;

	private String app_name;
	private String package_name;
	private String category;
	private String app_keywords;
	private String app_version;
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getApp_keywords() {
		return app_keywords;
	}
	public void setApp_keywords(String app_keywords) {
		this.app_keywords = app_keywords;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}


}
