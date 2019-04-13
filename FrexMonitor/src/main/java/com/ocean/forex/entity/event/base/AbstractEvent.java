package com.ocean.forex.entity.event.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractEvent {
	private String beginDate;
	private String endDate;
	private String name;
	private String desc;
	private static final String strDateFormat= "yyyy-MM-dd HH:mm:ss";
	public String getDateStr(){
		SimpleDateFormat sdf=new SimpleDateFormat(strDateFormat);
		return sdf.format(new Date());
	}
	public String getBeginDate() {
		return beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
