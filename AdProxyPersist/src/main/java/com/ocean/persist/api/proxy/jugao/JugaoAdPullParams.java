package com.ocean.persist.api.proxy.jugao;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
public class JugaoAdPullParams   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927076896439399164L;
    private String adid;//广告位id
    private int adtype;//1:横幅广告 2:开屏广告 3:原生信息流广告 4:插屏广告  5:原生视频广告 6:视频广告
    private int width;//广告宽度
    private int height;//广告高度
    private String pkgname;//应用程序主包名
    private String appname;//应用程序名称
    private String ua;
    private int os;//0:Android 1:Ios 2:Windows Phone 3:Others
    private String osv;//设备版本
    private int carrier;//设备运营商
    private int conn;//设备网络类型 1:2g 2:3g 3:4g 4:wifi  5:其他
    private String ip;//设备的公网IP （非局域网IP）
    private float density;//设备屏幕的密度
    private String brand;//设备制造厂商
    private String model;//设备型号
    private String uuid;//设备唯一标识 （安卓和ios 都  需要）
    private String anid;//The Android Id （仅限安卓设 备）
    private String mac;
    private Integer secure;//是否支持https 该值传0 时聚告返回的链接以http 开头，传1时会返回https 开头的链接，如果不传默认只支持http
    private String Lon;
    private String Lat;
    private int pw;//设备屏幕宽度
    private int ph;//设备屏幕高度
    private int devicetype;//设备类型 1：移动手机 2：平板电脑
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
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
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public int getCarrier() {
		return carrier;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public int getConn() {
		return conn;
	}
	public void setConn(int conn) {
		this.conn = conn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public float getDensity() {
		return density;
	}
	public void setDensity(float density) {
		this.density = density;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAnid() {
		return anid;
	}
	public void setAnid(String anid) {
		this.anid = anid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Integer getSecure() {
		return secure;
	}
	public void setSecure(Integer secure) {
		this.secure = secure;
	}
	public String getLon() {
		return Lon;
	}
	public void setLon(String lon) {
		Lon = lon;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	public int getPh() {
		return ph;
	}
	public void setPh(int ph) {
		this.ph = ph;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
    
    
}
