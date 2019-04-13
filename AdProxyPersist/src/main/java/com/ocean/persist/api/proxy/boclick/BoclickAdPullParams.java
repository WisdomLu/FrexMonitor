package com.ocean.persist.api.proxy.boclick;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class BoclickAdPullParams    extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String zoneid;// 广告位ID
	private Integer width;// 广告位宽度
	private Integer height;// 广告位高度
	private String media;//	合作方名称
	private String layerstyle;// 返回数据类型
	private String phone_model;// 手机型号
	private String software_name;// 软件名称
	private String unid;// unid
	public String getZoneid() {
		return zoneid;
	}
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getLayerstyle() {
		return layerstyle;
	}
	public void setLayerstyle(String layerstyle) {
		this.layerstyle = layerstyle;
	}
	public String getPhone_model() {
		return phone_model;
	}
	public void setPhone_model(String phone_model) {
		this.phone_model = phone_model;
	}
	public String getSoftware_name() {
		return software_name;
	}
	public void setSoftware_name(String software_name) {
		this.software_name = software_name;
	}
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
