package com.ocean.persist.api.proxy.yijifen;

import java.util.List;
import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class YijifenAdContent  implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	private Integer creative_type;// 广告创意类型： 1.文字 2.图片 3.图文 4.Flash 5.视频 6.h5

	private String title;// 标题，非html非原生广告时有效果
	private String words;// 广告描述，非html非原生广告时有效
	private String image_url;// 图片地址
	private String click_url;// 广告点击链接，非html非原生广告时有效
	private List<String> feedback_pv_address;// 展示上报地址
	private List<String> feedback_click_address;// 点击上报地址
	private List<String> feedback_down_address;// 下载完成上报地址
	private String html_content;// Html代码，仅在html5类型广告时有效
	// 点击后动作类型可能取值 0.打开native详情页 1.应用内下载 2.webview方式打开
	// 3.浏览器打开 4.应用内下载且支持触发url 5.直接跳转AppStore
	private Integer landing_type;
	private Integer adType;// 广告位类型。 1:BANNER 2:插屏 3:开屏 4.信息流
	private Integer adcategory;// 广告类型,见附录
	private YijifenNativeCreative nativeCreaqtive;// 原生广告属性

	public Integer getCreative_type() {
		return creative_type;
	}

	public void setCreative_type(Integer creative_type) {
		this.creative_type = creative_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public List<String> getFeedback_pv_address() {
		return feedback_pv_address;
	}

	public void setFeedback_pv_address(List<String> feedback_pv_address) {
		this.feedback_pv_address = feedback_pv_address;
	}

	public List<String> getFeedback_click_address() {
		return feedback_click_address;
	}

	public void setFeedback_click_address(List<String> feedback_click_address) {
		this.feedback_click_address = feedback_click_address;
	}

	public List<String> getFeedback_down_address() {
		return feedback_down_address;
	}

	public void setFeedback_down_address(List<String> feedback_down_address) {
		this.feedback_down_address = feedback_down_address;
	}

	public String getHtml_content() {
		return html_content;
	}

	public void setHtml_content(String html_content) {
		this.html_content = html_content;
	}

	public Integer getLanding_type() {
		return landing_type;
	}

	public void setLanding_type(Integer landing_type) {
		this.landing_type = landing_type;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public Integer getAdcategory() {
		return adcategory;
	}

	public void setAdcategory(Integer adcategory) {
		this.adcategory = adcategory;
	}

	public YijifenNativeCreative getNativeCreaqtive() {
		return nativeCreaqtive;
	}

	public void setNativeCreaqtive(YijifenNativeCreative nativeCreaqtive) {
		this.nativeCreaqtive = nativeCreaqtive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
