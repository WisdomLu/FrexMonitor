package com.ocean.persist.api.proxy.adwo;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月13日 
      @version 1.0 
 */
public class AdwoAdPullParams      extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2577826300061465832L;
	private String pid;// Y String ADWO 分配的ID，32 位对外称为广告位ID
	private int format ;// Y int 广告信息返回格式3、feeds 4、video
	private int w;//  N int 请求广告位的width 当forma=4 时此字段必填
	private int  h;// N int 请求广告位的height
	private AdwoApp app;//  Y Object 程序信息app object App Object

	private AdwoDevice dev ;// Y Object 设备信息device object Device Object
	private AdwoFeeds feeds ;// N Object feeds 当format=3 时必填
	/*
	 * // N Int 标签id
	需要有标签关联的广告时传递，另
	外需要提供标签表（传了该字段值
	则只有与之对应的广告才会下发）
	 * */
	private int tagid ;
	private int ihttps ;// N int 是否返回https 资源,1 返回0 返回http,图片资源、统计接口
	
	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public int getFormat() {
		return format;
	}


	public void setFormat(int format) {
		this.format = format;
	}


	public int getW() {
		return w;
	}


	public void setW(int w) {
		this.w = w;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}


	public AdwoApp getApp() {
		return app;
	}


	public void setApp(AdwoApp app) {
		this.app = app;
	}


	public AdwoDevice getDev() {
		return dev;
	}


	public void setDev(AdwoDevice dev) {
		this.dev = dev;
	}


	public AdwoFeeds getFeeds() {
		return feeds;
	}


	public void setFeeds(AdwoFeeds feeds) {
		this.feeds = feeds;
	}


	public int getTagid() {
		return tagid;
	}


	public void setTagid(int tagid) {
		this.tagid = tagid;
	}


	public int getIhttps() {
		return ihttps;
	}


	public void setIhttps(int ihttps) {
		this.ihttps = ihttps;
	}

}
