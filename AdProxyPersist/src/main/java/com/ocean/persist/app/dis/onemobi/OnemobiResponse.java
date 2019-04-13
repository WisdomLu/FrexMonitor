package com.ocean.persist.app.dis.onemobi;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月8日 
      @version 1.0 
 */
public class OnemobiResponse  implements AppDisResponse{
	private int code;//	是	Int	0表示成功，其它值表示失败
	private String message;//	否	String	说明
	private String nextcur;//	否	String	分页上下文，查询下一页列表时必须带上
	private List<OnemobiAPP> lists;//	成功是	Array	应用列表
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNextcur() {
		return nextcur;
	}
	public void setNextcur(String nextcur) {
		this.nextcur = nextcur;
	}
	public List<OnemobiAPP> getLists() {
		return lists;
	}
	public void setLists(List<OnemobiAPP> lists) {
		this.lists = lists;
	}



}
