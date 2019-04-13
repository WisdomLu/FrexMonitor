package com.ocean.persist.api.proxy.huzhong;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
public class HuzhongAdDevice    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 763607726202205685L;
	private String udid;
	private String identify_type;
	private String android_id;
	private String mac;
	private String vendor;
	private String model;
	private int os;
	private String os_version;
	private int network;
	private int operator;
	private String density;
	private int width;
	private int height;
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getIdentify_type() {
		return identify_type;
	}
	public void setIdentify_type(String identify_type) {
		this.identify_type = identify_type;
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
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

}
