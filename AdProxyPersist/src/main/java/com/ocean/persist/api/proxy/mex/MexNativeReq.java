package com.ocean.persist.api.proxy.mex;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class MexNativeReq {

	private static final long serialVersionUID = 1L;
	
	// required 	原生广告布局类型（详见字典 原生广告布局类型）。根据类型的不同，
	// MEX在返回的数据中按照MEX原生广告的标准填充所需数据
	// 1 	内容墙 
	// 2 	应用墙 
	// 3 	信息流  
	// 4 	对话列表 
	// 5 	旋转木马 
	// 6 	内容流 
	// 7 	方格连接内容 
	// 500 	通知栏信息 
	// 501+  	暂未使用 
	//private Integer layout;
	private List<String> required;
	private int maxtitle;
	private int maxdesc;
	private int type;
	private List<MexImage> img;
	private MexImage icon;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getRequired() {
		return required;
	}

	public void setRequired(List<String> required) {
		this.required = required;
	}

	public int getMaxtitle() {
		return maxtitle;
	}

	public void setMaxtitle(int maxtitle) {
		this.maxtitle = maxtitle;
	}

	public int getMaxdesc() {
		return maxdesc;
	}

	public void setMaxdesc(int maxdesc) {
		this.maxdesc = maxdesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<MexImage> getImg() {
		return img;
	}

	public void setImg(List<MexImage> img) {
		this.img = img;
	}

	public MexImage getIcon() {
		return icon;
	}

	public void setIcon(MexImage icon) {
		this.icon = icon;
	}
}
