package com.ocean.persist.api.proxy.shaibo;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboExt    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 727060214149198494L;
	private List<String> ds ;//	list of string 	事件回调数组,app下载开始时回调,只针对安卓下载广告
	private List<String> df 	;//	list of string 	事件回调数组,app下载完成时回调,只针对安卓下载广告
	private List<String> inst 	;//	list of string 	事件回调数组,app安装完成时回调,只针对安卓下载广告
	private List<String> open 	;//	list of string 	事件回调数组,app应用打开时回调,只针对安卓下载广告
	public List<String> getDs() {
		return ds;
	}
	public void setDs(List<String> ds) {
		this.ds = ds;
	}
	public List<String> getDf() {
		return df;
	}
	public void setDf(List<String> df) {
		this.df = df;
	}
	public List<String> getInst() {
		return inst;
	}
	public void setInst(List<String> inst) {
		this.inst = inst;
	}
	public List<String> getOpen() {
		return open;
	}
	public void setOpen(List<String> open) {
		this.open = open;
	}
}
