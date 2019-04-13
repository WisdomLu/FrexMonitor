package com.ocean.persist.api.proxy.jugao;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
public class JugaoAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109496218843554773L;
    private String returncode;
    private String adid;//广告创意ID
    private String adnum;//返回广告数量
    private List<JugaoAd> ads;
	public String getReturncode() {
		return returncode;
	}
	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getAdnum() {
		return adnum;
	}
	public void setAdnum(String adnum) {
		this.adnum = adnum;
	}
	public List<JugaoAd> getAds() {
		return ads;
	}
	public void setAds(List<JugaoAd> ads) {
		this.ads = ads;
	}
   
    
}
