package com.ocean.persist.app.dis.zs.appasyn;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class ZsAppasynReply  implements AppDisResponse{
	private List<ZsAppasynApp> list;

	public List<ZsAppasynApp> getList() {
		return list;
	}

	public void setList(List<ZsAppasynApp> list) {
		this.list = list;
	}
	
}
