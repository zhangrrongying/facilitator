package com.ec.facilitator.base.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ec.facilitator.base.dal.common.MailAndSmsLogDao;
import com.ec.facilitator.base.model.common.MailAndSmsLogModel;
import com.ec.facilitator.base.util.SLogger;

/**
 * 默认邮件发送实现 
 * @author 张荣英
 * @date 2017年5月11日 下午4:08:53
 */
@Component
public class DefaultMailSendImpl implements IMailSendable{

	//mail配置文件
	@Autowired
	private MailConfig mailConfig;
	//模板引擎
	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactoryBean;
	@Autowired
	private MailAndSmsLogDao mailAndSmsLogDao;
	
	@Override
	public boolean sendMailSync(String toMail, String subject, String content,ContentFormat format) {
		//mail-null直接返回
		if(null == toMail || "".endsWith(toMail)){
			return false;
		}
		List<String> toMails = new ArrayList<String>();
		toMails.add(toMail);
		return sendMailSync(toMails, subject, content,format);
	}

	@Override
	public void sendMailAsync(String toMail, String subject, String content,ContentFormat format) {
		if(null != toMail && !"".endsWith(toMail)){
			List<String> toMails = new ArrayList<String>();
			toMails.add(toMail);
			sendMailAsync(toMails, subject, content,format);
		}
	}
	
	@Override
	public boolean sendMailSync(List<String> toMails, String subject, String content,ContentFormat format) {
		//mail-null直接返回
		if(null == toMails || toMails.size() == 0){
			return false;
		}
		//日志实体的生成不能影响mail发送的过程
		MailAndSmsLogModel log = null;
		try{
			log = setModelAttribute(toMails, subject, content);
		}catch(Exception e){
			SLogger.error(e);
		}
		try {
			//判断是否开启发送
			if (mailConfig.getSendable()) {
				//组装mail内容
				Message mailMessage = setMsgFormat(toMails, subject, content,format,null);
				//发送邮件
				Transport.send(mailMessage);
				//控制日志生成，不能影响mail发送结果
				try{
					log.setIsSuccess(1);
//					logDao.insertLog(log);
					mailAndSmsLogDao.insertLog(log);
				}catch(Exception e){
					SLogger.error(e);
				}
			}
		} catch (MessagingException e) {
			SLogger.error(e);
			log.setIsSuccess(0);
//			logDao.insertLog(log);
			mailAndSmsLogDao.insertLog(log);
			return false;
		}
		return true;
	}
	
	@Override
	public void sendMailAsync(List<String> toMails, String subject, String content, ContentFormat format){
		if(null != toMails && toMails.size() != 0){
			try {
				if (mailConfig.getSendable()) {
					Message mailMessage = setMsgFormat(toMails, subject, content,format,null);
					Executors.newCachedThreadPool().execute(new MailSendTask(mailMessage,contactMailStr(toMails),subject,content,mailConfig.getResendTimes(),mailConfig.getResendInterval(),mailAndSmsLogDao));
				}
			} catch (MessagingException e) {
				SLogger.error(e);
			}
		}
	}
	
