package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

public class ZhuoyiV2Device {
	private int type;
	/*	required	int	设备类型0.DEV_UNKNOWN
	1.DEV_PHONE
	2.PC 3.TABLET 4.TV*/
	private List<ZhuoyiV2DeviceId> ids	;//required	array of id object	设备 ID 的标识
	private int os_type	;//required	int	操作系统类型( 0.UNKNOWN
/*	1.ANDROID
	2.IOS
	3.WP）*/
	private ZhuiyiV2Version os_version	;//required	version object	操作系统版本
	private String brand	;//required	string	设备品牌
	private String  model	;//required	string	设备型号
	private ZhuoyiV2Size screen_size	;//required	size object	屏幕尺寸
	private double screen_density	;//optional	double	屏幕像素密度
	public int getType() {
		return type;
	}
	public List<ZhuoyiV2DeviceId> getIds() {
		return ids;
	}
	public int getOs_type() {
		return os_type;
	}
	public ZhuiyiV2Version getOs_version() {
		return os_version;
	}
	public String getBrand() {
		return brand;
	}
	public ZhuoyiV2Size getScreen_size() {
		return screen_size;
	}
	public double getScreen_density() {
		return screen_density;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setIds(List<ZhuoyiV2DeviceId> ids) {
		this.ids = ids;
	}
	public void setOs_type(int os_type) {
		this.os_type = os_type;
	}
	public void setOs_version(ZhuiyiV2Version os_version) {
		this.os_version = os_version;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setScreen_size(ZhuoyiV2Size screen_size) {
		this.screen_size = screen_size;
	}
	public void setScreen_density(double screen_density) {
		this.screen_density = screen_density;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}
