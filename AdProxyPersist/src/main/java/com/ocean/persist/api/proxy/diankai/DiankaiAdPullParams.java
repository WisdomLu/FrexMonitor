package com.ocean.persist.api.proxy.diankai;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月24日 
      @version 1.0 
 */
public class DiankaiAdPullParams     extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850678270286923143L;
    private String pid;
    private String ad_space;
    private int ad_type;
    private String  ad_category;
    private String package_name;
    
    //device
    private String device_name;
    private String device_brand;
    private String  os;
    private String  os_version;
    private int screen_width;
    private int screen_height;
    private String sn;
    private String  ua;
    private String   sim;
    private String  android_id;
    private String  mac;
    private int  is_tablet;
    
    //net
    private String cell_ids;
    private String  carrier;
    private String  lat;
    private String lng;
    private int is_wifi;
    private String ip;
    private int client;
    private String access_points;
    
    private String api_ver;
    private String  token;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAd_space() {
		return ad_space;
	}
	public void setAd_space(String ad_space) {
		this.ad_space = ad_space;
	}
	public int getAd_type() {
		return ad_type;
	}
	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}
	public String getAd_category() {
		return ad_category;
	}
	public void setAd_category(String ad_category) {
		this.ad_category = ad_category;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_brand() {
		return device_brand;
	}
	public void setDevice_brand(String device_brand) {
		this.device_brand = device_brand;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public int getScreen_width() {
		return screen_width;
	}
	public void setScreen_width(int screen_width) {
		this.screen_width = screen_width;
	}
	public int getScreen_height() {
		return screen_height;
	}
	public void setScreen_height(int screen_height) {
		this.screen_height = screen_height;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getIs_tablet() {
		return is_tablet;
	}
	public void setIs_tablet(int is_tablet) {
		this.is_tablet = is_tablet;
	}
	public String getCell_ids() {
		return cell_ids;
	}
	public void setCell_ids(String cell_ids) {
		this.cell_ids = cell_ids;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public int getIs_wifi() {
		return is_wifi;
	}
	public void setIs_wifi(int is_wifi) {
		this.is_wifi = is_wifi;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public String getAccess_points() {
		return access_points;
	}
	public void setAccess_points(String access_points) {
		this.access_points = access_points;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	public String getApi_ver() {
		return api_ver;
	}
	public void setApi_ver(String api_ver) {
		this.api_ver = api_ver;
	}
    
}
