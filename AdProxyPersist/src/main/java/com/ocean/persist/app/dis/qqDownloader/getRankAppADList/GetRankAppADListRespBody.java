package com.ocean.persist.app.dis.qqDownloader.getRankAppADList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.BaseAppADListItem;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class GetRankAppADListRespBody   implements AppDisResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3754658375191589934L;
	private int ret;
	private int hasNext;
	private List<String> pageContext;
	private List<BaseAppADListItem> appList;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public int getHasNext() {
		return hasNext;
	}
	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}
	public List<String> getPageContext() {
		return pageContext;
	}
	public void setPageContext(List<String> pageContext) {
		this.pageContext = pageContext;
	}
	public List<BaseAppADListItem> getAppList() {
		return appList;
	}
	public void setAppList(List<BaseAppADListItem> appList) {
		this.appList = appList;
	}
}
