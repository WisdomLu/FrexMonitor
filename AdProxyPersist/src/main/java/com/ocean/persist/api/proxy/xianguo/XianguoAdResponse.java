package com.ocean.persist.api.proxy.xianguo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class XianguoAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	
	private XinGuoV204.BidResponse response;

	public XinGuoV204.BidResponse getResponse() {
		return response;
	}

	public void setResponse(XinGuoV204.BidResponse response) {
		this.response = response;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
