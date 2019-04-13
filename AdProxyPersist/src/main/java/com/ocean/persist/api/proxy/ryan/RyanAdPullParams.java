package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/**ADX 向多家 DSP 同时发送竞价请求， DSP 返回广告竞价价格，出价最高的 DSP 获取广告展示机会， 并以次高价作为成交价格。
 * ADX 会通过获胜通知或者展示监控通知 DSP 最终成交价。 
 * * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanAdPullParams   extends AbstractInvokeParameter{

	private static final long serialVersionUID = 2832366901765392914L;
    private String id;//ADX生成的本次请求唯一标识
    private List<RyanImpression> imp;//广告位描述数组，目前只支持一个
    private RyanApp app;//应用信息
    private RyanDevice device;//设备信息
    private RyanUser  user;//用户信息
    private Object ext;//拓展信息
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<RyanImpression> getImp() {
		return imp;
	}
	public void setImp(List<RyanImpression> imp) {
		this.imp = imp;
	}
	public RyanApp getApp() {
		return app;
	}
	public void setApp(RyanApp app) {
		this.app = app;
	}
	public RyanDevice getDevice() {
		return device;
	}
	public void setDevice(RyanDevice device) {
		this.device = device;
	}
	public RyanUser getUser() {
		return user;
	}
	public void setUser(RyanUser user) {
		this.user = user;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
    
}
