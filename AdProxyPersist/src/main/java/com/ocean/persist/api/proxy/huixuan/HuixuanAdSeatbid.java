package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdSeatbid      implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1159475746597161438L;
	private List<HuixuanAdBid> bid;
	private String seat;
	public List<HuixuanAdBid> getBid() {
		return bid;
	}
	public void setBid(List<HuixuanAdBid> bid) {
		this.bid = bid;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}

}
