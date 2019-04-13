package com.ocean.persist.api.proxy.boclick;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class BoclickAdData  implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	
	private List<BoclickAdContent> content;
	
	private String pos;

	public List<BoclickAdContent> getContent() {
		return content;
	}

	public void setContent(List<BoclickAdContent> content) {
		this.content = content;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}
}
