package com.ocean.persist.api.proxy.gmobi;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
public class GmobiAdPullParams  extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8436918410128283915L;
	private  String app;
	private  String key;
	private  String placement;
	private  String type;
	private  String adid;
	private  String ua;
	private  String os;
	private  String os_v;
	private  String net;
	private  String ip;
	private  int count;
	private  String caps;
	private  String imei;
	private  String sn;
	private  int iw;
	private  int ih;
	private  String strict;
	private  int sw;
	private  int sh;
	//loc
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPlacement() {
		return placement;
	}
	public void setPlacement(String placement) {
		this.placement = placement;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOs_v() {
		return os_v;
	}
	public void setOs_v(String os_v) {
		this.os_v = os_v;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCaps() {
		return caps;
	}
	public void setCaps(String caps) {
		this.caps = caps;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getIw() {
		return iw;
	}
	public void setIw(int iw) {
		this.iw = iw;
	}
	public int getIh() {
		return ih;
	}
	public void setIh(int ih) {
		this.ih = ih;
	}
	public String getStrict() {
		return strict;
	}
	public void setStrict(String strict) {
		this.strict = strict;
	}
	public int getSw() {
		return sw;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
