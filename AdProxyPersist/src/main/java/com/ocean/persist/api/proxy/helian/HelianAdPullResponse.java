package com.ocean.persist.api.proxy.helian;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月15日 
      @version 1.0 
 */
public class HelianAdPullResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3761123012551640522L;
	private String returncode;//响应状态码

	private String msg;//失败原因

	private String adid;//广告id

	private int adnum;//广告数量

	private List<HelianAd> ads;//

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public int getAdnum() {
		return adnum;
	}

	public void setAdnum(int adnum) {
		this.adnum = adnum;
	}

	public List<HelianAd> getAds() {
		return ads;
	}

	public void setAds(List<HelianAd> ads) {
		this.ads = ads;
	}



}
