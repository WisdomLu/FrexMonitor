package com.ocean.persist.api.proxy.zhangyou;

import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouAssetReq   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3667417595288918994L;
	private int id;//广告元素id
	private int required;//广告元素是否必须， 1：必须， 0：可选， 默认为 1
	/*
	 * len int>title元素最大文字长度
	 * 
	 * */
	private Map<String,Object> title;//文字元素
	/*
	 * image元素的类型， 
	 * type int>1：图标， 2: 品牌 Logo, 3:大图
	 * w    int>image元素的宽度，单位为像素
	 * h    int>image元素的高德，单位为像素
	 * */
	private Map<String,Object> img;//图像元素
	/* type int>数据类型 1：Sponsor 名称， 应该包含品牌2：描述 ,  3：打分， 4：点赞个数， 5：下载个数， 6： 产品价格， 7：销售价格， 往和前者结合表示折扣8：电话， 9：地址， 10 ：描述 2， 11 ：显 示的链接， 12 ：行动按钮名称 ，1001 ：视频 url，1002：评论数
	 * len  int>元素最大文字长度
	 * */
	private Map<String,Object> data;//其他数据元素
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
	public Map<String, Object> getTitle() {
		return title;
	}
	public void setTitle(Map<String, Object> title) {
		this.title = title;
	}
	public Map<String, Object> getImg() {
		return img;
	}
	public void setImg(Map<String, Object> img) {
		this.img = img;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	

}
