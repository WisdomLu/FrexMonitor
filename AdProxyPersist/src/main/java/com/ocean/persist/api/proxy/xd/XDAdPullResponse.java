package com.ocean.persist.api.proxy.xd;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月10日 
      @version 1.0 
 */
public class XDAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8887424068943698684L;
	private int code;
	private String msg;
	private List<XDAd> ads;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<XDAd> getAds() {
		return ads;
	}
	public void setAds(List<XDAd> ads) {
		this.ads = ads;
	}
}
