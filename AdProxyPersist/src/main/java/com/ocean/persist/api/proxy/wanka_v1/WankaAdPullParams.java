package com.ocean.persist.api.proxy.wanka_v1;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class WankaAdPullParams    extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2708743593679915913L;
	private String reqjson;/*
	String
	是
	广告请求字段，值为 json 格式。并 使用 URLencode.字段说明详见 API请求字段。*/
	private String channel_id;/*
	String
	是
	渠道 ID.与运营人员联系获取*/
	private String signature;/*
	String
	是
	请求秘钥，用于用户验证。生成规 则详见 4 获取广告的签名算法*/
	private long timestamp;/*
	long
	是
	时间戳*/
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getReqjson() {
		return reqjson;
	}

	public void setReqjson(String reqjson) {
		this.reqjson = reqjson;
	}

}
