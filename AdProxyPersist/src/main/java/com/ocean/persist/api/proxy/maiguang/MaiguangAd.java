package com.ocean.persist.api.proxy.maiguang;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月3日 
      @version 1.0 
 */
public class MaiguangAd   implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3745110427559003107L;
	
	
	private String aid;
	private String name;
	private String title;
	private String desc;
	private String img;
	private String url;
	private String icon;
	private int category;
	private String pk;
	private int fileSize;
	private int type;

	private String page;
	private int imgw;
	private int imgh;
	private List<MaiguangReport> cb;
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
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
	public List<MaiguangReport> getCb() {
		return cb;
	}
	public void setCb(List<MaiguangReport> cb) {
		this.cb = cb;
	}
}
