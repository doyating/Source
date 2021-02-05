/**
 * project name:LunchOrder
 * package name: com.application.api.util
 * file name:DateUtil.java
 *@date 2021年2月2日
 * 
 */
package com.application.api.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * @author Yu Chen Yen
 *
 */
public class DateUtil {

	/**
	 * @param args
	 * created date:2021年2月2日
	 * created time:上午11:30:10
	 * return type:void
	 */
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	private static final String TIME_PATTERN = "HH:mm";

	static GregorianCalendar calendar = new GregorianCalendar();
	/**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";
    
	public static Date getEndDateOfThisWeek(Date currentDate){
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7-calendar.get(Calendar.DAY_OF_WEEK));
		return getEndDate(calendar.getTime());
	}
	
	public static Date getStartDateOfThisWeek(Date currentDate){
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_WEEK)+1);
		return getStartDate(calendar.getTime());		
	}

	public static Date getPreviousNDay(Date date, int dayCount){
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -dayCount);
		return getEndDate(calendar.getTime());
	}

	/**
	 * Return the time difference in seconds.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getTimesBetween (Date startDate, Date endDate) {
		Calendar startCal  =  new GregorianCalendar(TimeZone.getDefault());
		startCal.setTime(startDate);
		Calendar endCal = new GregorianCalendar(TimeZone.getDefault());
		endCal.setTime(endDate);

        return getTimesBetween( startCal, endCal);
    }

    /**
     * Return the time difference in seconds.
     * @param startCal
     * @param startCal
     * @return
     */
    public static long getTimesBetween (Calendar startCal, Calendar endCal) {
        
        long millis = startCal.getTimeInMillis() - endCal.getTimeInMillis();

        if ( millis < 0 ) {
            millis = -millis;
        }

        return millis/1000;
	}

	/**
	 * Passing seconds and get a time String in the format of HH:MM:SS
	 * @param seconds
	 * @return time String in the format of HH:MM:SS
	 */
	public static String getTimeStringHMS(int seconds){
		StringBuffer sb = new StringBuffer();
		int mins = seconds/60;
		int hrs = mins/60;
		if(hrs>0) {
			sb.append(hrs).append(":");
		}
		if(mins>0){
			sb.append(mins %= 60).append(":");
		}
		sb.append(seconds %= 60);
		return sb.toString();
	}

    public static Date getStartDate(Date date){
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}
	public static Date getEndDate(Date date){
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}
	public static Date getNextDay(Date date){
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return (Date) calendar.getTime().clone();
	}
	public static Date getNextNDay(Date date, int n){
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return (Date) calendar.getTime().clone();
	}
    
