package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuAdslot {

	private static final long serialVersionUID = 1L;
	
	private String id;// required string 广告位ID，由捷酷提供
	private Integer type;// required int 广告位类型 1.BANNER(横幅) 4.INITIALIZATION(开屏) 5.INSERT(插屏) 9.NATIVE(信息流)
	private JiekuSize size;// required size object ⼲⼴广告位尺⼨寸
	private Integer capacity;// optional int 广告容量(默认为1)
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public JiekuSize getSize() {
		return size;
	}

	public void setSize(JiekuSize size) {
		this.size = size;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
