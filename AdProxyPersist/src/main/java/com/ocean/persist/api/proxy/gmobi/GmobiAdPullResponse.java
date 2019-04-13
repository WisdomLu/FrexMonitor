package com.ocean.persist.api.proxy.gmobi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
public class GmobiAdPullResponse    extends AbstractBaseEntity  implements AdPullResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7488953032670405165L;
	private List<GmobiAd> ads;

	public List<GmobiAd> getAds() {
		return ads;
	}

	public void setAds(List<GmobiAd> ads) {
		this.ads = ads;
	}
}