    public static Date getNextHour(Date date){
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		return (Date) calendar.getTime().clone();
	}
    public static Date getNextNHour(Date date, int n){
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, n);
        return (Date) calendar.getTime().clone();
    }

    public static Date getNextWeek(Date date){
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_MONTH, 1);
		return (Date) calendar.getTime().clone();
	}
    public static Date getNextNWeek(Date date, int n){
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, n);
        return (Date) calendar.getTime().clone();
    }

    public static Date getNextMonth(Date date){
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return (Date) calendar.getTime().clone();
	}
    public static Date getNextNMonth(Date date, int n){
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return (Date) calendar.getTime().clone();
    }
	public static Date getBeginOfMonth(Date date, int months){
		calendar.setTime(date);
		if(months!=0)
			calendar.add(Calendar.MONTH, months);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndOfMonth(Date date, int months){
		calendar.setTime(date);
		if(months!=0)
			calendar.add(Calendar.MONTH, months);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return (Date) calendar.getTime().clone();
	}
	public static Date getBeginOfYear(Date date){
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar
				.getActualMinimum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndOfYear(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar
				.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

    public static Date getEndOfYear(int year) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, calendar
				.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static int monthsBetween(Date startDate, Date endDate) {
		calendar.setTime(startDate);
		int startMonth = calendar.get(Calendar.MONTH);
		int startYear = calendar.get(Calendar.YEAR);
		calendar.setTime(endDate);
		int endMonth = calendar.get(Calendar.MONTH);
		int endYear = calendar.get(Calendar.YEAR);
		return (endYear-startYear)*12 + (endMonth-startMonth);
	}

	public static int daysBetween(Date startDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        return daysBetween(c1, c2);
	}

	public static int daysBetween(Calendar early, Calendar late) {
		return (int) (toJulian(late) - toJulian(early));
	}
	
	public static float toJulian(Calendar c) {
        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        return (C + D + E + F) - 1524.5f;
    }

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(BUNDLE_KEY, locale)
					.getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	public static String getDateTimeToMinutesPattern() {
		return DateUtil.getDatePattern() + " hh:mm a";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask   the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException when String doesn't match the expected format
	 * @see java.text.SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			//log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

    /**
     * create a tempstamp using a string and a format
     *
     * @param dateString String
     * @param theFormat  String
     * @return Timestamp
     * @throws Exception
     */
    public static Timestamp convertTimestamp(String dateString, String theFormat)
            throws Exception {
        try {
            SimpleDateFormat sd = new SimpleDateFormat(theFormat);
            java.util.Date d = sd.parse(dateString);

            return new Timestamp(d.getTime());
        } catch (Exception e) {
            return null;
        }
    }

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(),
					pe.getErrorOffset());
		}

		return aDate;
	}

    public static int LEGAL_AGE = 18;

    public static String  DATE_FORMAT =  "^([0]\\d|[1][0-2])/([0-2]\\d|[3][0-1])/([2][01]|[1][6-9])\\d{2}(\\s([0-1]\\d|[2][0-3])(:[0-5]\\d){1,2})?$";

    public static String MMYY_DATE_FORMAT = "^([0]\\d|[1][0-2])/([2][01]|[1][6-9])\\d{2}(\\s([0-1]\\d|[2][0-3])(\\:[0-5]\\d){1,2})?$";

    public static Timestamp createTimestamp(int year, int month, int date) {
        try {
            Calendar cal = new GregorianCalendar(year, month, date);
            if (cal.get(Calendar.MONTH ) != month) {
                return null;
            }
            return new Timestamp(cal.getTime().getTime());
        }
        catch(Exception e) {
            return null;
        }
    }

    public static Timestamp createTimestamp(String year, String month, String date) {
        try {
            return createTimestamp(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
        }
        catch (Exception e) {
            return null;
        }

    }


    public static boolean isLegalAge (Timestamp birthDate) {
        //log.debug("birthDate = " + birthDate);
        Calendar today = new GregorianCalendar();
        Calendar birthDateCalendar = new GregorianCalendar();
        birthDateCalendar.setTimeInMillis(birthDate.getTime());
        int age = today.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR);
        //log.debug("age = " + age);
        return (age > LEGAL_AGE) || ((age >= LEGAL_AGE) && isDateAfter(birthDateCalendar, today));
    }

    public static boolean isDateFormat (String Date) {                     
        Pattern p = Pattern.compile(DATE_FORMAT);
        Matcher m = p.matcher(Date);
        return m.matches();             
    }

    public static boolean isMMYYDateFormat (String Date) {
        Pattern p = Pattern.compile(MMYY_DATE_FORMAT);
        Matcher m = p.matcher(Date);
        return m.matches();      
    }

    private static boolean isDateAfter(Calendar birthDate, Calendar today) {
        today.set(Calendar.YEAR, 2000);
        birthDate.set(Calendar.YEAR, 2000);
        return birthDate.getTime().before(today.getTime());
    }

	
	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//      Calendar cal = new GregorianCalendar();
//      cal.set(Calendar.YEAR, 1987);
//      cal.set(Calendar.MONTH, 11);
//      cal.set(Calendar.DATE, 22);
//      cal.set(Calendar.HOUR, 0);
//      cal.set(Calendar.MINUTE, 0);
//      cal.set(Calendar.SECOND, 0);
//      cal.set(Calendar.MILLISECOND, 0);
//      log.debug(isLegalAge(new Timestamp(cal.getTimeInMillis())));
//
//      Timestamp ts = createTimestamp(1963, 9, 11);
//      log.debug("ts = " +  ts);

//      Date aDate = new Date();
//      
//
//      LinkedHashMap <String, String> lookup = new LinkedHashMap();
//      String []ids = {"US/Hawaii", "US/Alaska","US/Pacific","US/Mountain","US/Central","US/Eastern"};
//      for (String id : ids) {
//          TimeZone tz = TimeZone.getTimeZone(id);
//          lookup.put(""+tz.getRawOffset()/60/60/1000, id);
//      }
//      for (String theKey : lookup.keySet()) {
//          System.out.println(theKey + " | " + lookup.get(theKey));
//      }
//      String [] ids = TimeZone.getAvailableIDs(-8*60*60*1000);
//      for (String id : ids) {
//          TimeZone tz = TimeZone.getTimeZone(id);
//          System.out.println("id = " + id + " and offset = " + tz.getRawOffset() / 60 / 60 / 1000);
//      }
//      Timestamp ts = new Timestamp (System.currentTimeMillis());


//      System.out.println(formatTimeWithTZ(ts, "HST"));
//      getTimezoneForOffset("-7");
	}
	/*
	 * created by:Yu Chen Yen
	 * created date:2021年2月2日
	 * created time:上午11:30:09
	 * drescription:TODO
	 */
}
