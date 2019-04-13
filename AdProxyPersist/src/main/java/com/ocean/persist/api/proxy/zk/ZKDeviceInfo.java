package com.ocean.persist.api.proxy.zk;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/**
 * 
 * @Description: 设备信息
 * @author yunhui.zhang
 * @date 2016年8月3日 上午11:35:38
 */
public class ZKDeviceInfo    {
//	/** 设备唯一标识 */
//	private String deviceId;
//
//	/** 设备标识类型-IMEI/IDFA/AAID... */
//	private DeviceIDType deviceIdType;
//
//	/** 设备标识加密方式 */
//	private DeviceIDHashType deviceIdHashType;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -264592929151398176L;

	private List<ZKDeviceID> device_id;

	/** 操作系统 */
	private int os_type;

	/** 操作系统版本 */
	private String os_version;

	/** 设备品牌 */
	private String brand;

	/** 设备型号 */
	private String model;

	/** 设备类型， 平板or手机 */
	private int device_type;

	/** 设备语言 */
	private String language;

	/** 屏幕宽度像素 */
	private int screen_width;

	/** 屏幕高度像素 */
	private int screen_height;

	/** 屏幕分辨率 */
	private double screen_density;
	
	private String os_api_level;

	public List<ZKDeviceID> getDevice_id() {
		return device_id;
	}

	public void setDevice_id(List<ZKDeviceID> device_id) {
		this.device_id = device_id;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getScreen_width() {
		return screen_width;
	}

	public void setScreen_width(int screen_width) {
		this.screen_width = screen_width;
	}

	public int getScreen_height() {
		return screen_height;
	}

	public void setScreen_height(int screen_height) {
		this.screen_height = screen_height;
	}

	public double getScreen_density() {
		return screen_density;
	}

	public void setScreen_density(double screen_density) {
		this.screen_density = screen_density;
	}

	public int getOs_type() {
		return os_type;
	}

	public void setOs_type(int os_type) {
		this.os_type = os_type;
	}

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

	public String getOs_api_level() {
		return os_api_level;
	}

	public void setOs_api_level(String os_api_level) {
		this.os_api_level = os_api_level;
	}

}
