package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeTitle  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6100121030559682968L;
    private Integer len;
    private RedStoneExt ext;
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
