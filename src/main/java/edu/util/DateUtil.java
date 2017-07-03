package edu.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

// This utility class is added to support date coversion in various formats.
public class DateUtil {
	
	private static Calendar today = new GregorianCalendar();
	private static DateFormat sdf;
	private static Date currentDateTime;
	protected DateUtil()
	{
		
	}
	
	public static String convertSQLDateToStringDate(Date sqlDate)
	{
		String stringDate = null;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			stringDate = sdf.format(sqlDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringDate;	
	}
	public static String convertSQLDateToStringDate(Date sqlDate,String datePattern)
	{
		String stringDate = null;
		try {
			sdf = new SimpleDateFormat(datePattern);
			stringDate = sdf.format(sqlDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringDate;	
	}
	public static Date convertStringDateToSQLDate(String stringDate)
	{
		Date sqlDate = null;
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			today.setTime(sdf.parse(stringDate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlDate = new Date(today.getTimeInMillis());
		return sqlDate;
	}
	public static java.util.Date convertStringDateToUtilDate(String stringDate, String datePattern){
		java.util.Date utilDate = null;
		try{
			DateFormat formatter = new SimpleDateFormat(datePattern);
			utilDate = formatter.parse(stringDate);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return utilDate;
	}
	public static Date getCurrentDateTime() {
		currentDateTime = new Date(new java.util.Date().getTime());
		return currentDateTime;
	}
	
	public static Timestamp getCurrentTime()
	{
		return new Timestamp(getCurrentDateTime().getTime());
	}
	public static Date convertUtilDateToSQLDate(java.util.Date utilDate)
	{
		return new Date(utilDate.getTime());
	}
	
	public static Date getOneDayBeforeDate(Date fromDate)
	{
		Date toDate = null;
		try {
			today.setTime(fromDate);
			today.add(Calendar.DAY_OF_MONTH, -1);
			toDate = new Date(today.getTimeInMillis());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toDate;
	}
	
	public static Date getOneDayAheadDate(Date fromDate)
	{
		Date toDate = null;
		try {
			today.setTime(fromDate);
			today.add(Calendar.DAY_OF_MONTH, 1);
			toDate = new Date(today.getTimeInMillis());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toDate;
	}
	
	public static Date convertTimestampToSQLDate(Timestamp time)
	{
		return new Date(time.getTime());
	}
	
	public static Timestamp getOneDayAheadTime(Timestamp time)
	{
		Timestamp newTime = null;
		Date sqlDate = convertTimestampToSQLDate(time);
		Date modifiedDate = getOneDayAheadDate(sqlDate);
		newTime = convertSQLDateToTimestamp(modifiedDate);
		return newTime;
	}
	
	public static Timestamp getOneDayBeforeTime(Timestamp time)
	{
		Timestamp newTime = null;
		Date sqlDate = convertTimestampToSQLDate(time);
		Date modifiedDate = getOneDayBeforeDate(sqlDate);
		newTime = convertSQLDateToTimestamp(modifiedDate);
		return newTime;
	}
	
	
	
	
	public static Timestamp convertSQLDateToTimestamp(Date sqlDate)
	{
		Timestamp timeStamp = new Timestamp(sqlDate.getTime());
		
		return timeStamp;
	}
	
	public static Timestamp convertUtilDateToTimestamp(java.util.Date utilDate)
	{
		Timestamp timeStamp = new Timestamp(utilDate.getTime());
		
		return timeStamp;
	}
	
	public static Date getSQLDate()
	{
		return new Date(new java.util.Date().getTime());
	}
	
	public static java.util.Date getUtilDate()
	{
		return new java.util.Date();
	}
	
}