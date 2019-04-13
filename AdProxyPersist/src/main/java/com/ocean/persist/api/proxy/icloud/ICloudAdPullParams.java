package com.ocean.persist.api.proxy.icloud;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月12日 
      @version 1.0 
 */
public class ICloudAdPullParams     extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2397004004046593472L;
	private String channelid;
	private String bid;
	private String adspaceid;
	private String adtype;
	private String pkgname;
	private String appname;;
	private String conn;
	private String carrier;
	private String apitype;
	private String os;
	private String osv;
	private String imei;
	private String wma;
	private String aid;
	private String aaid;
	private String idfa;
	private String oid;
	private String uid;
	private String device;
	private String ua;
	private String ip;
	private String width;
	private String height;
	private String pid;
	private String pcat;
	private String media;
	private String debug;
	private String density;
	private String lon;
	private String lat;
	private String cell;
	private String mcell;
	private String imsi;
	private String ggid;
	private String pw;
	private String ph;
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getAdspaceid() {
		return adspaceid;
	}
	public void setAdspaceid(String adspaceid) {
		this.adspaceid = adspaceid;
	}
	public String getAdtype() {
		return adtype;
	}
	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getConn() {
		return conn;
	}
	public void setConn(String conn) {
		this.conn = conn;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getApitype() {
		return apitype;
	}
	public void setApitype(String apitype) {
		this.apitype = apitype;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getWma() {
		return wma;
	}
	public void setWma(String wma) {
		this.wma = wma;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAaid() {
		return aaid;
	}
	public void setAaid(String aaid) {
		this.aaid = aaid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
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
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPcat() {
		return pcat;
	}
	public void setPcat(String pcat) {
		this.pcat = pcat;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getDebug() {
		return debug;
	}
	public void setDebug(String debug) {
		this.debug = debug;
	}
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
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
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getMcell() {
		return mcell;
	}
	public void setMcell(String mcell) {
		this.mcell = mcell;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getGgid() {
		return ggid;
	}
	public void setGgid(String ggid) {
		this.ggid = ggid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
