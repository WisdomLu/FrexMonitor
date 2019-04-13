package com.ocean.persist.api.proxy.yitong;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
public class YitongResource    extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5445421967055535825L;
	private String adcode;//": "https://images.sohu.com/bill/a2015/1118/CgpExFZMLv2AEJKqAAA6ssF8zyw877640x100.jpg", //不要使用该字段
	private List<String> admaster_click_imp;//": [], //第三方admaster点击监测地址(废弃)
	private List<String>  admaster_imp;//": [], //第三方admaster pv监测地址(废弃) 
	private String click;//": "httpss://www.baidu.com/", //点击跳转地址，调用sdk点击接口进行上报
	private List<String>  click_imp;//": [], //第三方点击监测地址
	private List<String> imp;//": [], //第三方PV监测地址
	private String file;//": "https://images.sohu.com/bill/a2015/1118/CgpExFZMLv2AEJKqAAA6ssF8zyw877640x100.jpg", //图片地址
	private String height;//": 100, //图片高 "imp": [], //第三方PV监测地址, 调用sdk加载接口进行上报 
	private String md5;//": "20qdp0q9U0000000ttF100tjE", //物料唯一标识mkey
	private List<String>  miaozhen_click_imp;//": [], //第三方秒针点击监测地址(废弃) 
	private List<String>  miaozhen_imp;//": [], //第三方秒针pv监测地址(废弃)
	private String text;//": "",
	private String type;//": "image", //返回类型，image代表图片
	private int width;//": 640 //图片宽
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	public List<String> getAdmaster_click_imp() {
		return admaster_click_imp;
	}
	public void setAdmaster_click_imp(List<String> admaster_click_imp) {
		this.admaster_click_imp = admaster_click_imp;
	}
	public List<String> getAdmaster_imp() {
		return admaster_imp;
	}
	public void setAdmaster_imp(List<String> admaster_imp) {
		this.admaster_imp = admaster_imp;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public List<String> getClick_imp() {
		return click_imp;
	}
	public void setClick_imp(List<String> click_imp) {
		this.click_imp = click_imp;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public List<String> getMiaozhen_click_imp() {
		return miaozhen_click_imp;
	}
	public void setMiaozhen_click_imp(List<String> miaozhen_click_imp) {
		this.miaozhen_click_imp = miaozhen_click_imp;
	}
	public List<String> getMiaozhen_imp() {
		return miaozhen_imp;
	}
	public void setMiaozhen_imp(List<String> miaozhen_imp) {
		this.miaozhen_imp = miaozhen_imp;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public List<String> getImp() {
		return imp;
	}
	public void setImp(List<String> imp) {
		this.imp = imp;
	}
}
