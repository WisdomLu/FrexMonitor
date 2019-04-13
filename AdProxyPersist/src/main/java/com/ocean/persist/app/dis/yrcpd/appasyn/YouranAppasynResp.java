package com.ocean.persist.app.dis.yrcpd.appasyn;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class YouranAppasynResp   implements AppDisResponse{
	
	    private int RetCode;//": 0,
	    private String RetMessage;//": "成功",
	    private List<YouranAppasynApp> Info;
		public int getRetCode() {
			return RetCode;
		}
		public void setRetCode(int retCode) {
			RetCode = retCode;
		}
		public String getRetMessage() {
			return RetMessage;
		}
		public void setRetMessage(String retMessage) {
			RetMessage = retMessage;
		}
		public List<YouranAppasynApp> getInfo() {
			return Info;
		}
		public void setInfo(List<YouranAppasynApp> info) {
			Info = info;
		}
}
