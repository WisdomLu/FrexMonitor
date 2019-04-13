package com.ocean.persist.app.dis.qqDownloader;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月13日 
      @version 1.0 
 */
public class Terminal     implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3463927908417448861L;
    private  String androidId;
    private  String imei;
    private  String imsi;
    private  String macAddress;
    private  String manufacture;
    private  String mode;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getAndroidId() {
		return androidId;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

}
