package com.ocean.persist.api.proxy.wangyue;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月29日 
      @version 1.0 
 */
public class WangyueAdPullParams  extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 211339642721155736L;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String requestId;//	string	是	请求流水号，请求ID，前缀+媒体自定义	前缀我方定义：AA00000001
	private String versionId;//	string	是	我方接口版本号	目前写1.0
	private int batch	;//integer	是	0不支持 1支持	是否支持多条广告，如0
	private String adid	;//string	是	广告位ID	我方提供
	private WangyueSub subInfo;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public WangyueSub getSubInfo() {
		return subInfo;
	}
	public void setSubInfo(WangyueSub subInfo) {
		this.subInfo = subInfo;
	}
	

}
