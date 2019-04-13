package com.ocean.persist.api.proxy.speed;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
public class SpeedMedia   implements AdPullResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = -635857116350629375L;
	private String html;// 广告代码
    private int event;//交互类型 1-打开网页 2-下载应用 3-拨打电话 4-播放视频 5-播放音频 6-打开地图
    private String target;//落地页
    /*信息流广告才会有*/
    private String icon;//小图
    private String image;//大图
    private String title;//广告标题
    private String desc;//广告描述
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
}
