package com.ocean.persist.app.dis.yrcpd.pkgsearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class YouranPkgSearchResp   implements AppDisResponse{
    private int RetCode;//": 0,
    private String RetMessage;//": "成功",
    private List<YouranPkgSearchApp> Info;
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
	public List<YouranPkgSearchApp> getInfo() {
		return Info;
	}
	public void setInfo(List<YouranPkgSearchApp> info) {
		Info = info;
	}
}
