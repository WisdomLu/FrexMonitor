package com.ocean.persist.app.dis.yitong.applist;

import java.util.List;

public class ListSearchYitongApp {
	private int ad_mode;// ": 2,//广告投放模式  如：CPT = 1;CPD = 2 …
	private String app_key;//": "10003",//app唯一标示
	private String app_name;//": "微博",//应用名称
	private String app_package;//": "com.sina.weibo",//包名
	private int app_file_size;//": 29203416, //应用大小，包大小 字节
	private String app_icon;//": "http://img.r1.market.hiapk.com/data/upload/2014/10_24/18/20141024062231_0086.png",//icon图片地址
	private String icon_small;//": "http://cdn00.baidu-img.cn/timg?vsapp&size=b800_800&quality=100&imgtype=3&er&sec=0&di=a84f1bb036f547a511375e8f1363f0c7&ref=http%3A%2F%2Fa.hiphotos.bdimg.com&src=http%3A%2F%2Fa.hiphotos.bdimg.com%2Fwisegame%2Fpic%2Fitem%2Fa81349540923dd5496deef08d209b3de9c824825.jpg",//小图icon
	private String banner;//": "http://nj.bs.baidu.com/bm-bmp/%2Fupload%2Fmaterial%2Fsource%2Ftmp%2F54fd5f82022be.jpg",//图片格式jpg、png、jpeg，像素640x100、不大于100k
    private String banner1;//":"http://nj.bs.baidu.com/bm-bmp/%2Fupload%2Fmaterial%2Fsource%2Ftmp%2F54fd5f82022be.jpg",//banner大图片  图片格式jpg、png、jpeg，像素828x341
	private List<String> downloadstart_url;//”:[“ http://btlaunch.baidu.com/baitong/index.php?r=InterfaceBTAction&m=download&trans=6c2bbbd7a32f5d5359d47856&unique=97d030cc&ad_type=1&ad_mode=2&source=&pos=1&dpos=1&platform=android&clienttype=client&abflag=&abversion=1”,”http://www.ytadunion.com/ad/adapp/dev/downloadstart”], //计费地址前半部分
	private List<String> downloadfinish_url;//”:[“ http://btlaunch.baidu.com/baitong/index.php?r=InterfaceBTAction&m=download&trans=6c2bbbd7a32f5d5359d47856&unique=97d030cc&ad_type=1&ad_mode=2&source=&pos=1&dpos=1&platform=android&clienttype=client&abflag=&abversion=1&down_complete=1”, ”http://www.ytadunion.com/ad/adapp/dev/downloadfinish”]
	private String category;/*//": "100001,2,504",//分类
		category格式如下：
		《软件分类》,《行业分类》,《软件分类》 
		(逗号隔开，具体编码意义建文档最后附件)*/
	private String real_dowload_url;//": "http:\/\/m.baidu.com\/api?xxxx"  IOS使用跳转appstore地址或者专用下载平台地址
	private int downnum;//": 4655031,//下载量
	private String open_url;//": "",//ios用于打开调用
	private String platform;//": "android",//平台
	private String source;//": "",//物料来源，已经废弃
	private String comment;//": "",//编辑信息
	private String version_code;//": 1487
	private String package_type;//":0
	private String screen1;//":"http:\/\/nj.bs.baidu.com\/bm-bmp\/%2Ftestenviron%2Fupload%2Fmaterial%2Fsource%2Ftmp%2F55a3310b64ca8.jpg?sign=MBO:12xHxpVsa1JfzVrdQkiHYvlbIf:ZWb63ejsXlS5AilQV5p8QrYAxjo%3D_600X500.jpg" 
    //插屏一：图片格式jpg、png、jpeg，像素600*500，不大于80k
	private String screen2;//":"http://nj.bs.baidu.com/bm-bmp/%2Ftestenviron%2Fupload%2Fmaterial%2Fsource%2Ftmp%2F55ff8705e52fd.JPG?sign=MBO:12xHxpVsa1JfzVrdQkiHYvlbIf:LQErP8XxgI4Y5HPPv6EsAaxIgU0%3D_320X480.jpg"
	//插屏二：图片格式jpg、png、jpeg，像素320*480，不大于100k
	private String starlevel;//":"75",星级
	private String trans;//":"f6e743587f6c06ed9e9eb587",计费串
	private String version_name;//":"4.0.2"
	private String price;//":42 广告价格
	private String desc;
	public int getAd_mode() {
		return ad_mode;
	}
	public void setAd_mode(int ad_mode) {
		this.ad_mode = ad_mode;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getApp_package() {
		return app_package;
	}
	public void setApp_package(String app_package) {
		this.app_package = app_package;
	}
	public int getApp_file_size() {
		return app_file_size;
	}
	public void setApp_file_size(int app_file_size) {
		this.app_file_size = app_file_size;
	}
	public String getApp_icon() {
		return app_icon;
	}
	public void setApp_icon(String app_icon) {
		this.app_icon = app_icon;
	}
	public String getIcon_small() {
		return icon_small;
	}
	public void setIcon_small(String icon_small) {
		this.icon_small = icon_small;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getBanner1() {
		return banner1;
	}
	public void setBanner1(String banner1) {
		this.banner1 = banner1;
	}
	public List<String> getDownloadstart_url() {
		return downloadstart_url;
	}
	public void setDownloadstart_url(List<String> downloadstart_url) {
		this.downloadstart_url = downloadstart_url;
	}
	public List<String> getDownloadfinish_url() {
		return downloadfinish_url;
	}
	public void setDownloadfinish_url(List<String> downloadfinish_url) {
		this.downloadfinish_url = downloadfinish_url;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReal_dowload_url() {
		return real_dowload_url;
	}
	public void setReal_dowload_url(String real_dowload_url) {
		this.real_dowload_url = real_dowload_url;
	}
	public int getDownnum() {
		return downnum;
	}
	public void setDownnum(int downnum) {
		this.downnum = downnum;
	}
	public String getOpen_url() {
		return open_url;
	}
	public void setOpen_url(String open_url) {
		this.open_url = open_url;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public String getPackage_type() {
		return package_type;
	}
	public void setPackage_type(String package_type) {
		this.package_type = package_type;
	}
	public String getScreen1() {
		return screen1;
	}
	public void setScreen1(String screen1) {
		this.screen1 = screen1;
	}
	public String getScreen2() {
		return screen2;
	}
	public void setScreen2(String screen2) {
		this.screen2 = screen2;
	}
	public String getStarlevel() {
		return starlevel;
	}
	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
