package com.example.demo.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * zhufeng 2019-02-28
 * 时间格式处理类
 * */
public class DateTimeUnits {
	
	/*
	 * 得到现在时间
	 * */  
	public static Date getNow() {  
	    Date currentTime = new Date();  
	    return currentTime;  
	}
	
	public static String getNowStr() {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return timestamp;
	}
	
	public static String getNowMs() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return timestamp;
	}
	
	/*
	 * 得到现在时间
	 * */ 
	public static int getNowDay() {  
	    Date currentTime = new Date(); 
	    return Convert2Int(currentTime);
	}  
	
	/*
	 * 将日期转为int，返回格式2018-11-01
	 * */
	public static String getNowDayStr() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(new Date());
		return dateString;
	}
	
	/*
	 * 将日期转为int，返回格式20181101
	 * */
	public static int Convert2Int(Date date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(date);
		int day = Integer.parseInt(dateString);
		return day;
	}
	
	
	/*
	 * 将int转为日期，输入格式20181101
	 * */ 
	public static Date Convert2Date(int dateNumber) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date date = formatter.parse(dateNumber+"", pos);  
	    return date;  
	}
	
	/*
	 * 将string转为日期，输入格式2018-11-01 08:50
	 * */ 
	public static Date Convert2DateYun(String dateNumber) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	    ParsePosition pos = new ParsePosition(0);  
	    Date date = formatter.parse(dateNumber, pos);  
	    return date;  
	}
	/*
	 * 转为格式：2018-12-12
	 * */
	public static String Convert2DateStrYun(int dateNumber) {
		Date date = Convert2Date(dateNumber);
		String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return timestamp;
	}
	
	/** 
	 * 根据一个日期，返回是星期几的字符串 
	 *  
	 * @param sdate 
	 * @return 
	 */  
	public static String getWeek(int dateNumber) {  
	    // 再转换为时间  
	    Date date = Convert2Date(dateNumber);  
	    Calendar c = Calendar.getInstance();  
	    c.setTime(date);  
	    // int hour=c.get(Calendar.DAY_OF_WEEK);  
	    // hour中存的就是星期几了，其范围 1~7  
	    // 1=星期日 7=星期六，其他类推  
	    return new SimpleDateFormat("EEEE").format(c.getTime());  
	}  
	  
	public static String getWeekStr(int dateNumber) {  
	    String str = "";  
	    str = getWeek(dateNumber);  
	    if ("1".equals(str)) {  
	        str = "星期日";  
	    } else if ("2".equals(str)) {  
	        str = "星期一";  
	    } else if ("3".equals(str)) {  
	        str = "星期二";  
	    } else if ("4".equals(str)) {  
	        str = "星期三";  
	    } else if ("5".equals(str)) {  
	        str = "星期四";  
	    } else if ("6".equals(str)) {  
	        str = "星期五";  
	    } else if ("7".equals(str)) {  
	        str = "星期六";  
	    }  
	    return str;  
	} 
	
	/*
	 * 获取两个时间差
	 * */
	public static long getDiffSeconds(Date beginTime, Date endTime) { 
	    
		long begin = beginTime.getTime();  
	    long end = endTime.getTime();  
	    long seconds = (end - begin) / 1000; 	    
	    return seconds;

	}
	
	public static Date addTime(Date date ,int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);//设置起时间 
		cal.add(Calendar.HOUR_OF_DAY, hour);
		//System.out.println("输出::"+cal.getTime());
		
		return cal.getTime();
	} 
	

}

