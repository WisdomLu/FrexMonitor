package com.ocean.persist.api.proxy.qidian;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年4月4日 
      @version 1.0 
 */
public class QidianAd {

	private long id;/* long 广告唯一id*/
	private String title;/* St ring 广告标题（需urldecode）*/
	private String summary ;/*St ring 广告描述（需urldecode）*/
	private String link;/* St ring 广告链接地址或者是app下载地址*/
	private List<String> clickUrls;/* Array
	广告链接点击后的回调地址数组，里面的地址需全部调用。务
	必使用GET方式请求*/
	private List<String> trackUrls ;/*Array
	广告展示后的回调地址，里面的地址需全部调用。务必使用
	GET方式请求*/
	private String packageName ;/*St ring
	Apk广告下载包包名，当广告类型是0时，该字段的返回值才存
	在*/
	private String md5 ;/*St ring apk包MD5值，t ype=0时不为空*/
	private String version ;/*St ring
	版本名和版本号使用，拼接而成，
	例如1.4.0.801,60，t ype=0时不为空*/
	private int type ;/*int
	广告类型：0-安卓非游戏应用 1-网站 2-安卓游戏应用 3-ios下
	载类*/
	private String image1 ;/*St ring 广告图片地址，对应用类此字段为应用icon*/
	private String image2 ;/*St ring 广告图片地址*/
	private String image3 ;/*St ring 广告图片地址*/
	private int standardId ;/*int 广告位规格id*/
	private int posId;/* int 传入参数的简单回传，位置id*/
	private int imgNum;/* int 图片数量*/
	private String img1Size ;/*St ring 图片1大小，形如1200*500*/
	private String img2Size ;/*St ring 图片2大小，形如1200*500
	奇点系统广告获取接口 - 6/9*/
	private String img3Size ;/*St ring 图片3大小，形如1200*500*/
	private String dsUrl ;/*St ring 下载开始回调地址，非下载类广告该字段为空*/
	private String dfUrl ;/*St ring 下载完成回调地址，非下载类广告该字段为空*/
	private String sfUrl ;/*St ring 安装完成回调地址，非下载类广告该字段为空*/
	private int splashCountdown ;/*int 开屏广告建议倒计时时间（单位秒），非开屏广告为-1*/
	private int splashMaxPerDay;/* int 开屏广告建议一天重复显示最大次数，非开屏广告为-1*/
	private int splashInterval ;/*int
	开屏广告建议两次相同广告出现最小间隔（单位秒），非开屏
	广告为-1*/
	private long splashStime ;/*long
	开屏广告计划开始时间，此时间前不要展示此广告，值为时间
	戳（从1970.1.1至今毫秒数），非开屏广告为-1*/
	private long splashEtime;/* long
	开屏广告计划结束时间，此时间后不要展示此广告，值为时间
	戳（从1970.1.1至今毫秒数），非开屏广告为-1*/
	private int splashType ;/*int 开屏广告类型 1 全屏 2 半屏，非开屏广告为-1*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<String> getClickUrls() {
		return clickUrls;
	}
	public void setClickUrls(List<String> clickUrls) {
		this.clickUrls = clickUrls;
	}
	public List<String> getTrackUrls() {
		return trackUrls;
	}
	public void setTrackUrls(List<String> trackUrls) {
		this.trackUrls = trackUrls;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public int getStandardId() {
		return standardId;
	}
	public void setStandardId(int standardId) {
		this.standardId = standardId;
	}
	public int getPosId() {
		return posId;
	}
	public void setPosId(int posId) {
		this.posId = posId;
	}
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	public String getImg1Size() {
		return img1Size;
	}
	public void setImg1Size(String img1Size) {
		this.img1Size = img1Size;
	}
	public String getImg2Size() {
		return img2Size;
	}
	public void setImg2Size(String img2Size) {
		this.img2Size = img2Size;
	}
	public String getImg3Size() {
		return img3Size;
	}
	public void setImg3Size(String img3Size) {
		this.img3Size = img3Size;
	}
	public String getDsUrl() {
		return dsUrl;
	}
	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}
	public String getDfUrl() {
		return dfUrl;
	}
	public void setDfUrl(String dfUrl) {
		this.dfUrl = dfUrl;
	}
	public String getSfUrl() {
		return sfUrl;
	}
	public void setSfUrl(String sfUrl) {
		this.sfUrl = sfUrl;
	}
	public int getSplashCountdown() {
		return splashCountdown;
	}
	public void setSplashCountdown(int splashCountdown) {
		this.splashCountdown = splashCountdown;
	}
	public int getSplashMaxPerDay() {
		return splashMaxPerDay;
	}
	public void setSplashMaxPerDay(int splashMaxPerDay) {
		this.splashMaxPerDay = splashMaxPerDay;
	}
	public int getSplashInterval() {
		return splashInterval;
	}
	public void setSplashInterval(int splashInterval) {
		this.splashInterval = splashInterval;
	}
	public long getSplashStime() {
		return splashStime;
	}
	public void setSplashStime(long splashStime) {
		this.splashStime = splashStime;
	}
	public long getSplashEtime() {
		return splashEtime;
	}
	public void setSplashEtime(long splashEtime) {
		this.splashEtime = splashEtime;
	}
	public int getSplashType() {
		return splashType;
	}
	public void setSplashType(int splashType) {
		this.splashType = splashType;
	}
}
