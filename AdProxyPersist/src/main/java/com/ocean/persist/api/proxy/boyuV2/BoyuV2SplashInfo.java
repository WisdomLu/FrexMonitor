package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2SplashInfo {
	private int countdown;//	int	2											开屏广告建议倒计时时间（单位秒），非开屏广	告为-1			
	private int maxPerDay;//	int	1											开屏广告建议一天重复显示最大次数，非开屏广	告为-1	字段名	类型	示例	说明	
	private int interval;//	int	100	开屏广告建议两次相同广告出现最小间隔（单位	秒），非开屏广告为-1	开屏广告计划开始时间，此时间前不要展示此广	
	private long startTime;//	long		告，值为时间戳（从1970.1.1至今毫秒数），非	开屏广告为-1	开屏广告计划结束时间，此时间后不要展示此广	
	private long endTime;//	long		告，值为时间戳（从1970.1.1至今毫秒数），非	开屏广告为-1	
	private int type	;//int	1	开屏广告类型 1 全屏 2 半屏，非开屏广告为-1	
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
