package com.ec.facilitator.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtil {

	public static final String DATE_TIME_MILLISECONDS = "yyyy/MM/dd HH:mm:ss:SSS";

	public static final String JACKSON_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

	/** 格式化模式1 => yyyy-MM-dd HH:mm:ss */
	public static final String DATE_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

	/** 格式化模式2 => yyyy-MM-dd */
	public static final String DATE_PATTERN_2 = "yyyy-MM-dd";

	/** 格式化模式3 => yyyy年MM月dd日 */
	public static final String DATE_PATTERN_3 = "yyyy年MM月dd日";

	/** 格式化模式4 => MM月dd日 */
	public static final String DATE_PATTERN_4 = "MM月dd日";

	/** 格式化模式5 => HH小时mm分钟 */
	public static final String DATE_PATTERN_5 = "HH小时mm分钟";
	
	/**一个未来的配送时间**/
	public static final String MAX = "3000-12-31";

	/**
	 * 将时间字符串解析为date.
	 * 
	 * @param dateStr
	 *            源时间字符串.
	 * @param pattern
	 *            格式化模式,NoahUtil类中提供了5种模式: DATE_PATTERN_1 => yyyy-MM-dd HH:mm:ss
	 *            DATE_PATTERN_2 => yyyy-MM-dd DATE_PATTERN_3 => yyyy年MM月dd日
	 *            DATE_PATTERN_4 => MM月dd日 DATE_PATTERN_5 => HH小时mm分钟.
	 * @return 解析后的date.
	 */
	public static Date parseStrToDate(String dateStr, String pattern) {
		Date date = null;
		try {
			date = new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将时间类型转换成指定格式的时间字符串
	 * 
	 * @param date
	 * @param pattern
	 */
	public static String parseDateToStr(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	
	/**
	 * 根据日期返回周几  周一返回1 周日返回0
	 * @param date
	 * @return
	 */
	public static int getWeekOfDate(Date date){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		int weekNum = cal.get(Calendar.DAY_OF_WEEK);
		return weekNum-1;
	}
	
	
}
