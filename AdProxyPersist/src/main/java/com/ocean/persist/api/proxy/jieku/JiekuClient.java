package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuClient {

	private static final long serialVersionUID = 1L;
	
	private Integer type;// required int 广告客户端类型(固定值 3)
	private JiekuVersion version;// required version object 版本(API接入目前版本是1.3.0.0)
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public JiekuVersion getVersion() {
		return version;
	}

	public void setVersion(JiekuVersion version) {
		this.version = version;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
