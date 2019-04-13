package com.ocean.persist.api.proxy.paerjiate_online;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class PaerjiateOLAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2917025517911300557L;
	private int errorCode;//":0,
	private String errMsg;//":"",
	private String requestId;//":"fad9772f-b5ff-4246-bf9f-7f772f561d20",
	private List<PaerjiateOLAd> ads;//
	private String adlogo;
	private String adtext;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public List<PaerjiateOLAd> getAds() {
		return ads;
	}
	public void setAds(List<PaerjiateOLAd> ads) {
		this.ads = ads;
	}
	public String getAdlogo() {
		return adlogo;
	}
	public void setAdlogo(String adlogo) {
		this.adlogo = adlogo;
	}
	public String getAdtext() {
		return adtext;
	}
	public void setAdtext(String adtext) {
		this.adtext = adtext;
	}
}
