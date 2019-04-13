package com.ocean.persist.api.proxy.xd;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月10日 
      @version 1.0 
 */
public class XDTracks    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1617081006486530226L;
	private int type;
	private String url;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
