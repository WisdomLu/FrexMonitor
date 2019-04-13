package com.ocean.persist.api.proxy.wanyueV2;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class WanyueV2AdPullResp   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9160330431769408791L;
	private String rc	;//返回码	string		是	
	private String msg	;//返回消息	string		是	
	private List<WanyueV2Data> data	;//数据节点	array		是	
	private String extra_data	;//回传数据	string		否	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRc() {
		return rc;
	}
	public String getMsg() {
		return msg;
	}
	public String getExtra_data() {
		return extra_data;
	}
	public void setRc(String rc) {
		this.rc = rc;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setExtra_data(String extra_data) {
		this.extra_data = extra_data;
	}
	public List<WanyueV2Data> getData() {
		return data;
	}
	public void setData(List<WanyueV2Data> data) {
		this.data = data;
	}
}
