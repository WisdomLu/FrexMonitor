package com.ocean.persist.api.proxy.yitong;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
public class YitongAdPullResponse extends AbstractYitongResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482753778664032612L;
	private List<YitongAd> ads;
	public List<YitongAd> getAds() {
		return ads;
	}
	public void setAds(List<YitongAd> ads) {
		this.ads = ads;
	}
}
