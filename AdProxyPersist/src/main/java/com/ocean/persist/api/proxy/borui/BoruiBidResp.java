package com.ocean.persist.api.proxy.borui;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class BoruiBidResp extends AbstractBaseEntity  implements AdPullResponse {

	private static final long serialVersionUID = -2717532576771025719L;

	//此 ID 就是广告请求的 id--合作方唯一 ID
	private String id;
	
	
	private List<BoruiBidRespBid> bids;
	
	//错误码
	/****
	 * JSON  错误码 值 解释
		0  正常返回
		1000 无广告返回
		2000 配置信息不正确
		3000 API 版本号信息错误
		4000 Device 对象配置错误
		5000 AdSlot 对象配置错误
		
		E HTTPCODE  状态码 值 解释
		202 此请求没有合适的广告源
		204 请求字符串不符合要求
		207 此流量源没有配置广告源
		209 此流量源未配置广告
	 * 
	 * 
	 * 
	 * 
	 */
	private Integer error_code;
	
	//出价 单位(分)
	private Integer bidprice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<BoruiBidRespBid> getBids() {
		return bids;
	}

	public void setBids(List<BoruiBidRespBid> bids) {
		this.bids = bids;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public Integer getBidprice() {
		return bidprice;
	}

	public void setBidprice(Integer bidprice) {
		this.bidprice = bidprice;
	}
}
