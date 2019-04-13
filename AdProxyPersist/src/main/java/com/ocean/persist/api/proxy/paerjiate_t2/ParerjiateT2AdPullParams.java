package com.ocean.persist.api.proxy.paerjiate_t2;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class ParerjiateT2AdPullParams extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8430642433455486477L;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String request_id;
	private int adcount ;
	private int count ;
	private String posid;
	private String datafmt;
	private String ua ;
	private String vos ;
	private int os ;
	private int muidtype ;
	private String muid ;
	private int conn ;
	private int carrier;
	private int cw ;
	private int ch ;
	private String vendor ;
	private String model ;
	private int tdevice ;
	private int adype;
	private String vapi ;
	private int cori ;
	private String mac ;
	private String anid ;
	private String ip ;
	private String density ;
	private String lat ;
	private String lng ;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRequest_id() {
		return request_id;
	}
	public int getAdcount() {
		return adcount;
	}
	public int getCount() {
		return count;
	}
	public String getPosid() {
		return posid;
	}
	public String getDatafmt() {
		return datafmt;
	}
	public String getUa() {
		return ua;
	}
	public String getVos() {
		return vos;
	}
	public int getOs() {
		return os;
	}
	public int getMuidtype() {
		return muidtype;
	}
	public String getMuid() {
		return muid;
	}
	public int getConn() {
		return conn;
	}
	public int getCarrier() {
		return carrier;
	}
	public int getCw() {
		return cw;
	}
	public int getCh() {
		return ch;
	}
	public String getVendor() {
		return vendor;
	}
	public String getModel() {
		return model;
	}
	public int getTdevice() {
		return tdevice;
	}
	public int getAdype() {
		return adype;
	}
	public String getVapi() {
		return vapi;
	}
	public int getCori() {
		return cori;
	}
	public String getMac() {
		return mac;
	}
	public String getAnid() {
		return anid;
	}
	public String getIp() {
		return ip;
	}
	public String getDensity() {
		return density;
	}
	public String getLat() {
		return lat;
	}
	public String getLng() {
		return lng;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public void setAdcount(int adcount) {
		this.adcount = adcount;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public void setDatafmt(String datafmt) {
		this.datafmt = datafmt;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public void setVos(String vos) {
		this.vos = vos;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public void setMuidtype(int muidtype) {
		this.muidtype = muidtype;
	}
	public void setMuid(String muid) {
		this.muid = muid;
	}
	public void setConn(int conn) {
		this.conn = conn;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public void setCw(int cw) {
		this.cw = cw;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setTdevice(int tdevice) {
		this.tdevice = tdevice;
	}
	public void setAdype(int adype) {
		this.adype = adype;
	}
	public void setVapi(String vapi) {
		this.vapi = vapi;
	}
	public void setCori(int cori) {
		this.cori = cori;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setAnid(String anid) {
		this.anid = anid;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
}
