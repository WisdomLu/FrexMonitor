package com.ocean.persist.api.proxy.adwo;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月13日 
      @version 1.0 
 */
public class AdwoFeeds    extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8449740288944372138L;
	private int imgw ;//N int 大图宽尺寸 当format=3 并且需要大图时必填
	private int imgh ;//N int 大图高尺寸
	/*
	 * //N int 标题内容长度
		=0 时代表文字长度随意;
		>0 时代表文字长度不能超过此限制
		<0 或不传此参数时代表不需要
	 * */
	private int tlen ;
	private int icow ;//N int icon 宽,当format=3 并且需要icon 时必填
	private int icoh ;//N int Icon 高
	private int desclen ;//N int 描述内容长度同tlen
	private int sumlen ;//N int 简介内容长度同tlen
	
	
	//resp
	private String img ;//N String 大图地址
	private String ico ;// N Icon 图片地址
	private String txt ;// N String 标题
	private String desc  ;//N String 描述
	private String sum ;// N String 简介
	
	public int getImgw() {
		return imgw;
	}
	public void setImgw(int imgw) {
		this.imgw = imgw;
	}
	public int getImgh() {
		return imgh;
	}
	public void setImgh(int imgh) {
		this.imgh = imgh;
	}
	public int getTlen() {
		return tlen;
	}
	public void setTlen(int tlen) {
		this.tlen = tlen;
	}
	public int getIcow() {
		return icow;
	}
	public void setIcow(int icow) {
		this.icow = icow;
	}
	public int getIcoh() {
		return icoh;
	}
	public void setIcoh(int icoh) {
		this.icoh = icoh;
	}
	public int getDesclen() {
		return desclen;
	}
	public void setDesclen(int desclen) {
		this.desclen = desclen;
	}
	public int getSumlen() {
		return sumlen;
	}
	public void setSumlen(int sumlen) {
		this.sumlen = sumlen;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
}
