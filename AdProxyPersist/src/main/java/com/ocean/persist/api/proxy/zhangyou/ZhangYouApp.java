package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouApp  {

	private static final long serialVersionUID = 98575315297012179L;
    private String id;
    private String name;
    private String app_key;
    private String bundle;
    private String ver;
    private String[] cat;//app 类别
    private ZhangYouPublisher publisher;
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
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getBundle() {
		return bundle;
	}
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String[] getCat() {
		return cat;
	}
	public void setCat(String[] cat) {
		this.cat = cat;
	}
	public ZhangYouPublisher getPublisher() {
		return publisher;
	}
	public void setPublisher(ZhangYouPublisher publisher) {
		this.publisher = publisher;
	}
    
    
}
