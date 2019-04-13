package com.ocean.persist.api.proxy.pairui;

import java.util.List;

public class PairuiRespSeat {

	// optional object array  竞价信息，根据媒体实际情况，决定返回1个或多个bids
	private List<PairuiRespBid> bids;

	public List<PairuiRespBid> getBids() {
		return bids;
	}

	public void setBids(List<PairuiRespBid> bids) {
		this.bids = bids;
	}
}
 