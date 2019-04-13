package com.ocean.persist.api.proxy.uniplay;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月7日 
      @version 1.0 
 */
public class UniPlayAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8339306354122899224L;
	private int res;
	private String msg;
    private UniPlayAd ad;

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UniPlayAd getAd() {
		return ad;
	}
	public void setAd(UniPlayAd ad) {
		this.ad = ad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
}
