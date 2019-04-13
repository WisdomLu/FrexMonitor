package com.ocean.persist.api.proxy.yuansheng;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengWord {
	private String id	;//string	Y	文字的id，用于区分一个广告位中的不同文字
	private int wtyp		;//integer	Y	文字说明类型A3
	private String cnt		;//string	Y	文字内容
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getWtyp() {
		return wtyp;
	}
	public void setWtyp(int wtyp) {
		this.wtyp = wtyp;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

}
