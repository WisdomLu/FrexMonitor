package com.ocean.persist.api.proxy.flying;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class FlyingAdPullResponse extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4903010867870555993L;
	private String strApiVersion;//: api 版本
	private int intError;//：错误码，0 为成功，非0 则为失败
	private String strMsg;//：错误描述
	private int intAdType;//:广告类型
	private int intADCount;//：返回的广告数
	private List<FlyingAd> objAds;//：广告列表
	public String getStrApiVersion() {
		return strApiVersion;
	}
	public void setStrApiVersion(String strApiVersion) {
		this.strApiVersion = strApiVersion;
	}
	public int getIntError() {
		return intError;
	}
	public void setIntError(int intError) {
		this.intError = intError;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public int getIntAdType() {
		return intAdType;
	}
	public void setIntAdType(int intAdType) {
		this.intAdType = intAdType;
	}
	public int getIntADCount() {
		return intADCount;
	}
	public void setIntADCount(int intADCount) {
		this.intADCount = intADCount;
	}
	public List<FlyingAd> getObjAds() {
		return objAds;
	}
	public void setObjAds(List<FlyingAd> objAds) {
		this.objAds = objAds;
	}
}
