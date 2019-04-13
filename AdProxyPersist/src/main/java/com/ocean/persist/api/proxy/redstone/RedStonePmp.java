package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStonePmp   {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3028315192643999700L;
	private int private_auction;
	private RedStoneDeal deals;
	private RedStoneExt ext;
	public int getPrivate_auction() {
		return private_auction;
	}
	public void setPrivate_auction(int private_auction) {
		this.private_auction = private_auction;
	}
	public RedStoneDeal getDeals() {
		return deals;
	}
	public void setDeals(RedStoneDeal deals) {
		this.deals = deals;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
