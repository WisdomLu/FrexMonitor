package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouPublisher  {
   /**
	 * 
	 */
	private static final long serialVersionUID = -2680358083729225066L;
    private String name;//出品方名称
    private String domain;//出品方顶级域名
    private String[] cat;//出品方类别
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
	public String[] getCat() {
		return cat;
	}
	public void setCat(String[] cat) {
		this.cat = cat;
	}
   
}
