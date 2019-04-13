package com.ocean.persist.app.dis.qqDownloader.getAppList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class GetAppListPullReqBody    implements AdDisParams{

	private static final long serialVersionUID = -2638214633043349604L;
	/*
	 * 0x05 广告列表，只返回包名 0x06 聚合标签对应的商业化应用列表（聚合标签调getCategoryDetailList获得）
       0x06 聚合标签对应的商业化应用列表（聚合标签调getCategoryDetailList获得）
       0x07应用标签对应的商业化应用列表（应用标签调getApplicationTag获得） 
       0x08 宿主+标识对应的商业化应用列表 0x09专题Id下的商业化应用列表 
       0x09专题Id下的商业化应用列表
       0x0A 一级分类对应的商业化应用列表（getCategoryDetailList获取一级分类回包中的categoryId字段）
	 * */
    private int listType;
    private int pageSize;
    private List<String> contextData;
    private long tagId;
    private int categoryId;
    private String identification;
    private int columnId;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public int getListType() {
		return listType;
	}
	public void setListType(int listType) {
		this.listType = listType;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public int getColumnId() {
		return columnId;
	}
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}
	public List<String> getContextData() {
		return contextData;
	}
	public void setContextData(List<String> contextData) {
		this.contextData = contextData;
	}

}
