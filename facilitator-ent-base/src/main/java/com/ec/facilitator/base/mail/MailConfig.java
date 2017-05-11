package com.ec.facilitator.base.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件参数配置 
 * @author 张荣英
 * @date 2017年5月11日 下午4:15:50
 */
@Configuration
public class MailConfig {

	@Value("${mail.mailServerHost:smtp.qq.com}")
	private String mailServerHost;
	
	@Value("${mail.mailServerPort:25}")
	private String mailServerPort;
	
	@Value("${mail.fromAddress:service@sunyuki.com}")
	private String fromAddress;
	
	@Value("${mail.userName:service@sunyuki.com}")
	private String userName;
	
	@Value("${mail.password:Sunyuki@20140416}")
	private String password;
	// 是否需要身份验证
	private boolean validate = true;
	
	@Value("${mail.sendable:true}")
	private Boolean sendable;
	
	@Value("${mail.resendTimes:3}")
	private int resendTimes;
	
	@Value("${mail.resendInterval:15000}")
	private int resendInterval;
	
	@Value("${mail.template:file:./conf/mailtemplate}")
	private String mailTemplate;

	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public Boolean getSendable() {
		return sendable;
	}

	public void setSendable(Boolean sendable) {
		this.sendable = sendable;
	}

	public int getResendTimes() {
		return resendTimes;
	}

	public void setResendTimes(int resendTimes) {
		this.resendTimes = resendTimes;
	}

	public int getResendInterval() {
		return resendInterval;
	}

	public void setResendInterval(int resendInterval) {
		this.resendInterval = resendInterval;
	}
	
	public String getMailTemplate() {
		return mailTemplate;
	}

	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}
}
