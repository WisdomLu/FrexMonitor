package com.ocean.persist.app.dis.yrcpd.appasyn;

import com.ocean.persist.app.dis.AdDisParams;

public class YouranAppasynReq   implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String app_id;//	string	由悠燃分配值 	是	310000
	private String hz_id;//	string	由悠燃分配值 ：	是	hz_id_00008
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getHz_id() {
		return hz_id;
	}
	public void setHz_id(String hz_id) {
		this.hz_id = hz_id;
	}
}
