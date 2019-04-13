package com.ocean.persist.api.proxy.ryan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanApp   {
   /**
	 * 
	 */
	private static final long serialVersionUID = -4464990374962378977L;
   private String id;//required,app 唯一标识， 由ADX 生成
   private String name;//optional,app名称
   private String bundle;//optional,app包名
   private String cat;//recommend,app 的分类
   private String context ;//optional,应用当前上下文信息， 需上下游协商
   private Object ext;//optional,拓展字段
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
	public String getBundle() {
		return bundle;
	}
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
}
