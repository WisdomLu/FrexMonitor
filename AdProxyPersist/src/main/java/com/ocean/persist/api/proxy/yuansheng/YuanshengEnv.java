package com.ocean.persist.api.proxy.yuansheng;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengEnv extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5513382224436313897L;
	private List<String>  applist;//	array of string	N	已安装APP列表，可传送app的bundle。	['com.xxx', 'com.xxx' 'com.xxx']
	private int hasckp	;//integer	N	是否支持在click回调中添加用户点击广告位置，0：不支持，1：支持。	
	public List<String> getApplist() {
		return applist;
	}
	public void setApplist(List<String> applist) {
		this.applist = applist;
	}
	public int getHasckp() {
		return hasckp;
	}
	public void setHasckp(int hasckp) {
		this.hasckp = hasckp;
	}

}
