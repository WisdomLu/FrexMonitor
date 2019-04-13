package com.ocean.persist.api.proxy.yidianzx;

import java.util.ArrayList;
import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class YidianzxReq   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1132291157785331780L;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String id ;//string 是检索ID，唯一标示请求
	private List<YidianzxImp> imp ;//object array 是曝光对象，每个请求最少包含一个Imp，见Imp
	private YidianzxDevice device;// object 是设备信息对象，见Device
	//site object 推荐网站信息对象，见Site
	private YidianzxApp app ;//object 推荐移动App 对象，见App
	//user object 推荐用户信息对象，见User
	private int at ;//int 是出价类型
/*	1: 最高价
	2: 第二高价*/
	//tmax int 否超时时长，单位毫秒，默认300ms
	private boolean test;// bool 否测试字段，默认false：
/*	false: 生产模式
	true: 测试模式*/
	
	private String from;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public YidianzxDevice getDevice() {
		return device;
	}
	public void setDevice(YidianzxDevice device) {
		this.device = device;
	}
	public YidianzxApp getApp() {
		return app;
	}
	public void setApp(YidianzxApp app) {
		this.app = app;
	}
	public int getAt() {
		return at;
	}
	public void setAt(int at) {
		this.at = at;
	}
	public boolean isTest() {
		return test;
	}
	public void setTest(boolean test) {
		this.test = test;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<YidianzxImp> getImp() {
		return imp;
	}
	public void setImp(List<YidianzxImp> imp) {
		this.imp = imp;
	}
}
