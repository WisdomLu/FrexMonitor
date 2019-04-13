package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class YijifenAdPullParams    extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1639586490937230066L;
	private Integer channel;// 选填	开发者 ID
	private String token;// 必填	授权对应的 token 值，用于进行权限验证，请联系工作人员获取。
	private YijifenImp imp;// 必填	参考展现信息对象定义
	private YijifenDevice device;// 必填	参考设备信息对象定义
	private YijifenApp app;// 必填	参考应用信息对象定义
	private YijifenUser user;// 选填	参考用户信息对象定义
	private Integer isTest;// 必填	是否是测试请求，0：正式 1:测试
	private String userInfo;// 选填	媒体用户附加信息，返回值将原样返回给媒体。
	private String ext;// 选填	扩展字段，用来扩展信息

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public YijifenImp getImp() {
		return imp;
	}

	public void setImp(YijifenImp imp) {
		this.imp = imp;
	}

	public YijifenDevice getDevice() {
		return device;
	}

	public void setDevice(YijifenDevice device) {
		this.device = device;
	}

	public YijifenApp getApp() {
		return app;
	}

	public void setApp(YijifenApp app) {
		this.app = app;
	}

	public YijifenUser getUser() {
		return user;
	}

	public void setUser(YijifenUser user) {
		this.user = user;
	}

	public Integer getIsTest() {
		return isTest;
	}

	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
