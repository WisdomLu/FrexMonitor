package com.ocean.persist.app.dis.onemobi;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.persist.app.dis.AdDisParams;

public class OnemobReqParams    implements AdDisParams{
	private static final Logger LOGGER = MyLogManager.getLogger();

	private String cp; // 必填	String	由OneMob分配，cp=AD000
	private String pan;// 必填	Api版本，目前填：api74tang
	private String imei; //必填，device IMEI
	private String imsi;// 选填	String	用户sim卡imsi号
	private String aid;// 必填	String	Android手机设备系统ID
	private String mac;// 选填	string	设备wifi网卡MAC地址
	private String brnd;// 必填	String	设备厂商名称，中文需要UTF-8编码
	private String mdl;//  必填	String	设备型号，中文需要UTF-8编码
	private String client_ip;// 必填	string	用户设备的公网ipv4地址，service to service接入必填
	private String client_ua;// 必填	String	用户客户端的user-Agent,
	// 必填	String 网络连接类型 无法探测网络类型：0、蜂窝数据2G网络：2g、蜂窝数据3G网络：
	// 3g、蜂窝数据4G网络：4g、蜂窝数据5G网络：5g、Wi-Fi网络：wifi
	private String nt;
	private String an;//发起广告请求的应用包名
	private String dm;//   必填	String	设备屏幕宽高，如：1080x1920(x为小写字母)
	
	private String rel;//操作系统版本，如：2.3、5.0等
	private String type;//	必填	String	固定值为list1
	private String nextcur	;//必填	String	分页上下文，第一页填空，第二页开始带上接口返回值

	
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getBrnd() {
		return brnd;
	}
	public void setBrnd(String brnd) {
		this.brnd = brnd;
	}
	public String getMdl() {
		return mdl;
	}
	public void setMdl(String mdl) {
		this.mdl = mdl;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getClient_ua() {
		return client_ua;
	}
	public void setClient_ua(String client_ua) {
		this.client_ua = client_ua;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}

	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(this.getImsi())){
			LOGGER.error("onemobi ad request,param error:imsi is empty!");
			return false;
		}
		if(StringUtils.isEmpty(this.getAid())){
			LOGGER.error("onemobi ad request,param error:aid is empty!");
			return false;
		}
		return true;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNextcur() {
		return nextcur;
	}
	public void setNextcur(String nextcur) {
		this.nextcur = nextcur;
	}



}
