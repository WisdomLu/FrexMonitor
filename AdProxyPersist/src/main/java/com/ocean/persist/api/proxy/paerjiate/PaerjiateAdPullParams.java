package com.ocean.persist.api.proxy.paerjiate;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class PaerjiateAdPullParams  extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3895561921117597056L;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String token;//	必填	string	验证密钥。由商务或运营提供
	private String sid	;//	必填	string	子渠道号，需要与token配套使用。
	private String gaid	;//	必填	string	GAID，设备号
	private String a_id	;//	必填	string	安卓ID
	private int maxpush	;//	选填	number	最大下发记录数，默认15条
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getGaid() {
		return gaid;
	}
	public void setGaid(String gaid) {
		this.gaid = gaid;
	}
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public int getMaxpush() {
		return maxpush;
	}
	public void setMaxpush(int maxpush) {
		this.maxpush = maxpush;
	}

}
