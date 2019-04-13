package com.ocean.persist.api.proxy.xd;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月10日 
      @version 1.0 
 */
public class XDSlot    {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3804468563216116574L;
	private String adspaceId;
	private int adspaceWidth;
	private int adspaceHeight;
	public String getAdspaceId() {
		return adspaceId;
	}
	public void setAdspaceId(String adspaceId) {
		this.adspaceId = adspaceId;
	}
	public int getAdspaceWidth() {
		return adspaceWidth;
	}
	public void setAdspaceWidth(int adspaceWidth) {
		this.adspaceWidth = adspaceWidth;
	}
	public int getAdspaceHeight() {
		return adspaceHeight;
	}
	public void setAdspaceHeight(int adspaceHeight) {
		this.adspaceHeight = adspaceHeight;
	}

}
