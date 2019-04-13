package com.ocean.persist.api.proxy.yidianzx;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class YidianzxResp extends AbstractBaseEntity implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5911339560150685897L;
	private String id;// string 是对应BidRequest 中的ID
	private List<YidianzxSeatBid> seatBid;// object array 否DSP 竞价信息，如果出价会固定返回一个
	private String bidid ;// string 否DSP 产生的响应ID，用于日志与追踪
	private int processtime ;// int 否DSP 处理时间，单位（ms）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBidid() {
		return bidid;
	}
	public void setBidid(String bidid) {
		this.bidid = bidid;
	}
	public int getProcesstime() {
		return processtime;
	}
	public void setProcesstime(int processtime) {
		this.processtime = processtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<YidianzxSeatBid> getSeatBid() {
		return seatBid;
	}
	public void setSeatBid(List<YidianzxSeatBid> seatBid) {
		this.seatBid = seatBid;
	}
}
