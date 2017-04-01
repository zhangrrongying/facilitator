package com.ec.facilitator.base.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 统一日志记录地方
 * @author ryan
 *
 */
public class SLogger {
	
	static Logger s_logger = Logger.getLogger(Logger.class);
	static Logger s_mailLogger = Logger.getLogger("mailLogger");
	static Logger s_noMailLogger = Logger.getLogger("noMailLogger");
	
	public static void log(Level level, Object message, Throwable t){
		s_logger.log(level, message, t);
	}
	
	public static void error(Throwable t){
		error("System Trace Error", t, false);
	}
	
	public static void error(Throwable t, boolean sendMail){
		error("System Trace Error", t, sendMail);
	}
	
	public static void error(String title, Throwable t, boolean sendMail){
		if (sendMail){
			s_mailLogger.error(title, t);
		}
		else{
			s_noMailLogger.error(title, t);
		}
	}
	
	/**
	 * 根据sendMail参数做不同的日志处理
	 * @param message 日志消息
	 * @param sendMail 是否发送邮件
	 * @return void
	 * @author liuchun 
	 * @date 2015年8月5日 上午10:28:47
	 */
	public static void trace(String message, boolean sendMail) {
		if (sendMail) {
			s_mailLogger.trace(message);
		} else {
			s_noMailLogger.trace(message);
		}
	}
}
