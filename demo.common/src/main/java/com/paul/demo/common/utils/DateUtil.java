/**
 */
package com.paul.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Paul
 * 
 * 2014年11月15日 上午11:28:57
 */
public class DateUtil {

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy.MM.dd HH:mm:ss
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_POINT = "yyyy.MM.dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd
	 */
	public static final String YYYY_MM_DD_LINE = "yyyy-MM-dd";
	
	/**
	 * yyyy.MM.dd
	 */
	public static final String YYYY_MM_DD_POINT = "yyyy.MM.dd";
	
	/**
	 * yyyy.MM.dd HH:mm
	 */
	public static final String YYYY_MM_DD_HH_MM_POINT = "yyyy.MM.dd HH:mm";
	
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String YYYY_MM_DD_HH_MM_LINE = "yyyy-MM-dd HH:mm";
	/**
	 * HH:mm am
	 */
	public static final String HH_MM_A_Z = "HH:mm a";
	/**
	 * MM-dd HH:mm
	 */
	public static final String MM_DD_HH_MM = "MM-dd HH:mm";
	/**
	 * MM/dd/yyyy
	 */
	public static final String MM_DD_YYYY = "MM/dd/yyyy";
	/**
	 * 20140506
	 */
	public static final String YYYY_MM_DD = "yyyyMMdd";
	/**
	 * 201405
	 */
	public static final String YYYY_MM = "yyyyMM";
	/**
	 * 02.18.2015 12:00:00
	 */
	public static final String MM_DD_YYYY_HH_MM_SS = "MM.dd.yyyy HH:mm:ss";
	/**
	 * 2015年03月13日
	 */
	public static final String YYYY_MM_DD_CH = "yyyy年MM月dd日";
	/**
	 * 2015年03月13日09:30
	 */
	public static final String YYYY_MM_DD_HH_MM_CH = "yyyy年MM月dd日HH:mm";
	/**
	 * 12:00
	 */
	public static final String HH_MM = "HH:mm";
	/**
	 * 2015年03月13日09:30
	 */
	public static final String YYYYMMDDHHMMSSS = "yyyyMMddHHmmssS";
	/**
	 * yyyyMMddHHmm
	 */
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
	
	private static Date mDate;
	public static final long SECONDS_OF_MINUTE = 60;
	public static final long SECONDS_OF_HOUR = 60 * 60;
	public static final long SECONDS_OF_DAY = 60 * 60 * 24;
	public static final long SECONDS_OF_WEEK = 60 * 60 * 24 * 7;
	public static final long SECONDS_OF_MONTH = 60 * 60 * 24 * 31;//暂定义位每月31天
	public static final long SECONDS_OF_YEAR = 60 * 60 * 24 * 365;
	public static final long DAYS_OF_MONTH = 31;
	public static final long DAYS_OF_YEAR = 365;
	public static final long MINUTES_OF_HOUR = 60;
	public static final long MONTHES_OF_YEAR = 12;

	/**
	 * 获取此时时间
	 * 
	 * @param format
	 *            返回的时间格式
	 * @return
	 */
	public static String getNow(String format) {
		mDate = new Date();
		return initFormat(format).format(mDate);
	}
	
