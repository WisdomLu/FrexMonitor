package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneApp     {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2347362599754076042L;
	private String id;
	private String name;
	private String domain;
	private List<Integer> cat;
	private String ver;
	private String bundle;
	private String paid;
	private String keywords;
	private String storeurl;
	private RedStoneExt ext;
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
	public List<Integer> getCat() {
		return cat;
	}
	public void setCat(List<Integer> cat) {
		this.cat = cat;
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getStoreurl() {
		return storeurl;
	}
	public void setStoreurl(String storeurl) {
		this.storeurl = storeurl;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
