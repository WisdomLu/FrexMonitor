package com.ocean.persist.api.proxy.yitong;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
public class YitongAd extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -432837037440265546L;
	private String adid;//": "112694_12849", //广告ID 
	private String clickmonitor;//": "hours=",//广告服务器ADserver下发的上报参数，透传
	private String du;//": 3, 
	private String form;//": "info_bannertxt", //特型名称，即模板名称，图文组合模板 
	private String impressionid;//": "5288492448d8590651a94529c0b369bf00:598D0E481FA2F510CEBA9612BAB2034D1C9E914294F2D6DFCBA6D438D68E5B9885ABA4BE67FB78CA14C84D8EDBAB079AB828C29AA9C405215201101A5E15103016FC22E6728281050EAD3A3275DF52CC:1:0:priority_n:1447839934:1447776000:1479398400:920191755091968:0:5.3.0",
	//广告展示ID，解析后请不要进行url encode
	private String itemspaceid;//": "12849",// 广告位id 
	private String monitorkey;//": "20qdp0q9U0000000ttF100tjE"//广告物料唯一标识，同mkey 
	private String offline;//": 1479398400, //广告下线时间  "
	private String onform;//": 1,//在线状态，0在线，1离线，默认是1 
	private String online;//": 1447776000, //广告上线时间 
	private String position;//": "0", //广告出现在流内的相对位置
	private String size;//": "640:100", //广告尺寸
	private String tag;//": 1447833460, //当前发布时间 
	private String viewmonitor;//": "hours=",// 广告服务器 ADserver下发的上报参数，透传
	private int weight;//": 200 // 广告权重，优先级
	private YitongResource resource;
	private YitongResource resource1;
	private YitongSpecial special;
	
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getClickmonitor() {
		return clickmonitor;
	}
	public void setClickmonitor(String clickmonitor) {
		this.clickmonitor = clickmonitor;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getImpressionid() {
		return impressionid;
	}
	public void setImpressionid(String impressionid) {
		this.impressionid = impressionid;
	}
	public String getItemspaceid() {
		return itemspaceid;
	}
	public void setItemspaceid(String itemspaceid) {
		this.itemspaceid = itemspaceid;
	}
	public String getMonitorkey() {
		return monitorkey;
	}
	public void setMonitorkey(String monitorkey) {
		this.monitorkey = monitorkey;
	}
	public String getOffline() {
		return offline;
	}
	public void setOffline(String offline) {
		this.offline = offline;
	}
	public String getOnform() {
		return onform;
	}
	public void setOnform(String onform) {
		this.onform = onform;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getViewmonitor() {
		return viewmonitor;
	}
	public void setViewmonitor(String viewmonitor) {
		this.viewmonitor = viewmonitor;
	}
	public int getWeight() {
		return weight;
	}
	public YitongResource getResource() {
		return resource;
	}
	public void setResource(YitongResource resource) {
		this.resource = resource;
	}
	public YitongResource getResource1() {
		return resource1;
	}
	public void setResource1(YitongResource resource1) {
		this.resource1 = resource1;
	}
	public YitongSpecial getSpecial() {
		return special;
	}
	public void setSpecial(YitongSpecial special) {
		this.special = special;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
