package com.ocean.persist.api.proxy.huzhong;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
public class HuzhongAdPullParams     extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8075958423907965161L;
	private String ip;
	private String ua;
	private String uid;
	private String si;
	private String rr;
	private String url;
	private int min;
	private int max;
	private String reqid;
	private int bf;
	private int https;//	可选	是否是https环境，0或1，默认为0	1
	private String tagid;//	可选	媒体的广告位ID	xxx
	
	private String app_version;
	private String package_name;
	private String mimes;
	private String jsonp;
	private String v;
	private String device;   //json串
	private String  gps;    //json串
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getRr() {
		return rr;
	}
	public void setRr(String rr) {
		this.rr = rr;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public int getBf() {
		return bf;
	}
	public void setBf(int bf) {
		this.bf = bf;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public String getMimes() {
		return mimes;
	}
	public void setMimes(String mimes) {
		this.mimes = mimes;
	}
	public String getJsonp() {
		return jsonp;
	}
	public void setJsonp(String jsonp) {
		this.jsonp = jsonp;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public int getHttps() {
		return https;
	}
	public void setHttps(int https) {
		this.https = https;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
