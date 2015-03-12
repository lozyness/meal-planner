package test.util;

import org.junit.Test;
import shawley.utilities.DateUtility;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateUtilityTest {

    @Test
    public void checkGetMidNightForDateSetsHourToZero() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR, 4);
        Date date = cal.getTime();
        cal.setTime(DateUtility.getMidnightForDate(date));
        assertEquals(0, cal.get(Calendar.HOUR));
    }

    @Test
    public void checkGetMidNightForDateSetsMinuteToZero() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 4);
        Date date = cal.getTime();
        cal.setTime(DateUtility.getMidnightForDate(date));
        assertEquals(0, cal.get(Calendar.MINUTE));
    }

    @Test
    public void checkGetMidNightForDateSetsSecondsToZero() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.SECOND, 4);
        Date date = cal.getTime();
        cal.setTime(DateUtility.getMidnightForDate(date));
        assertEquals(0, cal.get(Calendar.SECOND));
    }

    @Test
    public void checkGetMidNightForDateSetsMillisecondsToZero() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.MILLISECOND, 4);
        Date date = cal.getTime();
        cal.setTime(DateUtility.getMidnightForDate(date));
        assertEquals(0, cal.get(Calendar.MILLISECOND));
    }

    @Test
    public void checkGetLastDayOfWeekWhereCurrentDateIsMonday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date monday = cal.getTime();
        cal.setTime(monday);
        cal.add(Calendar.DATE, 5);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfWeekForWeekContainingDate(monday));
    }

    @Test
    public void checkGetLastDayOfWeekWhereCurrentDateIsSunday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date sunday = cal.getTime();
        cal.setTime(sunday);
        cal.add(Calendar.DATE, 6);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfWeekForWeekContainingDate(sunday));
    }

    @Test
    public void checkGetLastDayOfWeekWhereCurrentDateIsSaturday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        Date saturday = cal.getTime();
        cal.setTime(saturday);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfWeekForWeekContainingDate(saturday));
    }

    @Test
    public void checkGetLastDayOfWeekWhereCurrentDateIsFriday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        Date friday = cal.getTime();
        cal.setTime(friday);
        cal.add(Calendar.DATE, 1);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfWeekForWeekContainingDate(friday));
    }

    @Test
    public void checkGetLastDayOfMonthAfterDateGivenEarlyDateInApril() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 10);
        cal.set(Calendar.MONTH, Calendar.APRIL);
        Date tenthApril = cal.getTime();
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.MONTH, Calendar.MAY);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfMonthAfterDate(tenthApril));
    }

    @Test
    public void checkGetLastDayOfMonthAfterDateGivenLateDateInMay() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 20);
        cal.set(Calendar.MONTH, Calendar.MAY);
        Date twentiethMay = cal.getTime();
        cal.set(Calendar.DATE, 30);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfMonthAfterDate(twentiethMay));
    }

    @Test
    public void checkGetLastDayOfMonthAfterDateGivenFirstDateInMay() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, Calendar.MAY);
        Date twentiethMay = cal.getTime();
        cal.set(Calendar.DATE, 30);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfMonthAfterDate(twentiethMay));
    }

    @Test
    public void checkGetLastDayOfMonthAfterDateGivenLastDateInMay() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.MONTH, Calendar.MAY);
        Date twentiethMay = cal.getTime();
        cal.set(Calendar.DATE, 30);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfMonthAfterDate(twentiethMay));
    }

    @Test
    public void checkGetLastDayOfMonthAfterDateWorksAsExpectedForFebruaryOnNonLeapYear() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 10);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.YEAR, 2015);
        Date twentiethMay = cal.getTime();
        cal.set(Calendar.DATE, 28);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfMonthAfterDate(twentiethMay));
    }

    @Test
    public void checkGetLastDayOfMonthAfterDateWorksAsExpectedForFebruaryOnLeapYear() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 10);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.YEAR, 2016);
        Date twentiethMay = cal.getTime();
        cal.set(Calendar.DATE, 29);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        assertEquals(DateUtility.getMidnightForDate(cal.getTime()), DateUtility.getLastDayOfMonthAfterDate(twentiethMay));
    }

}