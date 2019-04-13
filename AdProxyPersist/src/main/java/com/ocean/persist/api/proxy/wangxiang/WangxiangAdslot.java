package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangAdslot {
	private String adslot_id	;//代码位id	必填，代码位id在ssp平台获取
	private WangxinagSize adslot_size;//	代码位尺寸	必填，当前设备可展现广告区域的尺寸
	public String getAdslot_id() {
		return adslot_id;
	}
	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}
	public WangxinagSize getAdslot_size() {
		return adslot_size;
	}
	public void setAdslot_size(WangxinagSize adslot_size) {
		this.adslot_size = adslot_size;
	}

}