	/**
	 * 初始化SimpleDateFormat
	 * @param format
	 */
	private static SimpleDateFormat initFormat(String format){
		SimpleDateFormat mDateFormat = new SimpleDateFormat(format);
		//mDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		return mDateFormat;
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            时间字符串
	 * @param dFomat
	 *            时间格式
	 * @param fomat
	 *            返回的时间格式
	 * @return
	 * @throws ParseException
	 */
	public static String format(String date, String dateFormat, String format)
			throws ParseException {
		mDate = getDate(date, dateFormat);
		return initFormat(format).format(mDate);
	}
	
	/**
	 * 
	 * @param date 时间
	 * @param format 需要的格式化格式
	 * @return
	 */
	public static String format(Date date, String format){
		return initFormat(format).format(date);
	}

	/**
	 * 获取到date的时间秒
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static long getTime(String date, String dateFormat)
			throws ParseException {
		return getDate(date, dateFormat).getTime();
	}
	
	
	/**
	 * 获取到现在的时间秒
	 * 
	 * @return
	 */
	public static long getTime() {
		mDate = new Date();
		return mDate.getTime();
	}

	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear() {
		return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}

	public static int getHourOfDay() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/**
	 * 获取时间与现在的时间差
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public static String getHowLong(String date, String format)
			throws ParseException {
		String longTime = "";
		Calendar calendar = Calendar.getInstance();
		mDate = getDate(date, format);
		Calendar calen = Calendar.getInstance();
		calen.setTime(mDate);
		int year = calen.get(Calendar.YEAR);
		int month = calen.get(Calendar.MONTH);
		int hour = calen.get(Calendar.HOUR_OF_DAY);
		int minute = calen.get(Calendar.MINUTE);
		long days = getDays(calen);
		if(days == 0){
			if(getHourOfDay() - hour == 0){
				if(getMinute() - minute == 0){
					longTime = "刚刚";
				}else {
					longTime = (getMinute() - minute) + "分钟前";
				}
			}else {
				if(getMinute()+(MINUTES_OF_HOUR-minute)+((getHourOfDay() - hour-1)*MINUTES_OF_HOUR) < MINUTES_OF_HOUR){
					longTime = (getMinute()+(MINUTES_OF_HOUR-minute)) + "分钟前";
				}else {
					longTime = (getHourOfDay() - hour) + "小时前";
				}
			}
		}else if(days < DAYS_OF_MONTH){
			longTime = days + "天前";
		}else if(days < DAYS_OF_YEAR && days >= DAYS_OF_MONTH){
			if(getYear() == year){
				longTime = (getMonth() - month) + "个月前";
			}else {
				longTime = (getMonth() + (MONTHES_OF_YEAR - month)) + "个月前";
			}
			
		}else {
			longTime = (getYear() - year) + "年前";
		}
		return longTime;
	}

	/**
	 * 获取据当前的天数
	 * 
	 * @param calendar
	 * @return
	 */
	private static long getDays(Calendar calendar) {
		long days = 0;
		int year = calendar.get(Calendar.YEAR);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		if (year == getYear()) {
			days = getDayOfYear() - dayOfYear;
		} else {
			days = getDayOfYear()
					+ (((getYear() - year - 1) * DAYS_OF_YEAR) + (DAYS_OF_YEAR - dayOfYear));
		}
		return days;
	}

	/**
	 * 获取时间
	 * 时间不正确，返回当前的时间
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String date, String format){
		try {
			return initFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取一个星期的开始时间和结束时间{开始时间， 结束时间}
	 * @param date
	 * @return
	 */
	public static Date[] getWeekDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if(calendar.get(Calendar.DAY_OF_WEEK) == 1){
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-6);
		}else{
			calendar.set(Calendar.DAY_OF_WEEK, 2);
		}
		Date startDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+7);
		calendar.set(Calendar.SECOND, -1);
		Date endDate = calendar.getTime();
		return new Date[]{startDate, endDate};
	}
	
	/**
	 * 获取一天的开始时间和结束时间{开始时间， 结束时间}
	 * @param date
	 * @return
	 */
	public static Date[] getDayDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date startDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		Date endDate = calendar.getTime();
		return new Date[]{startDate, endDate};
	}
	
	/**
	 * 获取以个月的开始时间和结束时间{开始时间， 结束时间}
	 * @param date
	 * @return
	 */
	public static Date[] getMonthDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = calendar.getTime();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		calendar.set(Calendar.SECOND, -1);
		Date endDate = calendar.getTime();
		return new Date[]{startDate, endDate};
	}
	
	/**
	 * 获取一天的开始时间和结束时间{开始时间， 结束时间}
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date[] getDayDate(String date, String format){
		return getDayDate(getDate(date, format));
	}
	/**
	 * 算出剩余天数和小时
	 * @param dateTime
	 * @return
	 */
	public static Time getDayDate(long dateTime){
		int hours = (int) (dateTime/1000/SECONDS_OF_HOUR);
		int days = hours/24;
		int hour = hours%24;
		Time time = new Time();
		time.day = days;
		time.hour = hour;
		return time;
	}
	
	public static class Time{
		int day = 0;
		int hour = 0;
		
		public int getDay() {
			return day;
		}
		
		public void setDay(int day) {
			this.day = day;
		}
		
		public int getHour() {
			return hour;
		}
		
		public void setHour(int hour) {
			this.hour = hour;
		}
		
	}
	
	
	public static void main(String[] args) {
		//System.out.println(MD5.encode("admin"));
		//getDayDate(604700000);
		/*Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime().getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		System.out.println(calendar.getTime().getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		System.out.println(calendar.getTime().getTime());*/
		/*Date[] dates = getMonthDate(new Date());
		System.out.println(format(dates[0], YYYY_MM_DD_HH_MM_SS));
		System.out.println(format(dates[1], YYYY_MM_DD_HH_MM_SS));*/
		/*Date date = getDate("2016-02-11 00:00:00", YYYY_MM_DD_HH_MM_SS);
		System.out.println(date.getHours()+date.getMinutes()+date.getSeconds());
		date.setTime(date.getTime() - 1000);
		System.out.println(format(date, YYYY_MM_DD_HH_MM_SS));*/
		/*Date date = new Date();
		date.setTime(1453305600000L);
		Sys*///tem.out.println(format(date, YYYY_MM_DD_HH_MM_SS));
		String o = "AccouMt";
		String oh = o.substring(0, 1);
		String ol = o.substring(1, o.length());
		System.out.println(oh.toLowerCase()+ol);
	}
	

}
