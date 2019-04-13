package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;

public class JiekuBrowser {

	private static final long serialVersionUID = 1L;
	
	private String user_agent;// required string 浏览器的UA

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
