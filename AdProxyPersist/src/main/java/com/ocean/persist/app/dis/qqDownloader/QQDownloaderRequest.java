package com.ocean.persist.app.dis.qqDownloader;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class QQDownloaderRequest implements AdDisParams{
	/**
	 * 
	 */
	private String businessKey;
	private String businessId;
	private String interName;
	private static final long serialVersionUID = -6734925431958463594L;
	private AdDisParams head;
	public final static String BUSINESSKEY="businessKey";
	public final static String CONTEXTDATA="contextData";
	public final static String BUSINESSID="businessId";
	public AdDisParams getHead() {
		return head;
	}
	public void setHead(AdDisParams head) {
		this.head = head;
	}
	public AdDisParams getBody() {
		return body;
	}
	public void setBody(AdDisParams body) {
		this.body = body;
	}
	private AdDisParams body;
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getInterName() {
		return interName;
	}
	public void setInterName(String interName) {
		this.interName = interName;
	}
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
