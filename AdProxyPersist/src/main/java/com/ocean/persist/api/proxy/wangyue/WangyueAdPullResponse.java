package com.ocean.persist.api.proxy.wangyue;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月29日 
      @version 1.0 
 */
public class WangyueAdPullResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1465598002386061260L;
	private String requestId;//	String	是	对应请求的ID	CB000001
	private String adid;//	String	是	广告位ID	
	private String returnCode;//	String	是	返回标识CODE（见文档以下code枚信息8.1）	
	private String returnMsg;//	String	是	返回信息	
	private List<WangyueAd> ads	;//array	是	广告数组（当请求batch位1的时，ads数组可能为多个）	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public List<WangyueAd> getAds() {
		return ads;
	}
	public void setAds(List<WangyueAd> ads) {
		this.ads = ads;
	}

}
