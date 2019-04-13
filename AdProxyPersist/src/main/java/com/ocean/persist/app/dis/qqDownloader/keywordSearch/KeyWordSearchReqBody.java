package com.ocean.persist.app.dis.qqDownloader.keywordSearch;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class KeyWordSearchReqBody      implements AdDisParams{
	private static final long serialVersionUID = -8550100217230004199L;
	private String keyword;
	private List<String> contextData;
	private int pageSize;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<String> getContextData() {
		return contextData;
	}
	public void setContextData(List<String> contextData) {
		this.contextData = contextData;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
