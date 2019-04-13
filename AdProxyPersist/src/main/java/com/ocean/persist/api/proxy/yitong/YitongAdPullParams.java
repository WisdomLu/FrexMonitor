package com.ocean.persist.api.proxy.yitong;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
public class YitongAdPullParams    extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8755558426328104604L;
	private String supplyid;/*
	必填
	平台标识 平台标识 id
	固定传 6，表示移动 ，表示移动 端请求*/
	private String itemspaceid;/*
	必填
	广告位 广告位 id，apid（向移动联盟客 （向移动联盟客 （向移动联盟客 服获取） 服获取）
	5位纯数字 位纯数字*/
	private String developerid;/*
	必填
	开发者 开发者 开发者 id（向移动联盟客服获 （向移动联盟客服获 （向移动联盟客服获 （向移动联盟客服获 取）
	6位纯数字 位纯数字*/
	private String appid;/*
	必填
	第三方 第三方 Appid（向移动联盟客服 （向移动联盟客服 （向移动联盟客服 （向移动联盟客服 获取）
	6位纯数字 位纯数字*/
	private String adps;/*
	必填
	广告位接受的图片大小 广告位接受的图片大小 广告位接受的图片大小 广告位接受的图片大小 广告位接受的图片大小
	图片大小：宽x10000+高*/
	private String apt;/*
	必填
	广告位所属站点 广告位所属站点 广告位所属站点
	0=PC； 1=app；
	
	*/
	private String sv;/*
	必填
	操作系统＋ 操作系统＋ App版本号 版本号
	Android5.3.0； iOS5.3.0*/
	private String pn;/*
	必填
	手机型号 手机型号
	如 pn=GT-I9502， iPhone8,1*/
			private String nets;/*
	必填
	用户网络环境 用户网络环境 用户网络环境 (针对移动端用户 针对移动端用户 针对移动端用户 针对移动端用户 )
	2g/3g/4g/wifi*/
	private String appv;/*
	必填
	App版本号 版本号
	5.3.0*/
	private String cid;/*
	必填
	移动端用户标识 移动端用户标识 移动端用户标识
	ios取idfa
	android取imei的md5值*/
	private String adsid;/*
	必填
	移动端用户标识 移动端用户标识 移动端用户标识
	ios取idfa
	android取imei*/
	private String idfa;/*
	ios必填
	苹果设备唯一标识 苹果设备唯一标识 苹果设备唯一标识 苹果设备唯一标识
	iOS必填*/
	private String idfv;/*
	ios必填
	苹果设备唯一标识 苹果设备唯一标识 苹果设备唯一标识 苹果设备唯一标识
	无法获取传空 法获取传空 法获取传空*/
	private String openudid;/*
	ios必填
	苹果设备唯一识别号 苹果设备唯一识别号 苹果设备唯一识别号 苹果设备唯一识别号
	无法获取传空 法获取传空 法获取传空*/
	private String imei;/*
	android必填
	安卓设备唯一标识 安卓设备唯一标识 安卓设备唯一标识 安卓设备唯一标识
	Android必填*/
	
	private String imsi;/*
	android必填
	安卓设备唯一标识 安卓设备唯一标识 安卓设备唯一标识 安卓设备唯一标识
	无法获取传空 法获取传空 法获取传空*/
	private String androidID;/*
	android必填
	安卓设备唯一标识 安卓设备唯一标识 安卓设备唯一标识 安卓设备唯一标识
	无法获取传空 法获取传空 法获取传空*/
	private String debugip;/*
	ssp接入必填
	用户ip，示例 debugip=123.126.70.242*/
	private String mac;
	
	private String scs;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getItemspaceid() {
		return itemspaceid;
	}

	public void setItemspaceid(String itemspaceid) {
		this.itemspaceid = itemspaceid;
	}

	public String getDeveloperid() {
		return developerid;
	}

	public void setDeveloperid(String developerid) {
		this.developerid = developerid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAdps() {
		return adps;
	}

	public void setAdps(String adps) {
		this.adps = adps;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getSv() {
		return sv;
	}

	public void setSv(String sv) {
		this.sv = sv;
	}

	public String getNets() {
		return nets;
	}

	public void setNets(String nets) {
		this.nets = nets;
	}

	public String getAppv() {
		return appv;
	}

	public void setAppv(String appv) {
		this.appv = appv;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAdsid() {
		return adsid;
	}

	public void setAdsid(String adsid) {
		this.adsid = adsid;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getIdfv() {
		return idfv;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}

	public String getOpenudid() {
		return openudid;
	}

	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getAndroidID() {
		return androidID;
	}

	public void setAndroidID(String androidID) {
		this.androidID = androidID;
	}

	public String getDebugip() {
		return debugip;
	}

	public void setDebugip(String debugip) {
		this.debugip = debugip;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getScs() {
		return scs;
	}

	public void setScs(String scs) {
		this.scs = scs;
	}

}
