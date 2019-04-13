package com.ocean.persist.api.proxy.yuansheng;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengImag {
	private String id;//	string	Y	图片的id，用于区分一个广告位中的不同图片
	private int imgtyp	;//整数	Y	图片展示类型。A4
	private String iurl	;//string	Y	图片链接地址。
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getImgtyp() {
		return imgtyp;
	}
	public void setImgtyp(int imgtyp) {
		this.imgtyp = imgtyp;
	}
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}

}
