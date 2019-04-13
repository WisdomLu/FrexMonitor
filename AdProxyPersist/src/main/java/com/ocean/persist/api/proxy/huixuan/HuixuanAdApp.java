package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdApp    {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4333828531032222807L;
	private String id;
	private String name;
	private String domain;
	private String ver;
	private String bundle;
	private String paid;
	private List<String>  keywords;
	private String storeurl;
	private int mkt_id;
	private String mkt_sn;
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getBundle() {
		return bundle;
	}
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public String getStoreurl() {
		return storeurl;
	}
	public void setStoreurl(String storeurl) {
		this.storeurl = storeurl;
	}
	public int getMkt_id() {
		return mkt_id;
	}
	public void setMkt_id(int mkt_id) {
		this.mkt_id = mkt_id;
	}
	public String getMkt_sn() {
		return mkt_sn;
	}
	public void setMkt_sn(String mkt_sn) {
		this.mkt_sn = mkt_sn;
	}

}
