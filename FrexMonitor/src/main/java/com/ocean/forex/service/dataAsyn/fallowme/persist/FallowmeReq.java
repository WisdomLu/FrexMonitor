package com.ocean.forex.service.dataAsyn.fallowme.persist;

import com.ocean.core.common.threadpool.Parameter;

public class FallowmeReq  implements Parameter {
	private String symbol;//=USDJPY
	private int resolution;//=1,级别，是1分钟还是5分中
	private int from;//=1545534620,开始时间，单位秒
	private int to=1545634620;//结束时间
	private int brokerId;
	public String getSymbol() {
		return symbol;
	}
	public int getResolution() {
		return resolution;
	}
	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}
}
