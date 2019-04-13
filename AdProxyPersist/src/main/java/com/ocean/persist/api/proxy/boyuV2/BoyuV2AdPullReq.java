package com.ocean.persist.api.proxy.boyuV2;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class BoyuV2AdPullReq   extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4456681646302606412L;
	private String requestId;//	string	Y	0000100000021459220140766	platformId+posId+timeStamp，详见2.1.1.1	
	private String auth;//		string	Y	D715B7F1565A94592C6D90C7B5460327	鉴权字符串，详见2.1.1.3								
	private BoyuV2Device device	;//	object	Y		设备相关信息									
	private List<BoyuV2Imp> imps;//		list	Y		广告位请求信息，一个广告请求对应对应一个组广告信	
					//息，至少应该填一个，目前暂时仅支持一个广告请求	
	private BoyuV2App app	;//	object	N		app信息		
	private String keyword	;//	string	N		请求关键词，如果不为空，系统将根据该关键词匹配广告	
						
	//渠道标识,是媒体针对自己不同的流量来源（例如不同的	
	private String channel	;//	string	N		渠道包）进行区分的一个字段，后续版本会在媒体的	
	//结算数据报表里增加按channel区分的结算数
	private String version;//		string	N		版本号	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getRequestId() {
		return requestId;
	}
	public String getAuth() {
		return auth;
	}
	public BoyuV2Device getDevice() {
		return device;
	}
	public List<BoyuV2Imp> getImps() {
		return imps;
	}
	public BoyuV2App getApp() {
		return app;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getChannel() {
		return channel;
	}
	public String getVersion() {
		return version;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public void setDevice(BoyuV2Device device) {
		this.device = device;
	}
	public void setImps(List<BoyuV2Imp> imps) {
		this.imps = imps;
	}
	public void setApp(BoyuV2App app) {
		this.app = app;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
