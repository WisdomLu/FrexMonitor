package com.ocean.persist.api.proxy.shenmi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class ShenmiAdReport {

	private static final long serialVersionUID = 1L;

	private String reporttime;
	
	private String reporturl;

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getReporturl() {
		return reporturl;
	}

	public void setReporturl(String reporturl) {
		this.reporturl = reporturl;
	}
	
}
