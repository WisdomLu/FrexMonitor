package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqPmp {

	// required  object  Deal信息，默认只有1个deal属性
	private PairuiBidReqPmpDeals deals;

	public PairuiBidReqPmpDeals getDeals() {
		return deals;
	}

	public void setDeals(PairuiBidReqPmpDeals deals) {
		this.deals = deals;
	}
}
