package com.ocean.persist.api.proxy.diankai;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月24日 
      @version 1.0 
 */
public class DiankaiAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6916180399236086973L;
    private List<DiankaiAd> ads;
    private int code;
    private String message;
    private String version;
	public List<DiankaiAd> getAds() {
		return ads;
	}
	public void setAds(List<DiankaiAd> ads) {
		this.ads = ads;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
