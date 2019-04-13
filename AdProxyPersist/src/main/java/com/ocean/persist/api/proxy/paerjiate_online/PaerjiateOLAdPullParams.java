package com.ocean.persist.api.proxy.paerjiate_online;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class PaerjiateOLAdPullParams extends AbstractInvokeParameter{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = -3689083495385626305L;
	private PaerjiateOLReqInfo  reqInfo;
	  private PaerjiateOLAdslot adSlotInfo;
	  private PaerjiateOLDevice mobileInfo;
	  private PaerjiateOLNet networkInfo;
	  private PaerjiateOLGps coordinateInfo;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public PaerjiateOLReqInfo getReqInfo() {
		return reqInfo;
	}

	public void setReqInfo(PaerjiateOLReqInfo reqInfo) {
		this.reqInfo = reqInfo;
	}

	public PaerjiateOLAdslot getAdSlotInfo() {
		return adSlotInfo;
	}

	public void setAdSlotInfo(PaerjiateOLAdslot adSlotInfo) {
		this.adSlotInfo = adSlotInfo;
	}

	public PaerjiateOLDevice getMobileInfo() {
		return mobileInfo;
	}

	public void setMobileInfo(PaerjiateOLDevice mobileInfo) {
		this.mobileInfo = mobileInfo;
	}

	public PaerjiateOLNet getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(PaerjiateOLNet networkInfo) {
		this.networkInfo = networkInfo;
	}

	public PaerjiateOLGps getCoordinateInfo() {
		return coordinateInfo;
	}

	public void setCoordinateInfo(PaerjiateOLGps coordinateInfo) {
		this.coordinateInfo = coordinateInfo;
	}
}
