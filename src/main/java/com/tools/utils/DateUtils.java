package com.tools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class DateUtils {

	public static String getLastDayOfTheCurrentMonth(String format) {
		Date today = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(format);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}
	public static boolean isLastDayOfMonth(String dateString, String formatString) throws ParseException
	{
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
	    return calendar.get(Calendar.DAY_OF_MONTH) == 1;
	}

	public static String getLastDayOfPreviousMonth(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(formatString);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}

	public static String getLastDayOfAGivenMonth(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(formatString);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}

	public static String getFirstDayOfAGivenMonth(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		//if something goes wrong on backwards compatibility delete this - start
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 00, 00, 00);
		//end
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(formatString);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}

	public static String getCurrentDate(String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Date today = new Date();

		return String.valueOf(sdf.format(today));
	}

	public static String getPreviousMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static String getPreviousMonthMiddle(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 15);
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static int getAge(String birthDate) {
		String[] parts = birthDate.split("/");

		LocalDate birthdate = new LocalDate(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
		LocalDate now = new LocalDate();
		Years age = Years.yearsBetween(birthdate, now);

		return age.getYears();
	}

	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	public static boolean isDateBeetween(String createdAt, String startDate, String endDate, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);

		return !(df.parse(createdAt).before(df.parse(startDate)) || df.parse(createdAt).after(df.parse(endDate)));
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.getLastDayOfAGivenMonth("2015-08-15 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.getFirstDayOfAGivenMonth("2015-08-15 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.isLastDayOfMonth("2015-09-30 00:00:00", "yyyy-MM-dd HH:mm:ss"));
	}

}
