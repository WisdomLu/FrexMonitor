package com.ocean.persist.app.dis.youyou;

import com.ocean.persist.app.dis.AdDisParams;

public class YouyouReportData extends BaseYouyouReq implements AdDisParams {

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	// 动态参数
	private String eventTimeStart;
	private String eventTimeEnd;
	private String offsetX;
	private String offsetY;
	private String key;
	private String dot_type;
	private String pnames;

	public String getEventTimeStart() {
		return eventTimeStart;
	}

	public void setEventTimeStart(String eventTimeStart) {
		this.eventTimeStart = eventTimeStart;
	}

	public String getEventTimeEnd() {
		return eventTimeEnd;
	}

	public void setEventTimeEnd(String eventTimeEnd) {
		this.eventTimeEnd = eventTimeEnd;
	}

	public String getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(String offsetX) {
		this.offsetX = offsetX;
	}

	public String getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(String offsetY) {
		this.offsetY = offsetY;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDot_type() {
		return dot_type;
	}

	public void setDot_type(String dot_type) {
		this.dot_type = dot_type;
	}

	public String getPnames() {
		return pnames;
	}

	public void setPnames(String pnames) {
		this.pnames = pnames;
	}

}
