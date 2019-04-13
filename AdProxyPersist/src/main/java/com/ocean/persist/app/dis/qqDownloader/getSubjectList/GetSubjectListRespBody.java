package com.ocean.persist.app.dis.qqDownloader.getSubjectList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月18日 
      @version 1.0 
 */
public class GetSubjectListRespBody     implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7652671816416311800L;
	private int ret;
	private int hasNext;
	private List<String> pageContext;
	private List<SubjectItem> subjectList;
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
	public List<SubjectItem> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SubjectItem> subjectList) {
		this.subjectList = subjectList;
	}
}
