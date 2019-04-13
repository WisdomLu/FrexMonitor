package com.ocean.persist.api.proxy.shaibo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboAdImg    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1405338300811622382L;
	private int type 	;//int 	图片类型,图标(101-199),截图(201-299),主图(301-399)。
	private String iurl 	;//string 	图片地址。
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}
}
