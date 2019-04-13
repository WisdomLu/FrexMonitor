package com.ocean.persist.api.proxy.xunfei;

import java.util.List;

/**
 * App
 */
public class XunfeiPmp  {

	public List<XunfeiDealObject> getDeals() {
		return deals;
	}
	public void setDeals(List<XunfeiDealObject> deals) {
		this.deals = deals;
	}
	private List<XunfeiDealObject> deals;
}
