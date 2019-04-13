package com.ocean.persist.app.dis.qqDownloader.getSubjectList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月18日 
      @version 1.0 
 */
public class GetSubjectListReqBody       implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6768452509138360154L;
    private List<String> pageContext;
    private int pageSize;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
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

}
