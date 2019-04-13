package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiDevice {

	private static final long serialVersionUID = 1L;
	
	private String ua;// optional 	user agent，在请求头中已经包含，可以省略 
	private String ip;// required 	设备的ipv4地址 
	private String iem;// recommended 	设备imei值，我们用它填充BidRequest中的did 
	private String ifa;// recommended 	iOS设备的idfa，我们用它来填充BidRequest中的 dpid 
	private String gpid;//  	Advertising ID 如果没有 传 o1
	private String o1;// 
	private Integer connectiontype;
	private String carrier; 
	private InmobiGeo geo;// 经纬度
	private InmobiDeviceExt ext;
	
	//new
	private String md5_imei;
	private String sha1_imei;
	private String um5;
	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIfa() {
		return ifa;
	}

	public void setIfa(String ifa) {
		this.ifa = ifa;
	}

	public String getGpid() {
		return gpid;
	}

	public void setGpid(String gpid) {
		this.gpid = gpid;
	}

	public String getO1() {
		return o1;
	}

	public void setO1(String o1) {
		this.o1 = o1;
	}

	public Integer getConnectiontype() {
		return connectiontype;
	}

	public void setConnectiontype(Integer connectiontype) {
		this.connectiontype = connectiontype;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public InmobiGeo getGeo() {
		return geo;
	}

	public void setGeo(InmobiGeo geo) {
		this.geo = geo;
	}

	public InmobiDeviceExt getExt() {
		return ext;
	}

	public void setExt(InmobiDeviceExt ext) {
		this.ext = ext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMd5_imei() {
		return md5_imei;
	}

	public void setMd5_imei(String md5_imei) {
		this.md5_imei = md5_imei;
	}

	public String getSha1_imei() {
		return sha1_imei;
	}

	public void setSha1_imei(String sha1_imei) {
		this.sha1_imei = sha1_imei;
	}

	public String getUm5() {
		return um5;
	}

	public void setUm5(String um5) {
		this.um5 = um5;
	}

	public String getIem() {
		return iem;
	}

	public void setIem(String iem) {
		this.iem = iem;
	}
}
