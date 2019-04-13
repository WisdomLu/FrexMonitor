package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouAdReq  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151383831941804880L;
	private int type;//广告类型， 0：横幅 ,，1: 插屏 , 2：开屏， 3：原生 ，4:视频
	private String place_id;//广告位 id ，由 ssp 给
	private float floor_price;//底价，单位为分
	private int w;//广告位宽度
	private int h;//广告位高度
	private int pos;//广告位位置，0：未知， 4：头部， 5：底部， 6：侧边栏， 7：全屏
	private List<Integer> inventory_types;//支持的广告资源类型 , 1:图片， 2: 图文， 3: 视频， 4:html5，5: 文本， 6: 原生 ， 7:html5 url, 即一个指向html5素材页面的 url。如果为空 ，则默认只支持 1:图片
    private ZhangYouNativeReq _native;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public float getFloor_price() {
		return floor_price;
	}
	public void setFloor_price(float floor_price) {
		this.floor_price = floor_price;
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
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}

	public ZhangYouNativeReq get_native() {
		return _native;
	}
	public void set_native(ZhangYouNativeReq _native) {
		this._native = _native;
	}
	public List<Integer> getInventory_types() {
		return inventory_types;
	}
	public void setInventory_types(List<Integer> inventory_types) {
		this.inventory_types = inventory_types;
	}

}
