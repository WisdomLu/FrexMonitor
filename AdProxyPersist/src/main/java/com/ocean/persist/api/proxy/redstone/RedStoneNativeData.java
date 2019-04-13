package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeData  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1978799834315141920L;
    private Integer type;
    private Integer len;
    private RedStoneExt ext;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLen() {
		return len;
	}
	public void setLen(Integer len) {
		this.len = len;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
