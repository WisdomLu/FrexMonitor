package com.ocean.persist.api.proxy.yitong;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
public class YitongSpecial  extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -652233816394676263L;
	private YitongDict dict;
	public YitongDict getDict() {
		return dict;
	}
	public void setDict(YitongDict dict) {
		this.dict = dict;
	}
}
