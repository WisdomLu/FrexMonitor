package com.ocean.persist.api.proxy.yuansheng;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengImpResp {
	private String impid	;//string	Y	广告曝光id，与ad request中imp对象中的id相同。
	private YuanshengBnaner impbanner	;//object	Y	banner广告展示数据，见4.1.3。
	private YuanshengNativeResp impnative	;//object	Y	原生广告展示数据，见4.1.4。
	public String getImpid() {
		return impid;
	}
	public void setImpid(String impid) {
		this.impid = impid;
	}
	public YuanshengBnaner getImpbanner() {
		return impbanner;
	}
	public void setImpbanner(YuanshengBnaner impbanner) {
		this.impbanner = impbanner;
	}
	public YuanshengNativeResp getImpnative() {
		return impnative;
	}
	public void setImpnative(YuanshengNativeResp impnative) {
		this.impnative = impnative;
	}

}
