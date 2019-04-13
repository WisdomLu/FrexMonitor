package com.ocean.persist.model.proxy;

import java.sql.Timestamp;
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
@Table(name = "position_target")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PositionTarget extends  BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5533617066783691781L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "positionTargetGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "positionTargetGenerate", strategy = "native")
	private String id;// 广告位信息id  
	
	@Column(name = "dpid")
	private String dpid;// dsp广告位id,dsp_position主键     
	
	@Column(name = "ip")
	private String ip;// 定向ip    
	
	@Column(name = "imei")
	private String imei;// 定向imei
	
	//创建时间
	@Column(name = "cre_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creTime;// 时间
	
	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}
}
