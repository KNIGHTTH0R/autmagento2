package com.tools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.tools.env.constants.DateConstants;

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

	public static boolean isLastDayOfMonth(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.get(Calendar.DAY_OF_MONTH) == 1;
	}

	public static boolean isFirstDayOfMonth(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
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
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 00, 00, 00);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(formatString);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}

	public static String addDaysToAAGivenDate(String dateString, String formatString, int days) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);

		return String.valueOf(format.format(calendar.getTime()));
	}

	public static String addDaysToAAGivenDate(String dateString, String formatString, Locale locale, int days) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString, locale);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);

		return String.valueOf(format.format(calendar.getTime()));
	}

	public static String addHoursToAGivenDate(String dateString, String formatString, int hours) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);

		return String.valueOf(format.format(calendar.getTime()));
	}

	public static String getCurrentDate(String format) {
		DateFormat sdf = new SimpleDateFormat(format);

		return String.valueOf(sdf.format(new Date()));
	}

	public static String getCurrentDate(String format, Locale locale) {
		DateFormat sdf = new SimpleDateFormat(format, locale);

		return String.valueOf(sdf.format(new Date()));
	}

	public static String getCurrentDateBegining(String format) {
		DateFormat sdf = new SimpleDateFormat(format);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 00, 00, 00);

		return String.valueOf(sdf.format(calendar.getTime()));
	}

	public static String getCurrentDateOneHourBack(String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, -1);

		return String.valueOf(sdf.format(calendar.getTime()));
	}

	public static String getCurrentDateTwoHoursBack(String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, -2);

		return String.valueOf(sdf.format(calendar.getTime()));
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
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		cal.set(year, month, day, 00, 00, 00);
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static String getThreeMonthsBackMiddle(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		cal.set(Calendar.DAY_OF_MONTH, 15);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		cal.set(year, month, day, 00, 00, 00);
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

	public static boolean isDateAfter(String currentDate, String startingDate, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);

		return !(df.parse(currentDate).before(df.parse(startingDate)));
	}

	public static int getNumberOfDaysBeetweenTwoDates(String firstDate, String secondDate, String formatString) throws ParseException {
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);

		Date date = sdf.parse(firstDate);
		cal1.setTime(date);
		date = sdf.parse(secondDate);
		cal2.setTime(date);

		return daysBetween(cal1.getTime(), cal2.getTime());
	}

	public static int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public static void main(String args[]) throws ParseException{
		System.out.println(DateUtils.addHoursToAGivenDate(DateUtils.getThreeMonthsBackMiddle(DateConstants.FORMAT), DateConstants.FORMAT_12_HOURS, 1));
	}

}
