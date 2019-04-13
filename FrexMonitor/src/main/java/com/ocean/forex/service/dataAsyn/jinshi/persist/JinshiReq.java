package com.ocean.forex.service.dataAsyn.jinshi.persist;

import com.ocean.core.common.threadpool.Parameter;

public class JinshiReq  implements Parameter {
	private String type;
	private long _;
	public String getType() {
		return type;
	}
	public long get_() {
		return _;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void set_(long _) {
		this._ = _;
	}
}
