package com.ocean.persist.app.dis.zk;

import com.ocean.core.common.base.AbstractBaseEntity;

public class ZKUserInfo  extends AbstractBaseEntity{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -5609106854551223632L;
	private String uid; // required
	private String clientIp; // required
	private String city; // optional
	private String lon; // optional
	private String lat; // optional
	private String net; // optional
	private String ua; // optional
	  
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getClientIp() {
			return clientIp;
		}
		public void setClientIp(String clientIp) {
			this.clientIp = clientIp;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
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
		public String getNet() {
			return net;
		}
		public void setNet(String net) {
			this.net = net;
		}
		public String getUa() {
			return ua;
		}
		public void setUa(String ua) {
			this.ua = ua;
		}

  
}

