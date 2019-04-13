package com.ocean.forex.entity.email;
public class Mail{
	
	private static final long serialVersionUID = 1L;

	/**邮件内容 */
	private String mailBody;
	
	/**邮件标题 */
	private String mailTitle;
	
	/**收件人邮箱    多个收件人时以,隔开*/
	private String toMail;

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
}
