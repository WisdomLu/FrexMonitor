package com.ocean.persist.api.proxy.qidian;

import java.util.List;

public class QidianDataV2 {
	private String requestId;// string 0000400000401524109061178 请求的requestId
	private long ts;// long 1524109081892 返回广告时间戳
	private List<QidianGroup> groups;
	public String getRequestId() {
		return requestId;
	}
	public long getTs() {
		return ts;
	}
	public List<QidianGroup> getGroups() {
		return groups;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public void setGroups(List<QidianGroup> groups) {
		this.groups = groups;
	}
}
