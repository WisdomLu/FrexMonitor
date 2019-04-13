package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangUdid {
	private String idfa ;//	iOS 设备唯一标识码	iOS 必填，广告定向依赖ID
	private String imei	 ;//Android设备唯一标识码	安卓必填，广告定向依赖ID
	private String android_id	 ;//Android设备ID	安卓必填
	private String 	mac ;//	Mac地址	必填
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

}
