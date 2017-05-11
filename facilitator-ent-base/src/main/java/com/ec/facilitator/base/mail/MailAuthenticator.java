package com.ec.facilitator.base.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件验证者 
 * @author 张荣英
 * @date 2017年5月11日 下午4:22:20
 */
public class MailAuthenticator extends Authenticator {

	String userName = null;
	String password = null;

	public MailAuthenticator() {
		
	}

	public MailAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
