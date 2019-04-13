package com.ocean.persist.api.proxy.shaibo;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboAdImp    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 307256344717552437L;
	private String impid ;//	string 	曝光id。
	private float price 	;//float 	CPM(元),广告出价(不一定有)。
	private String compid 	;//string 	广告投放公司id。
	private String crid 	;//string 	广告创意id。
	private List<ShaiboAdImg> image;
	private List<ShaiboAdWord> word;
	private List<ShaiboAdVideo> video;
	private String htmlstring 	;//string 	html代码片段。
	private String link 	;//string 	广告落地页或资源下载地址。
	private String 	deeplink 	;//string 	deeplink地址，deeplink广告才返回该字段。
	private int action  ;  //	int 	link类型,1:落地页,2:资源下载。
	private List<String> imptk 	; //list of string 	广告曝光回调地址数组。
	private List<String> clicktk 	;//list of string 	广告点击回调地址,(clicktk数组可能为空)。
	private List<String> playtk 	;//list of string 	视频广告播放回调地址。
	private ShaiboExt ext 	;//dict 	广告返回中的扩展字段,一般情况下不需要处理,参见4.6。
	public String getImpid() {
		return impid;
	}
	public void setImpid(String impid) {
		this.impid = impid;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public String getCrid() {
		return crid;
	}
	public void setCrid(String crid) {
		this.crid = crid;
	}
	public String getHtmlstring() {
		return htmlstring;
	}
	public void setHtmlstring(String htmlstring) {
		this.htmlstring = htmlstring;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public List<String> getImptk() {
		return imptk;
	}
	public void setImptk(List<String> imptk) {
		this.imptk = imptk;
	}
	public List<String> getClicktk() {
		return clicktk;
	}
	public void setClicktk(List<String> clicktk) {
		this.clicktk = clicktk;
	}
	public List<String> getPlaytk() {
		return playtk;
	}
	public List<ShaiboAdImg> getImage() {
		return image;
	}
	public void setImage(List<ShaiboAdImg> image) {
		this.image = image;
	}
	public List<ShaiboAdWord> getWord() {
		return word;
	}
	public void setWord(List<ShaiboAdWord> word) {
		this.word = word;
	}
	public List<ShaiboAdVideo> getVideo() {
		return video;
	}
	public void setVideo(List<ShaiboAdVideo> video) {
		this.video = video;
	}
	public void setPlaytk(List<String> playtk) {
		this.playtk = playtk;
	}
	public ShaiboExt getExt() {
		return ext;
	}
	public void setExt(ShaiboExt ext) {
		this.ext = ext;
	}
}
