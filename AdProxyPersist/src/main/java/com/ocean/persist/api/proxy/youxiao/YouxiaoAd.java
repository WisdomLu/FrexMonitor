package com.ocean.persist.api.proxy.youxiao;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月16日 
      @version 1.0 
 */
public class YouxiaoAd {
	private String error_code		;//广告主错误码	用于排查广告位异常，非必有
	private String yxviewid		;//viewid	请求广告产生的唯一标识
	private String pr_id		;//产品id	广告主产品id
	private String clk_url	;//	目标跳转url	落地页网址
	private List<String>  exp_track	;//	用于展现上报	Array格式，可能是多个url
	private List<String>  clk_track		;//	用于点击上报	Array格式，可能是多个url
	/*
	 * 用于转化上报	Array格式，多个url,每个url又是一个Array, starturl 开始下载
	doneurl 下载完成
	installurl 开始安装
	installdoneurl 安装完成
	
	 * */
	private YouxiaoTrack inv_track	;//
	private int useraction	;//		广告下发类型	取值: 0 unknown 1 普通链接 2 android app 3 ios app
	private String img	;//		图片url	广告大图片url
	private String 	icon	;//		图标url	广告小图标url
	private int yxadvtype	;//		广告类型id	取值1:开屏 2:插屏 3:banner 13:信息流
	private String title	;//		标题	
	private String desc		;//	描述	
	private int crt_type	;//		素材类型	0:未知1;文字; 2：图片 3:图文为准
	private int posw		;//	宽	
	private int posh	;//		高	
	private int apitype		;//	广告来源	0:默认1;官方JS 2：自有广告 4:ADX
	private String aci_id	;//		广告主id	
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getYxviewid() {
		return yxviewid;
	}
	public void setYxviewid(String yxviewid) {
		this.yxviewid = yxviewid;
	}
	public String getPr_id() {
		return pr_id;
	}
	public void setPr_id(String pr_id) {
		this.pr_id = pr_id;
	}
	public String getClk_url() {
		return clk_url;
	}
	public void setClk_url(String clk_url) {
		this.clk_url = clk_url;
	}
	public List<String> getExp_track() {
		return exp_track;
	}
	public void setExp_track(List<String> exp_track) {
		this.exp_track = exp_track;
	}
	public List<String> getClk_track() {
		return clk_track;
	}
	public void setClk_track(List<String> clk_track) {
		this.clk_track = clk_track;
	}
	public YouxiaoTrack getInv_track() {
		return inv_track;
	}
	public void setInv_track(YouxiaoTrack inv_track) {
		this.inv_track = inv_track;
	}
	public int getUseraction() {
		return useraction;
	}
	public void setUseraction(int useraction) {
		this.useraction = useraction;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getYxadvtype() {
		return yxadvtype;
	}
	public void setYxadvtype(int yxadvtype) {
		this.yxadvtype = yxadvtype;
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
	public int getCrt_type() {
		return crt_type;
	}
	public void setCrt_type(int crt_type) {
		this.crt_type = crt_type;
	}
	public int getPosw() {
		return posw;
	}
	public void setPosw(int posw) {
		this.posw = posw;
	}
	public int getPosh() {
		return posh;
	}
	public void setPosh(int posh) {
		this.posh = posh;
	}
	public int getApitype() {
		return apitype;
	}
	public void setApitype(int apitype) {
		this.apitype = apitype;
	}
	public String getAci_id() {
		return aci_id;
	}
	public void setAci_id(String aci_id) {
		this.aci_id = aci_id;
	}
}
