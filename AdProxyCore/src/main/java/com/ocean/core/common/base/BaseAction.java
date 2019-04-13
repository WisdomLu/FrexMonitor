package com.ocean.core.common.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;

public class BaseAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int page;
	private int rows;	
	/** struts2从2.1升级到2.3后会报这两个参数不存在的警告，但不影响使用。为了不显示恶心的警告信息，故加上这两个参数 */
	private String _;
	private String post;

	protected void saveMessage(String msg) {
		getRequest().setAttribute("msg", msg);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpSession getSession() {
		return getSession(true);
	}

	public HttpSession getSession(boolean autoCreate) {
		HttpSession session = getRequest().getSession(autoCreate);
		if (!autoCreate && session == null) {
			throw new BusinessException(ErrorCode.INTER_ERROR,"对不起，您未登陆或已超时");
		}
		return session;
	}

	protected void disposeSession() {
		// 在监听器中统一销毁
		try {
			getSession(false).invalidate();
		} catch (Exception e) {
			// 注销，不接受异常
		}
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 运用j2ee api将数据发给客户端 2011-1-13
	 * 
	 * @param content
	 * @throws IOException
	 */
	protected void sendClient(String content) throws IOException {
		PrintWriter out = getResponseWriter();
		out.print(content);
		out.flush();
		out.close();
	}

	/**
	 * 运用j2ee api将数据发给客户端 2011-1-13
	 * 
	 * @param content
	 * @throws IOException
	 */
	protected void writeToClient(String content) {
		try {
			sendClient(content);
		} catch (IOException e) {
			// throw new ServiceException(e.getMessage(),e);
		}
	}

	private PrintWriter getResponseWriter() throws IOException {
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		return response.getWriter();
	}

	/**
	 * 向相应中写入字符串信息 2011-1-13
	 * 
	 * @author yaoyuan
	 * @param msg
	 */
	protected void print(String msg) {
		try {
			getResponseWriter().print(msg);
		} catch (IOException e) {
		}
	}

	protected void closeWriter() {
		try {
			getResponseWriter().flush();
			getResponseWriter().close();
		} catch (IOException e) {
		}
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	public String get_() {
		return _;
	}

	public void set_(String _) {
		this._ = _;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	@Override
	public void addActionError(String anErrorMessage){
		   String s=anErrorMessage;
		   System.out.println(s);
		  }
	@Override
  public void addActionMessage(String aMessage){
   String s=aMessage;
   System.out.println(s);
  
  }
	@Override
  public void addFieldError(String fieldName, String errorMessage){
   String s=errorMessage;
   String f=fieldName;
   System.out.println(s);
   System.out.println(f);
  
  }
}
