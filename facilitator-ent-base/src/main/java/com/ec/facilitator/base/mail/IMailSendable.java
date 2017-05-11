package com.ec.facilitator.base.mail;

import java.util.List;
import java.util.Map;

/**
 * 邮件发送接口 
 * @author 张荣英
 * @date 2017年5月11日 下午4:22:36
 */
public interface IMailSendable {

	/**
	 * 
	* @Title: sendMailSync 
	* @Description:  发送邮件接口-同步
	* @param @param toMail
	* @param @param subject
	* @param @param content
	* @param @param format
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean sendMailSync(String toMail,String subject,String content,ContentFormat format);
	
	/**
	 * 
	* @Title: sendMailAsync 
	* @Description: 发送邮件接口-异步 
	* @param @param toMail
	* @param @param subject
	* @param @param content
	* @param @param format
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public void sendMailAsync(String toMail,String subject,String content,ContentFormat format);
	/**
	 * 
	* @Title: sendMailSync
	* @Description: 发送邮件接口-同步
	* @param @param toMails   接受方邮件LIST
	* @param @param subject   主题
	* @param @param content   内容
	* @param @param format    格式化内容HTML？PLAIN
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean sendMailSync(List<String> toMails,String subject,String content,ContentFormat format);
	/**
	 * 
	* @Title: sendMailAsync
	* @Description: 发送邮件接口-异步
	* @param @param toMails 接受方邮件LIST
	* @param @param subject 主题
	* @param @param content 内容
	* @param @param format 格式化内容HTML？PLAIN
	* @param @return    设定文件 
	* @return     返回类型 
	* @throws
	 */
	public void sendMailAsync(List<String> toMails,String subject,String content,ContentFormat format);
	/**
	 * 
	* @Title: sendMailSync 
	* @Description: 发送邮件接口-velocity模板-同步 
	* @param @param toMails 接受方邮件LIST
	* @param @param subject 主题
	* @param @param model 模板所需的hashMap
	* @param @param VelocityTemplate 模板名字
	* @param @param format 格式化内容HTML？PLAIN
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean sendMailSync(List<String> toMails,String subject,Map<String, Object> model,String VelocityTemplate,ContentFormat format);
	/**
	 * 
	* @Title: sendMailSync 
	* @Description: 发送邮件接口-velocity模板-异步
	* @param @param toMails 接受方邮件LIST
	* @param @param subject 主题
	* @param @param model 模板所需的hashMap
	* @param @param VelocityTemplate 模板名字
	* @param @param format 格式化内容HTML？PLAIN
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public void sendMailAsync(List<String> toMails,String subject,Map<String, Object> model,String VelocityTemplate,ContentFormat format);
	
	/**
	 * 
	* @Title: sendMailSync
	* @Description: 正文带图片
	* @param toMail
	* @param subject
	* @param content
	* @param format
	* @param imgUrls
	* @return
	* boolean
	* @author 韩兵
	* @date 2015年8月26日 下午4:56:07
	* @throws
	 */
	public boolean sendMailAsync(String toMail,String subject,String content,ContentFormat format,List<String> imgUrls);
	
	/**
	 * 
	* @Title: sendMailSync
	* @Description: 正文带图片
	* @param toMails
	* @param subject
	* @param content
	* @param format
	* @param imgUrls
	* @return
	* boolean
	* @author 韩兵
	* @date 2015年8月26日 下午4:56:07
	* @throws
	 */
	public boolean sendMailAsync(List<String> toMails,String subject,String content,ContentFormat format,List<String> imgUrls);
}
