package com.ocean.persist.api.proxy.boyuV2;

import java.util.List;

public class BoyuV2Ad {
		
	private int id;//	int	0											广告序号		
	private long creativeId;//	long	60013468										广告唯一ID		
	private String title;//	string		360%E5%80%9F%E6%9D%A1	urlencode之后的标题信息		
	private String summary	;//string		360%E5%A5%87%E8%99%8E...	urlencode之后的描述信息		
	private String link;//	string		http:\/\/rd.e.sogou.com\/ck?url=...	广告链接地址或者是app下载地址			
	private List<String> clickTrackUrls	;//array		["http://rd.e.sogou.com/nck?	广告链接点击后的回调地址数组，里面的地址需	url=&st=49CjS9UTfm"]	全部调用		
    private List<String> impTrackUrls;//	array		["http://rd.e.sogou.com/nvw?url="]	广告展示后的回调地址，里面的地址需全部调用		
	private double adWidth;//	double	1200.0										广告位宽度		
	private double adHeight;//	double	500.0										广告位高度	表示⼴告的展示类型。1.原⽣图⽂⼴告	
	private int adType	;//int	0											2.banner⼴告 3.开屏⼴告 4.插屏⼴告 5.原⽣视	频⼴告 9. 其他广告		
	private int downloadAd	;//int	1											表示是否是下载类广告。1.下载类 0.非下载类	表示⼴告来源。urlEncode之后的字符串。广告	
	private String source	;//string		%e6%90%9c%e7%8b%97%e5%a5%87%e7%82%b9	主填写该字段，则填充为广告主填写的字段，否	则为默认值"搜狗"，	nativeAd_type: 新增字段，当adType=1时，该	
	private int nativeAdType	;//int	1											字段有值。1.small（⼩图）2.big（⼤图）3.group（组图）9. 其他			
	private List<BoyuV2Img> imgs	;//array													广告图片信息		
	private BoyuV2App appInfo	;//object													包信息，下载类广告才有		
	private BoyuV2SplashInfo splashInfo	;//object													开屏广告信息	
	private BoyuV2Video video	;//object		视频信息	
	public int getId() {
		return id;
	}
	public long getCreativeId() {
		return creativeId;
	}
	public String getTitle() {
		return title;
	}
	public String getSummary() {
		return summary;
	}
	public String getLink() {
		return link;
	}
	public List<String> getClickTrackUrls() {
		return clickTrackUrls;
	}
	public List<String> getImpTrackUrls() {
		return impTrackUrls;
	}
	public double getAdWidth() {
		return adWidth;
	}
	public double getAdHeight() {
		return adHeight;
	}
	public int getAdType() {
		return adType;
	}
	public int getDownloadAd() {
		return downloadAd;
	}
	public String getSource() {
		return source;
	}
	public int getNativeAdType() {
		return nativeAdType;
	}
	public List<BoyuV2Img> getImgs() {
		return imgs;
	}
	public BoyuV2App getAppInfo() {
		return appInfo;
	}
	public BoyuV2SplashInfo getSplashInfo() {
		return splashInfo;
	}
	public BoyuV2Video getVideo() {
		return video;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCreativeId(long creativeId) {
		this.creativeId = creativeId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setClickTrackUrls(List<String> clickTrackUrls) {
		this.clickTrackUrls = clickTrackUrls;
	}
	public void setImpTrackUrls(List<String> impTrackUrls) {
		this.impTrackUrls = impTrackUrls;
	}
	public void setAdWidth(double adWidth) {
		this.adWidth = adWidth;
	}
	public void setAdHeight(double adHeight) {
		this.adHeight = adHeight;
	}
	public void setAdType(int adType) {
		this.adType = adType;
	}
	public void setDownloadAd(int downloadAd) {
		this.downloadAd = downloadAd;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setNativeAdType(int nativeAdType) {
		this.nativeAdType = nativeAdType;
	}
	public void setImgs(List<BoyuV2Img> imgs) {
		this.imgs = imgs;
	}
	public void setAppInfo(BoyuV2App appInfo) {
		this.appInfo = appInfo;
	}
	public void setSplashInfo(BoyuV2SplashInfo splashInfo) {
		this.splashInfo = splashInfo;
	}
	public void setVideo(BoyuV2Video video) {
		this.video = video;
	}
	
	
}
