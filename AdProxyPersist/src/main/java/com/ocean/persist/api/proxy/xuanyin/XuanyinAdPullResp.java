package com.ocean.persist.api.proxy.xuanyin;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class XuanyinAdPullResp   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 737355209746812448L;
	private int code ;//是 int 响应状态码 0：Success、4006：没有填充 
	private String msg;//;// 是 string 响应提示 
	private List<XuanyinAd> ads;// 否 ad array for object 广告内容 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public List<XuanyinAd> getAds() {
		return ads;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setAds(List<XuanyinAd> ads) {
		this.ads = ads;
	}

}