	@Override
	public boolean sendMailSync(List<String> toMails, String subject, Map<String, Object> model, String VelocityTemplate, ContentFormat format) {
		String contentHtml = "";
		try {
			contentHtml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngineFactoryBean.createVelocityEngine(), VelocityTemplate,"UTF-8", model);
		} catch (VelocityException e) {
			SLogger.error(e);
			return false;
		} catch (IOException e) {
			SLogger.error(e);
			return false;
		}
		return sendMailSync(toMails, subject, contentHtml, format);
	}

	@Override
	public void sendMailAsync(List<String> toMails, String subject,Map<String, Object> model, String VelocityTemplate,ContentFormat format) {
		String contentHtml = "";
		try {
			contentHtml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngineFactoryBean.createVelocityEngine(), VelocityTemplate,"UTF-8", model);
		} catch (VelocityException e) {
			SLogger.error(e);
		} catch (IOException e) {
			SLogger.error(e);
		}
		sendMailAsync(toMails, subject, contentHtml, format);
	}
	
	
	/**
	 * 
	* @Title: setMsgFormat 
	* @Description: 设置消息主体的格式，HTML？PLAIN
	* @param @param mailMessage
	* @param @param format
	* @param @param content
	* @param @param imgUrls
	* @param @return
	* @param @throws MessagingException    设定文件 
	* @return Message    返回类型 
	* @throws
	 */
	public Message setMsgFormat(List<String> toMails, String subject, String content,ContentFormat format, List<String> imgUrls) throws MessagingException{
		// 判断是否需要身份认证
		MailAuthenticator authenticator = null;
		Properties pro = mailConfig.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailConfig.isValidate()) {
			authenticator = new MailAuthenticator(mailConfig.getUserName(),mailConfig.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailConfig.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		@SuppressWarnings("static-access")
		InternetAddress[] iaToList = new InternetAddress().parse(contactMailStr(toMails));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipients(Message.RecipientType.TO, iaToList);
		// 设置邮件消息的主题
		mailMessage.setSubject(subject);
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		switch (format.value()) {	
			case 0 :
				{
					// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
					MimeMultipart mainPart = new MimeMultipart("related");
					// 创建一个包含HTML内容的MimeBodyPart
					BodyPart html = new MimeBodyPart();
					// 设置HTML内容
					if(null != imgUrls && imgUrls.size() > 0){
						for(String imgUrl : imgUrls){
							content = content+"<br/><img src=\""+imgUrl+"\">";
						}
					}
					html.setContent(content,"text/html; charset=utf-8");
					mainPart.addBodyPart(html);
					
					// 将MiniMultipart对象设置为邮件内容
					mailMessage.setContent(mainPart);
				} break;
			case 1 :
			{
			    mailMessage.setText(content);
			} break;
			default :
				{
				    mailMessage.setText(content);
				} 
		}
		return mailMessage;
	}
	
	
	/**
	 * 
	* @Title: contactMailStr 
	* @Description: 拼接mailList为String字符串，以逗号分割
	* @param @param mailList
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	private String contactMailStr(List<String> mailList){
		StringBuffer mainStr = new StringBuffer();
		for(String mail : mailList){
			mainStr.append(mail).append(",");
		}
		return mainStr.toString().substring(0, mainStr.toString().length()-1);
	}
	/**
	 * 
	* @Title: setModelAttribute 
	* @Description: 组装log实体属性 
	* @param @param toMails
	* @param @param subject
	* @param @param content
	* @param @return    设定文件 
	* @return MailAndSmsLogModel    返回类型 
	* @throws
	 */
	private MailAndSmsLogModel setModelAttribute(List<String> toMails, String subject, String content){
		MailAndSmsLogModel log = new MailAndSmsLogModel();
		String newLogID = UUID.randomUUID().toString();
		log.setLogId(newLogID);
		log.setPhoneOrMail(contactMailStr(toMails));
		log.setSubject(subject);
		log.setContent(content);
		log.setSendDate(new Date());
		log.setType(0);
		return log;
	}

	/**
	 * 
	* @Title: sendMailSync 
	* @Description: 正文带图片的email
	* @param @param toMails
	* @param @param subject
	* @param @param content
	* @throws
	 */
	@Override
	public boolean sendMailAsync(String toMail, String subject, String content, ContentFormat format, List<String> imgUrls) {
		List<String> toMails = new ArrayList<String>();
		toMails.add(toMail);
		try {
			if (mailConfig.getSendable()) {
				Message mailMessage = setMsgFormat(toMails, subject, content,format,imgUrls);
				Executors.newCachedThreadPool().execute(new MailSendTask(mailMessage,contactMailStr(toMails),subject,content,mailConfig.getResendTimes(),mailConfig.getResendInterval(),mailAndSmsLogDao));
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	* @Title: sendMailSync 
	* @Description: 正文带图片的email
	* @param @param toMails
	* @param @param subject
	* @param @param content
	* @throws
	 */
	@Override
	public boolean sendMailAsync(List<String> toMails, String subject, String content, ContentFormat format, List<String> imgUrls) {
		try {
			if (mailConfig.getSendable()) {
				Message mailMessage = setMsgFormat(toMails, subject, content,format,imgUrls);
				Executors.newCachedThreadPool().execute(new MailSendTask(mailMessage,contactMailStr(toMails),subject,content,mailConfig.getResendTimes(),mailConfig.getResendInterval(),mailAndSmsLogDao));
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
