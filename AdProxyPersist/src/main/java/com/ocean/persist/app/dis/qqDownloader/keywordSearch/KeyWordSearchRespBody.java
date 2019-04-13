package com.ocean.persist.app.dis.qqDownloader.keywordSearch;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.BaseAppADListItem;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class KeyWordSearchRespBody  implements AppDisResponse{
	private static final long serialVersionUID = 5796501776232461138L;
    private int ret;
    private List<BaseAppADListItem> appList;
    private List<String> contextData;
    private int hasNext;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public List<BaseAppADListItem> getAppList() {
		return appList;
	}
	public void setAppList(List<BaseAppADListItem> appList) {
		this.appList = appList;
	}
	public List<String> getContextData() {
		return contextData;
	}
	public void setContextData(List<String> contextData) {
		this.contextData = contextData;
	}
	public int getHasNext() {
		return hasNext;
	}
	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}
}
