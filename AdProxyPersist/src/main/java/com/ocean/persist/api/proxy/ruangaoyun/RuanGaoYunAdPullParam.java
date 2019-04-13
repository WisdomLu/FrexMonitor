package com.ocean.persist.api.proxy.ruangaoyun;


import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunAdPullParam    extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3637123878610488567L;
    private String id;//动态生成一个唯一id
    private String version;//版本号
    private RuanGaoYunSite site;
    private RuanGaoYunImp imp;
    private RuanGaoYunApp app;
    private RuanGaoYunDevice device;
    private RuanGaoYunUser user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public RuanGaoYunSite getSite() {
		return site;
	}
	public void setSite(RuanGaoYunSite site) {
		this.site = site;
	}
	public RuanGaoYunImp getImp() {
		return imp;
	}
	public void setImp(RuanGaoYunImp imp) {
		this.imp = imp;
	}
	public RuanGaoYunApp getApp() {
		return app;
	}
	public void setApp(RuanGaoYunApp app) {
		this.app = app;
	}
	public RuanGaoYunDevice getDevice() {
		return device;
	}
	public void setDevice(RuanGaoYunDevice device) {
		this.device = device;
	}
	public RuanGaoYunUser getUser() {
		return user;
	}
	public void setUser(RuanGaoYunUser user) {
		this.user = user;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
