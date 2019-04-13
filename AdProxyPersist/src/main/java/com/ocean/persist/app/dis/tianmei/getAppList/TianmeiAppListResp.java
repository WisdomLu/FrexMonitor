package com.ocean.persist.app.dis.tianmei.getAppList;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuResp;

public class TianmeiAppListResp    implements AppDisResponse{
	private  int result	;//number	是	结果状态,0成功,其他失败
	private String msg	;//string	是	状态说明

	private List<TianmeiApp> adms	;//Array	是	广告列表，可能为空[]
	public int getResult() {
		return result;
	}
	public String getMsg() {
		return msg;
	}

	public List<TianmeiApp> getAdms() {
		return adms;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setAdms(List<TianmeiApp> adms) {
		this.adms = adms;
	}

}
