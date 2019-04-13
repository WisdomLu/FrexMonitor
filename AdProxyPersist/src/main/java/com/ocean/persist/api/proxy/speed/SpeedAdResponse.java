package com.ocean.persist.api.proxy.speed;
import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


public class SpeedAdResponse  extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1403136931834666247L;
    private int error_code;
    private String msg;
    private String version;//api版本
    private List<SpeedAd> ad;
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<SpeedAd> getAd() {
		return ad;
	}
	public void setAd(List<SpeedAd> ad) {
		this.ad = ad;
	}
   
}
