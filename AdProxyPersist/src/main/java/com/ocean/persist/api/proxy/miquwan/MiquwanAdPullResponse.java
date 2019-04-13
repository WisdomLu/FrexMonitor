package com.ocean.persist.api.proxy.miquwan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月31日 
      @version 1.0 
 */
public class MiquwanAdPullResponse extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8694362549815775299L;
	private String version;
	private String ret;
	private MiquwanAd adInfo;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public MiquwanAd getAdInfo() {
		return adInfo;
	}
	public void setAdInfo(MiquwanAd adInfo) {
		this.adInfo = adInfo;
	}
}
