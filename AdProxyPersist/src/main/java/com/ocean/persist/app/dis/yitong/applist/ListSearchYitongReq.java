package com.ocean.persist.app.dis.yitong.applist;

import com.ocean.persist.app.dis.AdDisParams;

public class ListSearchYitongReq  implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	private String developerid;
	private String appid;
	private String itemspaceid;
	
	private String params;
	
	
	private String package_name;
	private Integer ad_type;
	private String platform;
	private String baiduid;
	private String cuid;
	private String log_id;
	private Integer page;
	private Integer page_size;
	private String user_ip;
	private String sub_channel;
	private String sub_channel_name;

	
	//v2 版本参数
	private String clienttype;
	//private String user_agent;
	private int count;
	
	public String getDeveloperid() {
		return developerid;
	}
	public void setDeveloperid(String developerid) {
		this.developerid = developerid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getBaiduid() {
		return baiduid;
	}
	public void setBaiduid(String baiduid) {
		this.baiduid = baiduid;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getSub_channel() {
		return sub_channel;
	}
	public void setSub_channel(String sub_channel) {
		this.sub_channel = sub_channel;
	}
	public String getSub_channel_name() {
		return sub_channel_name;
	}
	public void setSub_channel_name(String sub_channel_name) {
		this.sub_channel_name = sub_channel_name;
	}
	public String getItemspaceid() {
		return itemspaceid;
	}
	public void setItemspaceid(String itemspaceid) {
		this.itemspaceid = itemspaceid;
	}
	public String getParams() {
		return params;
	}
/*	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}*/
	public void setParams(String params) {
		this.params = params;
	}
	public String getClienttype() {
		return clienttype;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public Integer getPage() {
		return page;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Integer getAd_type() {
		return ad_type;
	}
	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
