package com.ocean.persist.model.proxy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import com.inveno.base.BaseModel;
@Entity
@Table(name = "adproxy_dsp_space")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DSPPosition extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8572593892505214462L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "DSPPositionGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "DSPPositionGenerate", strategy = "native")
	private String id;// 广告位信息id               
	@Column(name = "dsp")
	private String dsp;// 广告位所属dsp平台类型 ,现在取自DSP枚举类型
	
	@Column(name = "summary")
	private String summary;// 广告位标题      
	
	@Column(name = "pos")
	private String pos;// 广告位，三方dsp给过来的广告位id
	
	@Column(name = "pos_type")
	private Short posType;// 广告类型 1横幅 2开屏 3插屏 4信息流 5文字链
	
	@Column(name = "settlement_type")
	private Integer settlementType;// 结算类型CPM = 0,CPC = 1,CPI = 2,CPA = 3 
	
	@Column(name = "settlement_price")
	private Integer settlementPrice;// 价格单位 分                
	
	@Column(name = "text1")
	private String text1;// 额外的属性比如appid
	
	@Column(name = "text2")
	private String text2;// 额外的属性比如appkey
	
	@Column(name = "text3")
	private String text3;//额外的属性比如app package name
	@Column(name = "state")
	private int state;//状态
	
	@Column(name = "click_areas")//控制点击区域
	private String clickAreas;
	
	//创建时间
	@Column(name = "cre_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creTime;
	
	@Column(name = "silent_install_rate")
	private Integer siRate;
	@Column(name = "active_rate")
	private Integer activeRate;
	@Column(name = "click_rate")
	private Integer clickRate;
	@Column(name = "retry_down_times")
	private Integer retryDld;
	
	@Column(name = "video_flag")
	private Integer videoFlag;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Short getPosType() {
		return posType;
	}

	public void setPosType(Short posType) {
		this.posType = posType;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public Integer getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(Integer settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getClickAreas() {
		return clickAreas;
	}

	public void setClickAreas(String clickAreas) {
		this.clickAreas = clickAreas;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public Integer getSiRate() {
		return siRate;
	}

	public void setSiRate(Integer siRate) {
		this.siRate = siRate;
	}

	public Integer getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(Integer activeRate) {
		this.activeRate = activeRate;
	}

	public Integer getClickRate() {
		return clickRate;
	}

	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}

	public Integer getRetryDld() {
		return retryDld;
	}

	public void setRetryDld(Integer retryDld) {
		this.retryDld = retryDld;
	}

	public Integer getVideoFlag() {
		return videoFlag;
	}

	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}


}
