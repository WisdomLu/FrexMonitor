package com.ocean.persist.app.dis.daluoma;

import com.ocean.persist.app.dis.AdDisParams;

public class BaseDaluomaRequest  implements AdDisParams{
	    //url参数
		private int organization ;
		private String ip;
	
		
		//设备参数
		private String imei;
		private String androidid;
		private String serialno;
		private String md;
		private String br;
		private int os;
		private String osv;
		private String carrier;
		private int sw;
		private int sh;
		private double dip;

		
		//动态参数
		private int so;
		private int net;
		private String searchword;
		private String ua;
		public boolean validate() {
			// TODO Auto-generated method stub
			return false;
		}
		public int getOrganization() {
			return organization;
		}
		public void setOrganization(int organization) {
			this.organization = organization;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getImei() {
			return imei;
		}
		public void setImei(String imei) {
			this.imei = imei;
		}
		public String getAndroidid() {
			return androidid;
		}
		public void setAndroidid(String androidid) {
			this.androidid = androidid;
		}
		public String getSerialno() {
			return serialno;
		}
		public void setSerialno(String serialno) {
			this.serialno = serialno;
		}
		public String getMd() {
			return md;
		}
		public void setMd(String md) {
			this.md = md;
		}
		public String getBr() {
			return br;
		}
		public void setBr(String br) {
			this.br = br;
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
		public String getCarrier() {
			return carrier;
		}
		public void setCarrier(String carrier) {
			this.carrier = carrier;
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
		public double getDip() {
			return dip;
		}
		public void setDip(double dip) {
			this.dip = dip;
		}
		public int getSo() {
			return so;
		}
		public void setSo(int so) {
			this.so = so;
		}
		public int getNet() {
			return net;
		}
		public void setNet(int net) {
			this.net = net;
		}
		public String getSearchword() {
			return searchword;
		}
		public void setSearchword(String searchword) {
			this.searchword = searchword;
		}
		public String getUa() {
			return ua;
		}
		public void setUa(String ua) {
			this.ua = ua;
		}

		
}
