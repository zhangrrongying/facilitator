package com.ec.facilitator.base.model.common;

import java.util.Date;


public class MailAndSmsLogModel {
	private String logId;

	private String phoneOrMail;

	private String subject;

	private String content;

	private int isSuccess;

	private Date sendDate;

	private int type;
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getPhoneOrMail() {
		return phoneOrMail;
	}
	public void setPhoneOrMail(String phoneOrMail) {
		this.phoneOrMail = phoneOrMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
