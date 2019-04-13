package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanUser   { 
	private static final long serialVersionUID = -3469803848807368007L;
    private List<String> tags;//optional,用户标签， 详见附录 8.3
    private Object ext;//optional,拓展字段
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
}
