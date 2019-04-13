package com.ocean.proxy.api.base;

import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.thrift.entity.AdRecomReq;

public class ProxyInvokeAttribute {

	private AdRecomReq adreq;
	
	private DSPPosition dspPosition;

	public ProxyInvokeAttribute(AdRecomReq adreq, DSPPosition dspPosition){
		this.adreq = adreq;
		this.dspPosition = dspPosition;
	}
	
	public AdRecomReq getAdreq() {
		return adreq;
	}

	public void setAdreq(AdRecomReq adreq) {
		this.adreq = adreq;
	}

	public DSPPosition getDspPosition() {
		return dspPosition;
	}

	public void setDspPosition(DSPPosition dspPosition) {
		this.dspPosition = dspPosition;
	}
}
