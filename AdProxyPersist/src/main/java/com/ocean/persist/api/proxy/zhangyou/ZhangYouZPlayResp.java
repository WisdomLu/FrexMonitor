package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月9日 
      @version 1.0 
 */
public class ZhangYouZPlayResp   {
   /**
	 * 
	 */
	private static final long serialVersionUID = -5242046324198883024L;
   private String app_id;//zplay ssp api返回的app id
   private String position_sid;//zplay ssp api返回的position sid
   private String app_secret;//zplay ssp api返回的app secret
   private String trans_data;//zplay ssp api透传数据
   private String deep_link_backup_url;//deeplink 链接访问失败时的备份链接
public String getApp_id() {
	return app_id;
}
public void setApp_id(String app_id) {
	this.app_id = app_id;
}
public String getPosition_sid() {
	return position_sid;
}
public void setPosition_sid(String position_sid) {
	this.position_sid = position_sid;
}
public String getApp_secret() {
	return app_secret;
}
public void setApp_secret(String app_secret) {
	this.app_secret = app_secret;
}
public String getTrans_data() {
	return trans_data;
}
public void setTrans_data(String trans_data) {
	this.trans_data = trans_data;
}
public String getDeep_link_backup_url() {
	return deep_link_backup_url;
}
public void setDeep_link_backup_url(String deep_link_backup_url) {
	this.deep_link_backup_url = deep_link_backup_url;
}
}
