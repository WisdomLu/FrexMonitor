package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeTitleResp    implements  AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4371913041174717199L;
    private String text;
    private RedStoneExt ext;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
