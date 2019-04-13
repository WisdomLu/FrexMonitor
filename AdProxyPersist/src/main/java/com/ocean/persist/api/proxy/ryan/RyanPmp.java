package com.ocean.persist.api.proxy.ryan;

import java.util.List;
import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanPmp   {
  /**
	 * 
	 */
  private static final long serialVersionUID = 339454483000078974L;
  private List<Map<String,Object>> deals;//required,{key:id  value:<string  required  双方线下确定好的 deal id>,key:bidfloor value:<double required  双方线下确认好的价格>,key:ext  value<object optional  扩展字段>}
  private Object ext;//optional,拓展字段
	public List<Map<String, Object>> getDeals() {
		return deals;
	}
	public void setDeals(List<Map<String, Object>> deals) {
		this.deals = deals;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
  
}
