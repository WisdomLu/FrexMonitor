package com.ocean.persist.app.dis.yrcpd_t2.pkgsearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class YouranT2PkgSearchResp   implements AppDisResponse{
    private int RetCode;//": 0,
    private String RetMessage;//": "成功",
    private List<YouranT2PkgSearchApp> Info;
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
	public List<YouranT2PkgSearchApp> getInfo() {
		return Info;
	}
	public void setInfo(List<YouranT2PkgSearchApp> info) {
		Info = info;
	}
}
