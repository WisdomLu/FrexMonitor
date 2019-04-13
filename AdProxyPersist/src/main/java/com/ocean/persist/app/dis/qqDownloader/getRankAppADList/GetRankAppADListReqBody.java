package com.ocean.persist.app.dis.qqDownloader.getRankAppADList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class GetRankAppADListReqBody       implements AdDisParams{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8964895353132686965L;
	private long categoryId;
	private int page;
	private List<String> pageContext;
	private int pageSize;
	private long sceneId;
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<String> getPageContext() {
		return pageContext;
	}
	public void setPageContext(List<String> pageContext) {
		this.pageContext = pageContext;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getSceneId() {
		return sceneId;
	}
	public void setSceneId(long sceneId) {
		this.sceneId = sceneId;
	}
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
