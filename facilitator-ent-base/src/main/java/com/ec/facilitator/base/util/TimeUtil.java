package com.ec.facilitator.base.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String SHORT_PATTERN = "yyyy-MM-dd";

	/**
	 * 时间转化为数据库类型
	 * @param date
	 * @return
	 * @return Timestamp
	 * @author zx
	 * @date 2015年6月19日 下午3:35:15
	 */
	public static Timestamp formatTime(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 计算时间
	 * @param date 当前日期时间
	 * @param time 时间长度(单位：小时)
	 * @return String
	 * @author liuchun 
	 * @date 2015年12月2日 下午6:42:00
	 */
	public static String addTime(Date date, BigDecimal time) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		long dateTime = date.getTime();
		double timeLenth = time.doubleValue();
		long result = (long) (dateTime + timeLenth * 60 * 60 * 1000);
		Date tempDate = new Date(result);
		String dateStr = sdf.format(tempDate);
		return dateStr;
	}
	
	public static String addTime(Date date, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		Calendar cal = Calendar.getInstance();  
		cal.add(Calendar.DATE, days);  
        Date time = cal.getTime();
        String preTime = sdf.format(time);
        return preTime;
	}
	
	public static String Date2String(Date date, boolean flag) {
		SimpleDateFormat sdf = null;
		if (flag) {
			sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		} else {
			sdf = new SimpleDateFormat(SHORT_PATTERN);
		}
        String dateStr = sdf.format(date);
        return dateStr;
	}
	
	public static String addMonth(Date date, int months) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, months);
		String preTime = sdf.format(cal.getTime());
		return preTime;
	}
	
	public static Date String2Date(String dateStr, boolean flag) {
		SimpleDateFormat sdf = null;
		Date date = null;
		if (flag) {
			sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		} else {
			sdf = new SimpleDateFormat(SHORT_PATTERN);
		}
		
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();    
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static String getFirstDayOfBeforeMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date date = calendar.getTime();
		String dateStr = Date2String(date, false);
		return dateStr;
	}
	
	
}
