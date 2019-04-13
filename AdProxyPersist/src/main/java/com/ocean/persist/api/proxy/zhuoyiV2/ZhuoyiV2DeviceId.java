package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2DeviceId {
	private int type	;//required	int	id 类型1.IMEI
/*	2.MAC
	3.IDFA
	4.ANDROIDID
	5.IDFV
	Android: 1,2,4 required
	IOS: 2,3,5 required*/
	private String id;//	required	string	设备 ID，以明文传递
	public int getType() {
		return type;
	}
	public String getId() {
		return id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setId(String id) {
		this.id = id;
	}
}
