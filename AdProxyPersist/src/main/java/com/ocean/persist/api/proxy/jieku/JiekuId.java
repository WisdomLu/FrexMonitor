package com.ocean.persist.api.proxy.jieku;

import org.apache.commons.codec.digest.DigestUtils;

import com.ocean.core.common.base.AbstractBaseEntity;

public class JiekuId {

	private static final long serialVersionUID = 1L;
//	int id的类型 (1.IMEI 2.MAC 3.IDFA 6. ANDROIDID 7.IMSI)
//	注意：
//	android设备必须包含
//	IMEI，MAC，
//	ANDROIDID，IMSI
//	iOS设备必须包含
//	IDFA
	private Integer type;// required 
	private String id;// required string 设备ID，以明⽂文传递
	private String md5;// optional bool 是否md5过，默认没有md5，尽量传明⽂文
	
	public JiekuId() {
		super();
	}

	public JiekuId(Integer type, String id) {
		super();
		this.type = type;
		this.id = id;
		this.md5 = DigestUtils.md5Hex(id);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
