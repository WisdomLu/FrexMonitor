package com.ocean.persist.api.proxy.xianguo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;


public class XianguoAdPullParams   extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;
	
	private XinGuoV204.BidRequest bidRequest;

	public XinGuoV204.BidRequest getBidRequest() {
		return bidRequest;
	}

	public void setBidRequest(XinGuoV204.BidRequest bidRequest) {
		this.bidRequest = bidRequest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	
}
