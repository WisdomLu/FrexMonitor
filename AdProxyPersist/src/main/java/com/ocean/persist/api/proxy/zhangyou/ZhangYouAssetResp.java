package com.ocean.persist.api.proxy.zhangyou;

import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月9日 
      @version 1.0 
 */
public class ZhangYouAssetResp  {
   /**
	 * 
	 */
   private static final long serialVersionUID = -7135815341289350181L;
   private int id;//广告元素id
   private int required;//广告元素是否必须显示， 1：必须， 0：可选， 默认为 1
	/*
	 * text String>标题文字
	 * 
	 * */
	private ZhangYouTitleResp title;//文字元素
	/*
	 * image元素的类型， 
	 * url String>imge url 地址
	 * w    int>image元素的宽度，单位为像素
	 * h    int>image元素的高德，单位为像素
	 * */
	private ZhangYouImageResp img;//图像元素
	/* label String>数据名称
	 * value String>数据正文
	 * */
	private ZhangYouDataResp data;//其他数据元素
	/*url       String>目标链接
	 *clicktracker 数组>点击追踪链接
	 *type         int>点击动作类型，1: 在 app 内 webview打开目标链接 2： 在系统浏览器打开目标链接 , 3：打开地图 4： 拨打电话， 5：播放视频 , 6:App 6:App, 6:App, 6:App
	 * */
	private ZhangYouLinkResp link;//点击目标链接
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRequired() {
		return required;
	}
	public void setRequired(int required) {
		this.required = required;
	}
	public ZhangYouTitleResp getTitle() {
		return title;
	}
	public void setTitle(ZhangYouTitleResp title) {
		this.title = title;
	}
	public ZhangYouImageResp getImg() {
		return img;
	}
	public void setImg(ZhangYouImageResp img) {
		this.img = img;
	}
	public ZhangYouDataResp getData() {
		return data;
	}
	public void setData(ZhangYouDataResp data) {
		this.data = data;
	}
	public ZhangYouLinkResp getLink() {
		return link;
	}
	public void setLink(ZhangYouLinkResp link) {
		this.link = link;
	}
   
}
