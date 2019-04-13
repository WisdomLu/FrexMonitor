package com.ocean.persist.api.proxy.huzhong;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
public class HuzhongAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8540123794456502996L;
	private List<HuzhongAd> ad;
	private String version;
	private long pt;
	private String reqid;
	public List<HuzhongAd> getAd() {
		return ad;
	}
	public void setAd(List<HuzhongAd> ad) {
		this.ad = ad;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public long getPt() {
		return pt;
	}
	public void setPt(long pt) {
		this.pt = pt;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

}
