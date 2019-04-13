package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuVersion {

	private static final long serialVersionUID = 1L;
	
	private String major;// required uint32 主版本号
	private String minor;// optional uint32 次版本号
	private String micro;// optional uint32 三位版本号
	private String build;// optional uint32 四位版本号
	
	public JiekuVersion() {
		super();
	}
	
	public JiekuVersion(String major) {
		super();
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getMicro() {
		return micro;
	}

	public void setMicro(String micro) {
		this.micro = micro;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
