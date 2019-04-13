package com.ocean.persist.api.proxy.jieku;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuDevice {

	private static final long serialVersionUID = 1L;
	// required int 设备类型 0. DEV_UNKONWN 1.PC 2.DEV_PHONE 3.TABLER 4.TV
	private Integer type;
	//  required array of id object 设备ID的标识
	private List<JiekuId> ids;
	//  required int 操作系统类型(0.OS_UNKNOWN 1.ANDROID 2.IOS 3.WP)
	private Integer os_type;
	//  required version object 操作系统版本（最少填2位，注意类型是version object）
	private JiekuVersion os_version;
	// optional string 设备品牌
	private String brand;
	// optional string 设备型号
	private String model;
	// required size object 屏幕尺⼨寸
	private JiekuSize screen_size;
	// screen_density optional double 屏幕像素密度
	private Double screen_density;
	
	public Double getScreen_density() {
		return screen_density;
	}

	public void setScreen_density(Double screen_density) {
		this.screen_density = screen_density;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<JiekuId> getIds() {
		return ids;
	}

	public void setIds(List<JiekuId> ids) {
		this.ids = ids;
	}

	public Integer getOs_type() {
		return os_type;
	}

	public void setOs_type(Integer os_type) {
		this.os_type = os_type;
	}

	public JiekuVersion getOs_version() {
		return os_version;
	}

	public void setOs_version(JiekuVersion os_version) {
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

	public JiekuSize getScreen_size() {
		return screen_size;
	}

	public void setScreen_size(JiekuSize screen_size) {
		this.screen_size = screen_size;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
