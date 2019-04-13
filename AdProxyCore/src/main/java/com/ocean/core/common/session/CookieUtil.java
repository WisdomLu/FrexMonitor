package com.ocean.core.common.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;

public class CookieUtil
{
  private static Logger log = LoggerFactory .getLogger(CookieUtil.class); 
  public static void setAttribute(HttpSession session, String attrName, Object attrObject)
  {
    session.setAttribute(attrName, attrObject);
  }

  public static Object getAttribute(HttpSession session, String attrName)
  {
    Object object = session.getAttribute(attrName);
    return object;
  }


  public static void addCookie(String cookieName, String cookieValue, int expiry, HttpServletRequest request, HttpServletResponse response)
         throws BusinessException{
		    try{
		        Cookie cookie = new Cookie(cookieName, cookieValue);
		        cookie.setMaxAge(expiry);
		        cookie.setPath(request.getContextPath());
		        response.addCookie(cookie);
		    }catch(Exception e){
		 	   log.error("the method getCookieValue fired runtime exception!",e);
		 	   throw new BusinessException(ErrorCode.INTER_ERROR,e.getMessage());
		
		 	}
  }



  public static String getCookieValue(String cookieName, HttpServletRequest request) throws BusinessException
  {
		try{
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		        if (cookieName.equals(cookies[i].getName())) {
		          return cookies[i].getValue();
		        }
		      }
		    }
		}catch(Exception e){
		   log.error("the method getCookieValue fired runtime exception!",e);
		   throw new BusinessException(ErrorCode.INTER_ERROR,e.getMessage());
	
		}
	    return null;
  }
}