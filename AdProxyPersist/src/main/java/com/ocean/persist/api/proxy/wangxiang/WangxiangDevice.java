package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangDevice {
	private int device_type	;//设备类型	必填，1=PHONE  2=TABLET
	private int 		os_type	;//操作系统	必填，1=ANDROID  2=IOS
	private WangxiangVersion 		os_version	;//操作系统版本	必填，至少需要填写主版本号major
	private String 		vendor;//	设备厂商名称	必填，中文需要UTF-8 编码
	private String 		model ;//	设备型号	必填，中文需要UTF-8 编码
	private WangxinagSize 		screen_size;//	设备屏幕尺寸	必填 
	private WangxiangUdid 		udid	;//设备唯一标识	必填，务必填写真实信息，否则无法保证变现效果
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public int getOs_type() {
		return os_type;
	}
	public void setOs_type(int os_type) {
		this.os_type = os_type;
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
	public WangxiangVersion getOs_version() {
		return os_version;
	}
	public void setOs_version(WangxiangVersion os_version) {
		this.os_version = os_version;
	}
	public WangxinagSize getScreen_size() {
		return screen_size;
	}
	public void setScreen_size(WangxinagSize screen_size) {
		this.screen_size = screen_size;
	}
	public WangxiangUdid getUdid() {
		return udid;
	}
	public void setUdid(WangxiangUdid udid) {
		this.udid = udid;
	}

}
