package com.ocean.persist.api.proxy.wangxiang;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;



/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangAdPullResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3489450389537005646L;
	private int error_code;//	响应状态码
	private String request_id;//	请求id，唯一标识一次请求
	private WangxiangAd wxad	;//广告内容

	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public WangxiangAd getWxad() {
		return wxad;
	}
	public void setWxad(WangxiangAd wxad) {
		this.wxad = wxad;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}



}
