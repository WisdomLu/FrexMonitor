package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneAdPullParams    extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6976132435565301235L;
	private String id;
	private List<RedStoneImp> imp;
	private RedStoneApp app;
	private RedStoneDevice device;
	private RedStoneUser user;
	private int at;
	private int tmax;
	private List<String> weat;
	private List<String> wcid;
	private List<String> cur;
	private List<String> bcat;
	private List<String> badv;
	private String callback;
	private RedStoneExt ext;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<RedStoneImp> getImp() {
		return imp;
	}
	public void setImp(List<RedStoneImp> imp) {
		this.imp = imp;
	}
	public RedStoneApp getApp() {
		return app;
	}
	public void setApp(RedStoneApp app) {
		this.app = app;
	}
	public RedStoneDevice getDevice() {
		return device;
	}
	public void setDevice(RedStoneDevice device) {
		this.device = device;
	}
	public RedStoneUser getUser() {
		return user;
	}
	public void setUser(RedStoneUser user) {
		this.user = user;
	}
	public int getAt() {
		return at;
	}
	public void setAt(int at) {
		this.at = at;
	}
	public int getTmax() {
		return tmax;
	}
	public void setTmax(int tmax) {
		this.tmax = tmax;
	}
	public List<String> getWeat() {
		return weat;
	}
	public void setWeat(List<String> weat) {
		this.weat = weat;
	}
	public List<String> getWcid() {
		return wcid;
	}
	public void setWcid(List<String> wcid) {
		this.wcid = wcid;
	}
	public List<String> getCur() {
		return cur;
	}
	public void setCur(List<String> cur) {
		this.cur = cur;
	}
	public List<String> getBcat() {
		return bcat;
	}
	public void setBcat(List<String> bcat) {
		this.bcat = bcat;
	}
	public List<String> getBadv() {
		return badv;
	}
	public void setBadv(List<String> badv) {
		this.badv = badv;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
