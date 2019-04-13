package com.ocean.persist.api.proxy.yuansheng;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengSession  extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3806042068545605574L;
	private String id;//	string	Y	用户会话id，推荐使用uuid算法	
	private List<String> context;//	array of string	N	上下文内容列表	作用：为了媒体可以通过context对象来设置广告展现的上下文内容以此来优化广告投放策略
	private int isunique	;//integer	Y	广告是否唯一	1：广告唯一，0：广告非唯一
	private int seq;//	integer	N	当前请求在session中的顺序位置	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getContext() {
		return context;
	}
	public void setContext(List<String> context) {
		this.context = context;
	}
	public int getIsunique() {
		return isunique;
	}
	public void setIsunique(int isunique) {
		this.isunique = isunique;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

}
