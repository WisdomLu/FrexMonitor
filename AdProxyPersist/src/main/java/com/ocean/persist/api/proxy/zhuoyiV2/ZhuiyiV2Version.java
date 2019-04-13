package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuiyiV2Version {
	private int major	;//required	unit32	主版本号
	private int minor	;//optional	unit32	次级版本号
	private int micro	;//optional	unit32	三位版本号
	private int build	;//optional	unit32	四位版本号
	public int getMajor() {
		return major;
	}
	public int getMinor() {
		return minor;
	}
	public int getMicro() {
		return micro;
	}
	public int getBuild() {
		return build;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public void setMicro(int micro) {
		this.micro = micro;
	}
	public void setBuild(int build) {
		this.build = build;
	}
}
