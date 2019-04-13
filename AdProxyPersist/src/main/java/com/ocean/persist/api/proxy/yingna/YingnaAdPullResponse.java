package com.ocean.persist.api.proxy.yingna;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月18日 
      @version 1.0 
 */
public class YingnaAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5165325808878645931L;
	private YingnaAd banner;
	private YingnaAd full;
	private YingnaAd popup;
	private YingnaFlowInfo flowinfo;
	public YingnaAd getBanner() {
		return banner;
	}
	public void setBanner(YingnaAd banner) {
		this.banner = banner;
	}
	public YingnaAd getFull() {
		return full;
	}
	public void setFull(YingnaAd full) {
		this.full = full;
	}
	public YingnaAd getPopup() {
		return popup;
	}
	public void setPopup(YingnaAd popup) {
		this.popup = popup;
	}
	public YingnaFlowInfo getFlowinfo() {
		return flowinfo;
	}
	public void setFlowinfo(YingnaFlowInfo flowinfo) {
		this.flowinfo = flowinfo;
	}
	
}
