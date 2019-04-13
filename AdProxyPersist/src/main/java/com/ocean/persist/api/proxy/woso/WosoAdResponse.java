package com.ocean.persist.api.proxy.woso;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年11月29日 
      @version 1.0 
 */
public class WosoAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3608745206186748969L;
	private String message;//返回数据的信息 ok为成功返回广告数据
	private int code;//返回值 非0时表示返回错误(见附录)
	private List<WosoAd> ads;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<WosoAd> getAds() {
		return ads;
	}
	public void setAds(List<WosoAd> ads) {
		this.ads = ads;
	}

}
