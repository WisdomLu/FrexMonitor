package com.ocean.forex.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.inveno.util.PropertyUtils;
import com.inveno.util.StringUtil;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.forex.entity.email.Mail;
import com.ocean.forex.service.notify.email.EmailAlertorImpl;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 发送邮件工具类
 * @author Xjun
 * @date 2015-03-02
 */
public class SendMailUtil extends ActionSupport {
	public static  final Logger logger = MyLogManager.getLogger();
	private static final long serialVersionUID = 1L;
	
	/**
	 * 配置并发送邮件工具方法（支持批量发送，只需在Mail对象set多个收件人并用逗号分隔即可）

	 */
	public static void sendMail(Mail mail){
		if(null==mail || StringUtil.isEmpty(mail.getToMail())) {
			return;
		}
		final String SysMailUserName = SystemContext.getDynamicPropertyHandler().get(MonitorConstants.EMAIL_USERNAME); //系统邮箱账号
		final String SysMailPassword = SystemContext.getDynamicPropertyHandler().get(MonitorConstants.EMAIL_PASSWORD); //系统邮箱密码
		String mailHost = SystemContext.getDynamicPropertyHandler().get(MonitorConstants.EMAIL_HOST);                               //企业邮箱服务器 smtp.qq.com
		
		//创建session
		Properties props = new Properties();
       
		props.put("mail.smtp.host", mailHost);

		props.put("mail.smtp.auth", "true");
		//创建session
		Session session=Session.getInstance(props,
				new Authenticator() {
              	@Override
				protected PasswordAuthentication getPasswordAuthentication() {
              	    
              		return new PasswordAuthentication(SysMailUserName,SysMailPassword); //添加发件人用户名与密码
              	}
          	}
		);
		
		//创建Message
		Message msg=new MimeMessage(session);
	    try{
	    	String nick=""; 
	        try { 
	            nick=javax.mail.internet.MimeUtility.encodeText("FrexMoniting");
	        } catch (UnsupportedEncodingException e) { 
	            e.printStackTrace(); 
	        }  

	    	msg.setFrom(new InternetAddress(SysMailUserName,nick));                       //设置发信箱
	        msg.setSubject(mail.getMailTitle());                                                //设置邮件标题
	        msg.setContent(mail.getMailBody(), "text/html;charset=utf-8");                      //发送邮件内容 ,与邮件字符编码
	        //设置批量收件人，并批量发送邮件
	        //会显示收件人，但可能会被认为是垃圾邮件
	        msg.setRecipients(RecipientType.TO, InternetAddress.parse(mail.getToMail()));
	        Transport.send(msg);
	    }catch(Exception e){
	    	logger.error("Frex monitor send email error {} ,acceptor:{}",e.getMessage(),mail.getToMail(),e);
	    	return;
	    }
	}
	
	/**
	 * 获取邮件模板
	 */
	public static String getMailTemplate(String temFileName) {
		BufferedReader read = null;
		String fileName = EmailAlertorImpl.class.getResource("")+ "template" + File.separator + temFileName;
		if(StringUtils.isNotEmpty(fileName)){
			fileName=fileName.substring(fileName.indexOf("/")+1);
		}
		logger.debug("email template file name:{}",fileName);
		fileName=fileName.replace("%20", " ");
		String s = "";
		StringBuilder sb = new StringBuilder(200);
		try {
			read = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			s = read.readLine();
			while (s != null) {					
				if (StringUtil.isNotEmpty(s)) {
					sb.append(s);
				}
				s = read.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 组装邮件内容对象并发送邮件工具方法
	 */
	public static void assembleMailAndSend(String title,String content,String receiveMail){
		Mail mail = new Mail();
		
		mail.setMailBody(content);
		mail.setMailTitle(title);
		mail.setToMail(receiveMail);
		SendMailUtil.sendMail(mail);
	}

}
