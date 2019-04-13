package com.ocean.persist.api.proxy.qidian;

public class QidianSplashInfo {

	private int countdown;
	private int maxPerDay ;
	private int interval ;
	private long startTime ;
	private long endTime;
	private int type ;
	public int getCountdown() {
		return countdown;
	}
	public int getMaxPerDay() {
		return maxPerDay;
	}
	public int getInterval() {
		return interval;
	}
	public long getStartTime() {
		return startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public int getType() {
		return type;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	public void setMaxPerDay(int maxPerDay) {
		this.maxPerDay = maxPerDay;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public void setType(int type) {
		this.type = type;
	}
}
