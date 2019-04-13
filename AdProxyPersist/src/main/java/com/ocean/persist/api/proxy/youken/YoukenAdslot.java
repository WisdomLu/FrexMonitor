package com.ocean.persist.api.proxy.youken;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月6日 
      @version 1.0 
 */
public class YoukenAdslot {
	private String adslot_id;//	string		是	广告位Id 
	private int adslot_w;//	int	0	是	广告位宽度（保留字段）
	private int adslot_h	;//int	0	是	广告位高度（保留字段）
	public String getAdslot_id() {
		return adslot_id;
	}
	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}
	public int getAdslot_w() {
		return adslot_w;
	}
	public void setAdslot_w(int adslot_w) {
		this.adslot_w = adslot_w;
	}
	public int getAdslot_h() {
		return adslot_h;
	}
	public void setAdslot_h(int adslot_h) {
		this.adslot_h = adslot_h;
	}

}
