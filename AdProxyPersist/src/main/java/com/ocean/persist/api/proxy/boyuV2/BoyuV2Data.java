package com.ocean.persist.api.proxy.boyuV2;

import java.util.List;

public class BoyuV2Data {
	private String requestId;//	string	0000400000401524109061178						请求的requestId	
				
	private long ts;//	long	1524109081892									返回广告时间戳	
				
	private List<BoyuV2Group> groups;//	array													广告组列表，所有规格广告列表	

	public String getRequestId() {
		return requestId;
	}

	public long getTs() {
		return ts;
	}

	public List<BoyuV2Group> getGroups() {
		return groups;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public void setGroups(List<BoyuV2Group> groups) {
		this.groups = groups;
	}
				
	
}
