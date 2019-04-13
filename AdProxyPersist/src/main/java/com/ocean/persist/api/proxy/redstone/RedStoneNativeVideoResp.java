package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeVideoResp    implements  AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1791483437353249739L;
    private String vasttag;
	public String getVasttag() {
		return vasttag;
	}
	public void setVasttag(String vasttag) {
		this.vasttag = vasttag;
	}
}
