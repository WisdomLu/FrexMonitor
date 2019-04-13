package com.ocean.persist.api.proxy.ryan;

import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanNativeReq   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4021739038307050974L;
	private Map<String,Object> title;//required,标题  {key:len value:<integer required 最大标题长度， 单位：字符>,key:ext value:<object optional 扩展字段>};
	private Map<String,Object> desc;//required,描述    {key:len value:<integer required 最大描述长度， 单位：字符>,key:ext value:<object optional 扩展字段>};
	private Map<String,Object> icon;//required,图标    {key:h value:<integer required 高度， 单位： 像素>,key:w value:<integer required 宽度， 单位： 像素>,key:ext value:<object optional 扩展字段>};
	private Map<String,Object> img;//required,图片      {key:h value:<integer required 高度， 单位： 像素>,key:w value:<integer required 宽度， 单位： 像素>,key:ext value:<object optional 扩展字段>};
	private Object ext;//optional,扩展字段
	public Map<String, Object> getTitle() {
		return title;
	}
	public void setTitle(Map<String, Object> title) {
		this.title = title;
	}
	public Map<String, Object> getDesc() {
		return desc;
	}
	public void setDesc(Map<String, Object> desc) {
		this.desc = desc;
	}
	public Map<String, Object> getIcon() {
		return icon;
	}
	public void setIcon(Map<String, Object> icon) {
		this.icon = icon;
	}
	public Map<String, Object> getImg() {
		return img;
	}
	public void setImg(Map<String, Object> img) {
		this.img = img;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	};
}
