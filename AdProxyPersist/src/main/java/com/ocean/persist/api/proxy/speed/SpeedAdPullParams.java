package com.ocean.persist.api.proxy.speed;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class SpeedAdPullParams    extends AbstractInvokeParameter{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1085182148095838915L;
	private String source;//优先请求广告源:GDT-广点通,AFP-阿里妈妈,OTHER-其他
    private String channel;//渠道号（由商务分配）
    private String pid;//广告位ID（由商务分配）
    private String model;//设备型号
    private String vendor;//设备厂商
    private int width;//屏幕宽度
    private int height;//屏幕高度
    private String osv;//操作系统版本
    private int ori;//屏幕角度，0-屏幕正对， 90-屏幕顺时针旋转90，180-屏幕顺时针旋转180， 270-屏幕顺时针旋转270
    private String appver;//应用版本号
    private int ostype;//设备类型，1-安卓，2-IOS
    private String size;//屏幕大小
    private String imei;//安卓imei号
    private String idfa;///苹果idfa号
    private String mac;
    private String ip;
    private int netype;//网络类型 0-unknown 1-wifi 2-2G 3-3G 4-4G
    private int carrier;//网络运营商 1-移动 2-联通 3-电信 0-unknown
    private String lat;
    private String lng;
    private String ltime;
    private String apiver;//协议版本
    private String androidid;
    private String aaid;
    private String imsi;
    private String ua;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public int getOri() {
		return ori;
	}
	public void setOri(int ori) {
		this.ori = ori;
	}
	public String getAppver() {
		return appver;
	}
	public void setAppver(String appver) {
		this.appver = appver;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLtime() {
		return ltime;
	}
	public void setLtime(String ltime) {
		this.ltime = ltime;
	}
	public int getOstype() {
		return ostype;
	}
	public void setOstype(int ostype) {
		this.ostype = ostype;
	}
	public int getNetype() {
		return netype;
	}
	public void setNetype(int netype) {
		this.netype = netype;
	}
	public int getCarrier() {
		return carrier;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public String getApiver() {
		return apiver;
	}
	public void setApiver(String apiver) {
		this.apiver = apiver;
	}
	public String getAndroidid() {
		return androidid;
	}
	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}
	public String getAaid() {
		return aaid;
	}
	public void setAaid(String aaid) {
		this.aaid = aaid;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
