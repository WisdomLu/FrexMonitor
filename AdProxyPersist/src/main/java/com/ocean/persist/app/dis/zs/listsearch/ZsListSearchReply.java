package com.ocean.persist.app.dis.zs.listsearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class ZsListSearchReply implements AppDisResponse {
	private List<ZsListSearchApp> list;

	public List<ZsListSearchApp> getLsit() {
		return list;
	}

	public void setLsit(List<ZsListSearchApp> lsit) {
		this.list = lsit;
	}
}
