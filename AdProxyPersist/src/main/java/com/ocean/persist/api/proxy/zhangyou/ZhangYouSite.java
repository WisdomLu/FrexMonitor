package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouSite  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5250889857301559402L;
    private String id;//网站id
    private String name;//网站名称
    private String domain;//网站域名
    private String page;//当前网页网址
    private String[] cat;//网站类别
    private String[] sectioncat;//网站当前频道类别
    private String[] pagecat;//网站当前页面类别
    private String ref;//当前页面referrer网址
    private String search;//进入当前页面的搜索关键词
    private int mobile;//是否为移动网站， 1为移动网站
    private String keywords;//网页关键字，可多个，逗号隔开
    private ZhangYouPublisher publisher;//出品方
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String[] getCat() {
		return cat;
	}
	public void setCat(String[] cat) {
		this.cat = cat;
	}
	public String[] getSectioncat() {
		return sectioncat;
	}
	public void setSectioncat(String[] sectioncat) {
		this.sectioncat = sectioncat;
	}
	public String[] getPagecat() {
		return pagecat;
	}
	public void setPagecat(String[] pagecat) {
		this.pagecat = pagecat;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public ZhangYouPublisher getPublisher() {
		return publisher;
	}
	public void setPublisher(ZhangYouPublisher publisher) {
		this.publisher = publisher;
	}
    
    
}
