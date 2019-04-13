package com.ocean.persist.api.proxy.zhongcheng;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class ZhongchengAdPullParams     extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String adunitid;// 必填 	广告位展示 id，唯一标识一个广告位 , 一个广告位只有一个 adunitid 	D8AF622DB3BF1FDB9A66433E58C 66BBC 
	private Integer net;// 必填 	联网类型（0:未知网络，1:以太网，2:wifi，3:未知移动网络，4:2G网络，5:3G 网络，6:4G 网络） 	2 
	private String pkg;// 必填 	App 媒体的包名称 	com.adusing.adsdemo 
	private Integer dvw;// 必填 	设备屏幕的宽度，以像素为单位。（单位为密度无关像素，即 DIP） 	1152 
	private Integer dvh;// 必填 	设备屏幕的高度，以像素为单位。（单位为密度无关像素，即 DIP） 	1920 
	private String appid;// 必填 	媒体展示 id，唯一标识一个媒体，一个媒体只有一个 appid 	B3BF1FC66BBCD8AF622DDB9A664 33E58 
	private String imei;// 必填 	手机 IMEI 码 	865429018126336 
	private String adid;// 必填 	Android ID 	403a66c10b9dec23 
	private String model;// 必填 	设备型号 	MX4 
	private String osv;// 必填 	操作系统版本 	5.1 
	private String ts;// 必填 	发送本地 UNIX 时间戳（十进制秒数） 	1457332258 
	private String ssid;// 必填 	当前连接 Wifi SSID，如有必填（否则可能影响收益） 	Tplink_8868 
	private String mac;// 必填 	mac 地址 	02:00:6B:16:C6:2A 
	private String api_ver;// 必填 	协议版本 	2.0 
	private String operator;// 必填 	网络运营商，  MCC（移动国家码）+MNC（移动网络码）  如 46001 为联通 	 46001 
	private Integer adw;// 必填 	广告位宽度。（单位为密度无关像素，即 DIP） 	640 
	private Integer adh;// 必填 广告位高度。（单位为密度无关像素，即 DIP） 	100 

	private String ua;// 必填 	User-Agent 信息 	Mozilla/5.0 (Linux; Android 4.4.4; MI 3C Build/KTU84P) AppleWebKit/537.36 (KHTML,  like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 
	private String ip;// 必填 	用户 IP 或移动 ISP 的网关 IP 	171.84.52.67 
	private String os;// 必填 	用户操作系统名称（如:Android, iOS） 	Android 
	private Integer batch_count;// 必填 	请求原生广告数量，原生广告必填  // 取值可为 1,2,3 	1 
	private String idfa;// 必填 	iOS IDFA 	C7873FB3-723F-4FA6-A1AF90C3E3ADF7F4 
	private Integer brk;// 必填 	iOS设备是否越狱。1：是, 0：否/未知（默认） 	0 
	private String openudid;// 建议 	OpenUDID  IOS 	04079b0cc9ad891b2135cc8d2f31cb179c6498ba 
	private String vendor;// 建议 	设备厂家   //提供信息越详细则越能增加收益 	Meizu 
	private String density;// 建议 	屏幕密度    //提供信息越详细则越能增加收益 	480 
	private String version;// 建议 	填写 ssp 自有下发 sdk 版本号，即 ssp 旗下媒体所集成 sdk 的版本号    //提供信息越详细则越能增加收益 	2.0 
	private String appname;// 建议 	App Name    //提供信息越详细则越能增加收益 	蹦心 app 
	private Integer devicetype;// 建议 	设备类型 （-1:未知 ,0:手机,1:pad, 2:pc, 3:tv,4:wap）    //提供信息越详细则越能增加收益 	0 
	private String geo;// 建议 	定位信息   经度, 纬度    //提供信息越详细则越能增加收益 	116.67,39.92 
	private String lan;// 建议 	语言-国家    //提供信息越详细则越能增加收益 	zh-CN 
	private String orientation;// 建议 	0 为竖屏，1 为横屏  //提供信息越详细则越能增加收益 	0 
	private String isboot;// 建议 	是否开机全屏    //提供信息越详细则越能增加收益 	0 
	private String mkt_tag;// 可选 	app 在应用商店的标签（以 UTF8urlencode 编码）用,分隔 	蹦心,戴在手上的家 
	private String mkt_cat;// 可选 	app 在应用商店的分类编号 	8 
	private Integer mkt;// 可选 	应用商店类型 1:iOS AppStore 2:Google Play 3:91 Market 	1 
	private String mkt_sn;// 可选 	app 在应用商店的编号 	1039738239 
	private String aaid;// 可选 	AAID(Advertising Id)  google play services 提供 	4c30c866-30d6-4418-a6dc-0f702737bf58 

	public String getAdunitid() {
		return adunitid;
	}

	public void setAdunitid(String adunitid) {
		this.adunitid = adunitid;
	}

	public Integer getNet() {
		return net;
	}

	public void setNet(Integer net) {
		this.net = net;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public Integer getDvw() {
		return dvw;
	}

	public void setDvw(Integer dvw) {
		this.dvw = dvw;
	}

	public Integer getDvh() {
		return dvh;
	}

	public void setDvh(Integer dvh) {
		this.dvh = dvh;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getApi_ver() {
		return api_ver;
	}

	public void setApi_ver(String api_ver) {
		this.api_ver = api_ver;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getAdw() {
		return adw;
	}

	public void setAdw(Integer adw) {
		this.adw = adw;
	}

	public Integer getAdh() {
		return adh;
	}

	public void setAdh(Integer adh) {
		this.adh = adh;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getBatch_count() {
		return batch_count;
	}

	public void setBatch_count(Integer batch_count) {
		this.batch_count = batch_count;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public Integer getBrk() {
		return brk;
	}

	public void setBrk(Integer brk) {
		this.brk = brk;
	}

	public String getOpenudid() {
		return openudid;
	}

	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getLan() {
		return lan;
	}

	public void setLan(String lan) {
		this.lan = lan;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getIsboot() {
		return isboot;
	}

	public void setIsboot(String isboot) {
		this.isboot = isboot;
	}

	public String getMkt_tag() {
		return mkt_tag;
	}

	public void setMkt_tag(String mkt_tag) {
		this.mkt_tag = mkt_tag;
	}

	public String getMkt_cat() {
		return mkt_cat;
	}

	public void setMkt_cat(String mkt_cat) {
		this.mkt_cat = mkt_cat;
	}

	public Integer getMkt() {
		return mkt;
	}

	public void setMkt(Integer mkt) {
		this.mkt = mkt;
	}

	public String getMkt_sn() {
		return mkt_sn;
	}

	public void setMkt_sn(String mkt_sn) {
		this.mkt_sn = mkt_sn;
	}

	public String getAaid() {
		return aaid;
	}

	public void setAaid(String aaid) {
		this.aaid = aaid;
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
