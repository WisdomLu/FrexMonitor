package com.ocean.persist.api.proxy.adwo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月13日 
      @version 1.0 
 */
public class AdwoAdPullResponse    extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2046867455859046676L;
	private int resultcode ;//Y int 请求广告状态1 代表请求成功。其它均失败
	private String errorinfo;// N String 错误信息只有resultcode 不为1 时才有
	private AdwoAd ad ;//Y object 广告信息对象ad object 广告对象
	public int getResultcode() {
		return resultcode;
	}
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	public String getErrorinfo() {
		return errorinfo;
	}
	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}
	public AdwoAd getAd() {
		return ad;
	}
	public void setAd(AdwoAd ad) {
		this.ad = ad;
	}
}
