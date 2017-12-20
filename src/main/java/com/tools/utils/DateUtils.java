package com.tools.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.tools.persistance.MongoReader;

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

	public static Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	
	public static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //2017-11-03 17:00:00
        return dateFormat.format(yesterday());
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

	public static String getFirstDayOfCurrentMonth(String formatString) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 00, 00, 00);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(formatString);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}

	public static String getLastDayOfNextMonth(String formatString) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		Date lastDayOfMonth = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat(formatString);

		return String.valueOf(sdf.format(lastDayOfMonth));
	}

	public static String getFirstDayOfNextMonth(String formatString) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
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

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(format.parse(dateString));
		calendar.add(Calendar.DATE, days);

		return String.valueOf(format.format(calendar.getTime()));
	}

	public static String addDaysToAAGivenDate(String dateString, String formatString, Locale locale, int days)
			throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString, locale);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);

		return String.valueOf(format.format(calendar.getTime()));
	}

	public static String getFirstFridayAfterDate(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			calendar.add(Calendar.DATE, 1);
		}

		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
			calendar.add(Calendar.DATE, 1);
		}

		return String.valueOf(format.format(calendar.getTime()));
	}
	
	public static String getFirstWednesdayAfterDate(String dateString, String formatString) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			calendar.add(Calendar.DATE, 1);
		}

		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
			calendar.add(Calendar.DATE, 1);
		}

		return String.valueOf(format.format(calendar.getTime()));
	}

	public static List<String> getFridaysBetweenDates(String startDateString, String endDateString, String formatString)
			throws ParseException {

		List<String> dates = new ArrayList<String>();
		DateFormat format = new SimpleDateFormat(formatString);
		Date startDate = format.parse(startDateString);
		Date endDate = format.parse(endDateString);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(endDate);

		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			c1.add(Calendar.DATE, 1);
		}

		while (c2.after(c1) || c2.equals(c1)) {

			if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				dates.add(format.format(c1.getTime()));
			}
			c1.add(Calendar.DATE, 1);
		}

		return dates;

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

	public static String parseDate(String dateString, String initialFormatString, Locale locale,
			String finalFormatString) throws ParseException {
		DateFormat formatInitial = new SimpleDateFormat(initialFormatString, locale);
		DateFormat formatFinal = new SimpleDateFormat(finalFormatString);
		Date date = formatInitial.parse(dateString);

		return String.valueOf(formatFinal.format(date));

	}

	public static String parseDate(String dateString, String initialFormatString, String finalFormatString)
			throws ParseException {
		DateFormat formatInitial = new SimpleDateFormat(initialFormatString);
		DateFormat formatFinal = new SimpleDateFormat(finalFormatString);
		Date date = formatInitial.parse(dateString);

		return String.valueOf(formatFinal.format(date));

	}

	public static String parseDate(String dateString, String initialFormatString, String finalFormatString,
			Locale locale) throws ParseException {
		DateFormat formatInitial = new SimpleDateFormat(initialFormatString);
		DateFormat formatFinal = new SimpleDateFormat(finalFormatString, locale);
		Date date = formatInitial.parse(dateString);

		return String.valueOf(formatFinal.format(date));

	}

	public static String parseMilisDate(String dateString, String finalFormatString){
	

		DateFormat formatter = new SimpleDateFormat(finalFormatString);

		long milliSeconds= Long.parseLong(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		
		return String.valueOf(formatter.format(calendar.getTime()));
		
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
	
	
	public static String addDaysToCurrentDate(String format, int days)
    {
		DateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
       // cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return String.valueOf(sdf.format(cal.getTime()));
    }

	public static String getPreviousMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static String getPreviousMonth(String dateString, String formatString) throws ParseException {

		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat(formatString).format(cal.getTime());
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

	public static String getNextMonthMiddle(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
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
		String[] parts = birthDate.split("-");
		
		LocalDate birthdate = new LocalDate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
				Integer.parseInt(parts[2]));
		LocalDate now = new LocalDate();
		Years age = Years.yearsBetween(birthdate, now);
		return age.getYears();
	}

	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	public static boolean isDateBeetween(String createdAt, String startDate, String endDate, String format)
			throws ParseException {
		DateFormat df = new SimpleDateFormat(format);

		return !(df.parse(createdAt).before(df.parse(startDate)) || df.parse(createdAt).after(df.parse(endDate)));
	}

	public static boolean isDateInCurrentMonth(String date, String format) throws ParseException {

		return DateUtils.isDateBeetween(date, DateUtils.getFirstDayOfCurrentMonth(format),
				DateUtils.getLastDayOfTheCurrentMonth(format), format);
	}

	public static boolean isDateInNextMonth(String date, String format) throws ParseException {

		return DateUtils.isDateBeetween(date, DateUtils.getFirstDayOfNextMonth(format),
				DateUtils.getLastDayOfNextMonth(format), format);
	}

	/*
	 * 
	 * returns true if currentDate is after or equal to startingDate
	 */
	public static boolean isDateAfter(String currentDate, String startingDate, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);

		return !(df.parse(currentDate).before(df.parse(startingDate)));
	}

	/*
	 * 
	 * returns true if date1 is before or equal to date2
	 */
	public static boolean isDateBefore(String date1, String date2, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);

		return !(df.parse(date1).after(df.parse(date2)));
	}

	public static int getNumberOfDaysBeetweenTwoDates(String startDate, String endDate, String formatString)
			throws ParseException {
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);

		Date date = sdf.parse(startDate);
		cal1.setTime(date);
		date = sdf.parse(endDate);
		cal2.setTime(date);

		return daysBetween(cal1.getTime(), cal2.getTime());
	}

	public static int daysBetween(Date d1, Date d2) {

		int result = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

		return result > 0 ? result : 0;
	}
	
	public static int timeBetween(Date d1, Date d2) {

		int result = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

		return result > 0 ? result : 0;
	}

	public static String[] getDateFields(String dateString, String formatString) throws ParseException {

		String[] components = new String[3];

		DateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		// month index starts from 0 (for 2015-06-01 month is 5 and need to add
		// 1)
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		components[0] = String.valueOf(year);
		components[1] = String.valueOf(month);
		components[2] = String.valueOf(day);

		return components;

	}

	public String formatMonth(int month, Locale locale) {
		DateFormatSymbols symbols = new DateFormatSymbols(locale);
		String[] monthNames = symbols.getMonths();
		return monthNames[month - 1];
	}

	public static void main(String args[]) throws ParseException {
		// System.out.println(DateUtils.getFirstDayOfAGivenMonth("2016-09-30
		// 12:00:00","yyyy-MM-dd HH:mm:ss"));
		// System.out.println(DateUtils.getLastDayOfAGivenMonth("2016-09-30
		// 12:00:00","yyyy-MM-dd HH:mm:ss"));
	//	System.out.println(DateUtils.getFridaysBetweenDates("2016-11-11", "2016-12-24", "yyyy-MM-dd"));
//		System.out.println(DateUtils.parseDate("2016-12-15", "yyyy-MM-dd", "dd. MM."));
		//System.out.println(DateUtils.parseDate("07. MRZ. 17", "dd. MMM. yy", new Locale.Builder().setLanguage("de").build(), "dd.MM.YYYY"));
	////	System.out.println(DateUtils.parseDate(dateString, initialFormatString, finalFormatString));
//		String expectedDate =DateUtils.parseDate("2017-05-04 11:35:03", "yyyy-MM-dd HH:mm:ss", "dd.MM.YYYY");
//		System.out.println(expectedDate);
		
		//System.out.println(DateUtils.addDays("yyyy-MM-dd", 1));
	//	System.out.println(DateUtils.parseDate("1484728910770", "dd. MMM. yy", new Locale.Builder().setLanguage("de").build(), "dd.MM.YYYY"));
		
		System.out.println(DateUtils.getYesterdayDateString());
	}

}
