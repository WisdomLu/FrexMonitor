package com.ocean.persist.api.proxy.ruangaoyun;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunSite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8543100923539446028L;
    private String id;
    private String name;
    private String page;
    private String ref;
    private RuanGaoYunContent content;
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public RuanGaoYunContent getContent() {
		return content;
	}
	public void setContent(RuanGaoYunContent content) {
		this.content = content;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
    
}
