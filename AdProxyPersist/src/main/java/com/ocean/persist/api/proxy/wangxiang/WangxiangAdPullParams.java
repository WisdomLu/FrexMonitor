package com.ocean.persist.api.proxy.wangxiang;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangAdPullParams   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 693067971370015830L;
	private String sign	;//校验签名，通过md5(key+apptoken)
	private String  apptoken	;//App唯一标识
	private WangxiangData data		;//Json 业务参数

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getApptoken() {
		return apptoken;
	}
	public void setApptoken(String apptoken) {
		this.apptoken = apptoken;
	}
	public WangxiangData getData() {
		return data;
	}
	public void setData(WangxiangData data) {
		this.data = data;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
