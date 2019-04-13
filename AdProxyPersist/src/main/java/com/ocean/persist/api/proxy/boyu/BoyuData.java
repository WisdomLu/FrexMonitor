package com.ocean.persist.api.proxy.boyu;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年4月4日 
      @version 1.0 
 */
public class BoyuData {
	private List<BoyuAd> adList;
	private String requestId;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public List<BoyuAd> getAdList() {
		return adList;
	}
	public void setAdList(List<BoyuAd> adList) {
		this.adList = adList;
	}
}
