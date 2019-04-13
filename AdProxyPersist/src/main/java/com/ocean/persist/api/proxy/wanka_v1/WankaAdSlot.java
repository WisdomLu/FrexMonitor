package com.ocean.persist.api.proxy.wanka_v1;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class WankaAdSlot {
	private String adslot_id;/*
	string
	是
	广告位 Id*/
	private int render_type;

	private int deeplink;

	public String getAdslot_id() {
		return adslot_id;
	}

	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}

	public int getRender_type() {
		return render_type;
	}

	public void setRender_type(int render_type) {
		this.render_type = render_type;
	}

	public int getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(int deeplink) {
		this.deeplink = deeplink;
	}
	
}
