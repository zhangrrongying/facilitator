package com.ec.facilitator.base.mail;

import java.util.Date;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Transport;

import com.ec.facilitator.base.dal.common.MailAndSmsLogDao;
import com.ec.facilitator.base.model.common.MailAndSmsLogModel;
import com.ec.facilitator.base.util.SLogger;

/**
 * 异步发送mail
 * @author 张荣英
 * @date 2017年5月11日 下午4:14:58
 */
public class MailSendTask implements Runnable {

	private MailAndSmsLogDao mailAndSmsLogDao;
	private Message mailMessage;
	private String toMails;
	private String subject;
	private String content;
	private int resendTimes;
	private int resendInterval;

	public MailSendTask(Message mailMessage, String toMails, String subject,String content,
			int resendTimes,int resendInterval, MailAndSmsLogDao mailAndSmsLogDao) {
		this.mailMessage = mailMessage;
		this.toMails = toMails;
		this.subject = subject;
		this.content = content;
		this.resendTimes = resendTimes;
		this.resendInterval = resendInterval;
		this.mailAndSmsLogDao = mailAndSmsLogDao;
	}

	@Override
	public void run() {
		String id = null;
		try{
			//默认发送消息失败
			id = persistentModel(0);
		}catch(Exception e){
			SLogger.error(e);
		}
		Exception e = null;
		while(resendTimes>0){
			try {
				Transport.send(mailMessage);
				try{
					updateModel(1,id);
				}catch(Exception ue){
					SLogger.error(ue);
				}
				break;
			} catch (Exception ee) {
				SLogger.error(ee);
				e=ee;
				resendTimes--;
			}
			try {
				Thread.sleep(resendInterval);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
				SLogger.error(ie);
			}
		}
		if(resendTimes<1){
			SLogger.error(String.format("发送邮件:\"%s\",主题：\"%s\"，已经重试3次，发送失败，请尽快检查原因，详细异常如下：",toMails,subject), e, true);
		}
	}

	public String persistentModel(int isSuccess) {
		String newLogID = UUID.randomUUID().toString();
		MailAndSmsLogModel log = new MailAndSmsLogModel();
		log.setLogId(newLogID);
		log.setPhoneOrMail(toMails);
		log.setSubject(subject);
		log.setContent(content);
		log.setSendDate(new Date());
		log.setType(0);
		log.setIsSuccess(isSuccess);
		return mailAndSmsLogDao.insertLog(log);
	}
	
	public void updateModel(int isSuccess,String id) {
		MailAndSmsLogModel log = mailAndSmsLogDao.getLog(id);
		log.setIsSuccess(isSuccess);
		mailAndSmsLogDao.updateLog(log);
	}
}
