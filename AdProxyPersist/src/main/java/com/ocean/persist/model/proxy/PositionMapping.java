package com.ocean.persist.model.proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import com.inveno.base.BaseModel;

@Entity
@Table(name = "adproxy_space_mapping")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PositionMapping extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5674759446420773883L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "positionMappingGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "positionMappingGenerate", strategy = "native")
	private String id;// 广告位信息id  
	

	
	@Column(name = "zpid")
	private String zpid;// 掌酷ssp广告位id    
	
	@Column(name = "dpid")
	private String dpid;// dsp广告位id,dsp_position主键
	
	@Column(name = "state")
	private int state;//状态
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZpid() {
		return zpid;
	}

	public void setZpid(String zpid) {
		this.zpid = zpid;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}
}
