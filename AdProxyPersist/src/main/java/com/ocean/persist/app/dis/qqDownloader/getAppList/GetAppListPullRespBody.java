package com.ocean.persist.app.dis.qqDownloader.getAppList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.BaseAppADListItem;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class GetAppListPullRespBody  implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3820691984738457026L;
    private int ret;
    private List<String> contextData;
    private int hasNext;
    private List<String>  pkgNameList;
    private List<BaseAppADListItem> appADList;
    private List<SimpleADAppInfo> appInfoList;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
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
	public List<String> getPkgNameList() {
		return pkgNameList;
	}
	public void setPkgNameList(List<String> pkgNameList) {
		this.pkgNameList = pkgNameList;
	}
	public List<BaseAppADListItem> getAppADList() {
		return appADList;
	}
	public void setAppADList(List<BaseAppADListItem> appADList) {
		this.appADList = appADList;
	}
	public List<SimpleADAppInfo> getAppInfoList() {
		return appInfoList;
	}
	public void setAppInfoList(List<SimpleADAppInfo> appInfoList) {
		this.appInfoList = appInfoList;
	}
}
