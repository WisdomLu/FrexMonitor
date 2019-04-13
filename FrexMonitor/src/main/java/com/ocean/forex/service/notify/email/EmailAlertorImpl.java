package com.ocean.forex.service.notify.email;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.forex.common.MonitorConstants;
import com.ocean.forex.common.SendMailUtil;
import com.ocean.forex.entity.event.base.AbstractEvent;
import com.ocean.forex.service.notify.IAlert;
@Component(value = "EmailAlertor")
public class EmailAlertorImpl implements IAlert{
	public  final Logger logger = MyLogManager.getLogger();
	private static final String MAIL_TEMP="monitor_mail_temp.txt";
	private static final String MACRO_MAIL_CONTENT="\\{__EVENT_INFO__\\}";
	@Override
	public void notify(AbstractEvent event) {
		// TODO Auto-generated method stub
		String mailToArr = SystemContext.getDynamicPropertyHandler().get(MonitorConstants.EMAIL_TO); //系统邮箱账号
		//发邮件策略
		
		for(String mailTo:mailToArr.split(";")){
			String content =SendMailUtil.getMailTemplate(MAIL_TEMP);
			content=content.replaceAll(MACRO_MAIL_CONTENT, event.getDesc());
			SendMailUtil.assembleMailAndSend(event.getName(), content, mailTo);
		}
		
	}

}
