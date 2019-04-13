package com.ocean.persist.api.proxy.maiguang;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月30日 
      @version 1.0 
 */
public class MaiguangAdpullParams    extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8636901162969760673L;
	private String action;
	private String appid;
	private String ver;
	private String tp;
	
	private String is;
	private int dt;
	private String dtv;
	private String ic;
	private String w;
	private String h;
	private String brand;
	private String mod;
	private String os;
	private String ov;
	private String sdkVersion;
	private String mcc;
	private String mnc;
	private String lac;
	private String cid;
	private int nt;
	private String mac;
	private int pt;
	private String pl;
	private String adrid;
	private int adnum;
	private String ua;
	private String dip;
	private String ip;
	private String aaid;
	private String lon;
	private String lat;
	private String 	density;
	private String 	sign;
	public String getIs() {
		return is;
	}
	public void setIs(String is) {
		this.is = is;
	}
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public String getDtv() {
		return dtv;
	}
	public void setDtv(String dtv) {
		this.dtv = dtv;
	}
	public String getIc() {
		return ic;
	}
	public void setIc(String ic) {
		this.ic = ic;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOv() {
		return ov;
	}
	public void setOv(String ov) {
		this.ov = ov;
	}
	public String getSdkVersion() {
		return sdkVersion;
	}
	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getNt() {
		return nt;
	}
	public void setNt(int nt) {
		this.nt = nt;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getPt() {
		return pt;
	}
	public void setPt(int pt) {
		this.pt = pt;
	}
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getAdrid() {
		return adrid;
	}
	public void setAdrid(String adrid) {
		this.adrid = adrid;
	}
	public int getAdnum() {
		return adnum;
	}
	public void setAdnum(int adnum) {
		this.adnum = adnum;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getDip() {
		return dip;
	}
	public void setDip(String dip) {
		this.dip = dip;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAaid() {
		return aaid;
	}
	public void setAaid(String aaid) {
		this.aaid = aaid;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
