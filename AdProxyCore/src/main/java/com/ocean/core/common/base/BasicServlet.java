package com.ocean.core.common.base;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 */
public class BasicServlet extends HttpServlet {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_CHARSET = "UTF-8";

	protected void outputJson(Object json, HttpServletResponse response)
			throws IOException {
		String text = JSONObject.toJSONString(json);
		outputText(text, response);
	}

	public void outputText(String text,HttpServletResponse response)
			throws IOException {
		response.getWriter().append(text);
		response.getWriter().flush();

	}

	public String getHttpBodyContentAsString(HttpServletRequest request)
			throws IOException {
		byte[] buf = getHttpBodyContentAsBytes(request);
		return new String(buf, 0, buf.length, DEFAULT_CHARSET);
	}

	public byte[] getHttpBodyContentAsBytes(HttpServletRequest request)
			throws IOException {
		int contentLen = request.getContentLength();
		if (contentLen <= 0) {
			throw new RuntimeException("ContentLength invalid : " + contentLen);
		}
		byte[] buf = new byte[contentLen];
		IOUtils.readFully(request.getInputStream(), buf);
		return buf;
	}

}
