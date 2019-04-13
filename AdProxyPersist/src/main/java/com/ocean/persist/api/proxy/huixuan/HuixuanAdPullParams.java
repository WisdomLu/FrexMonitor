package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdPullParams   extends AbstractInvokeParameter{

	private static final long serialVersionUID = -3682739952993251192L;
	private String id;
	private HuixuanAdDevice device;
	private HuixuanAdApp app;
	private HuixuanAdSite site;
	private HuixuanAdUser user;
	private List<HuixuanAdImp> imp;
	private int media_id;
	private int ts;
	private String token_md5;//Md5(media_id+ts+token)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HuixuanAdDevice getDevice() {
		return device;
	}
	public void setDevice(HuixuanAdDevice device) {
		this.device = device;
	}
	public HuixuanAdApp getApp() {
		return app;
	}
	public void setApp(HuixuanAdApp app) {
		this.app = app;
	}
	public HuixuanAdSite getSite() {
		return site;
	}
	public void setSite(HuixuanAdSite site) {
		this.site = site;
	}
	public HuixuanAdUser getUser() {
		return user;
	}
	public void setUser(HuixuanAdUser user) {
		this.user = user;
	}
	public List<HuixuanAdImp> getImp() {
		return imp;
	}
	public void setImp(List<HuixuanAdImp> imp) {
		this.imp = imp;
	}
	public int getMedia_id() {
		return media_id;
	}
	public void setMedia_id(int media_id) {
		this.media_id = media_id;
	}
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
	public String getToken_md5() {
		return token_md5;
	}
	public void setToken_md5(String token_md5) {
		this.token_md5 = token_md5;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
