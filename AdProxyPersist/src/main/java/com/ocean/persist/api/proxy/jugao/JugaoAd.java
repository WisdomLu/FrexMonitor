package com.ocean.persist.api.proxy.jugao;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月24日 
      @version 1.0 
 */
public class JugaoAd    implements AdPullResponse{
	 /**
	 * 
	 */
	    private static final long serialVersionUID = 1795336902339694583L;
	    private String adm;//adtype 为3、6 时，此字段为空 adtype 为1、2、4、5 时，返回完整的html
	    private String imgurl;//广告图片地址（视频广告为视频播放完毕贴片， 此种情况有可能为空）
	    private String clickurl;//落地页，点击链接地址
	    private List<String> imgtracking;//展示监控地址，可能为空，也可能是多个，如果不为空当广告展示的时候要逐条上报。所有广告 形式都需要处理
	    //点击监控地址，可能为空，也可能是多个，如果 不为空当广告被点击的时候要逐条上报。所有广 告形式都需要处理
	    private List<String> thclkurl;
	    private String displaytitle;//广告的标题。原生信息流广告返回
	    private String displaytext;//广告的文字描述。原生信息流广告返回
	    private String videourl;//视频广告的地址。 仅视频广告此字段返回
	    /*视频开始下载监控地址，可能为空，也可能是多
		个，如果不为空当广告被点击的时候要逐条上报。
		仅视频广告此字段返回*/
	    private List<String> vmu_d_start;
	    /*视频下载失败监控地址，可能为空，也可能是多
		个，如果不为空当广告被点击的时候要逐条上报。
		仅视频广告此字段返回*/
	    private List<String> vmu_d_fail;
	    /*视频下载成功监控地址，可能为空，也可能是多
		个，如果不为空当广告被点击的时候要逐条上报。
		仅视频广告此字段返回*/
	    private List<String> vmu_d_succ;
	    /*
	     * 视频开始播放监控地址，可能为空，也可能是多
		个，如果不为空当广告被点击的时候要逐条上报。
		仅视频广告此字段返回*/
	    private List<String> vmu_p_start;
	    /*视频播放失败监控地址，可能为空，也可能是多
		个，如果不为空当广告被点击的时候要逐条上报。
		仅视频广告此字段返回*/
	    private List<String> vmu_p_fail;
	    /*视频播放成功监控地址，可能为空，也可能是多
		个，如果不为空当广告被点击的时候要逐条上报。
		仅视频广告此字段返回*/
	    private List<String> vmu_p_succ;
	    /*视频播放预估时长，单位秒。
		仅视频广告此字段返回*/
	    private String duration;
	    /*0- 未知
		1- 图片展示
		2- 应用下载*/
	    private int adct;//广告点击行为
	    /*1- 视频广告
		2- 图片广告
		3- 图片＋文
		字广告(原生信
		息流)
		4- html 广告*/
	    private int admt;//广告类型
		public String getAdm() {
			return adm;
		}
		public void setAdm(String adm) {
			this.adm = adm;
		}
		public String getImgurl() {
			return imgurl;
		}
		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}
		public String getClickurl() {
			return clickurl;
		}
		public void setClickurl(String clickurl) {
			this.clickurl = clickurl;
		}
		public List<String> getImgtracking() {
			return imgtracking;
		}
		public void setImgtracking(List<String> imgtracking) {
			this.imgtracking = imgtracking;
		}
		public List<String> getThclkurl() {
			return thclkurl;
		}
		public void setThclkurl(List<String> thclkurl) {
			this.thclkurl = thclkurl;
		}
		public String getDisplaytitle() {
			return displaytitle;
		}
		public void setDisplaytitle(String displaytitle) {
			this.displaytitle = displaytitle;
		}
		public String getDisplaytext() {
			return displaytext;
		}
		public void setDisplaytext(String displaytext) {
			this.displaytext = displaytext;
		}
		public String getVideourl() {
			return videourl;
		}
		public void setVideourl(String videourl) {
			this.videourl = videourl;
		}
		public List<String> getVmu_d_start() {
			return vmu_d_start;
		}
		public void setVmu_d_start(List<String> vmu_d_start) {
			this.vmu_d_start = vmu_d_start;
		}
		public List<String> getVmu_d_fail() {
			return vmu_d_fail;
		}
		public void setVmu_d_fail(List<String> vmu_d_fail) {
			this.vmu_d_fail = vmu_d_fail;
		}
		public List<String> getVmu_d_succ() {
			return vmu_d_succ;
		}
		public void setVmu_d_succ(List<String> vmu_d_succ) {
			this.vmu_d_succ = vmu_d_succ;
		}
		public List<String> getVmu_p_start() {
			return vmu_p_start;
		}
		public void setVmu_p_start(List<String> vmu_p_start) {
			this.vmu_p_start = vmu_p_start;
		}
		public List<String> getVmu_p_fail() {
			return vmu_p_fail;
		}
		public void setVmu_p_fail(List<String> vmu_p_fail) {
			this.vmu_p_fail = vmu_p_fail;
		}
		public List<String> getVmu_p_succ() {
			return vmu_p_succ;
		}
		public void setVmu_p_succ(List<String> vmu_p_succ) {
			this.vmu_p_succ = vmu_p_succ;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public int getAdct() {
			return adct;
		}
		public void setAdct(int adct) {
			this.adct = adct;
		}
		public int getAdmt() {
			return admt;
		}
		public void setAdmt(int admt) {
			this.admt = admt;
		}
}
