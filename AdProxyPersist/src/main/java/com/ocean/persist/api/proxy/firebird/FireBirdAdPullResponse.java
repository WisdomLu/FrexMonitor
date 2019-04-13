package com.ocean.persist.api.proxy.firebird;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月19日 
      @version 1.0 
 */
public class FireBirdAdPullResponse extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6809981785328230886L;
	private int rspCode;
	private String rspMsg;
	private int sign;
	private List<FireBirdAd> rspObject ;
	public int getRspCode() {
		return rspCode;
	}
	public void setRspCode(int rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspMsg() {
		return rspMsg;
	}
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<FireBirdAd> getRspObject() {
		return rspObject;
	}
	public void setRspObject(List<FireBirdAd> rspObject) {
		this.rspObject = rspObject;
	}
	
}
