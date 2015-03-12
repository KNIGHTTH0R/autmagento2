package com.tools.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static String getAndFormatCurrentDate() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date(System.currentTimeMillis() - 3600 * 1000);
//		
//		String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
//		Calendar cal = Calendar.getInstance();
//		String month = monthName[cal.get(Calendar.MONTH)];	
//		
//		return String.valueOf(month + " " + dateFormat.format(date));
		return dateFormat.format(date);

	}


}
