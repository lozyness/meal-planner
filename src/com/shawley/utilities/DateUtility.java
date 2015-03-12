package shawley.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtility {

    public static Date getMidnightForDate(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastDayOfWeekForWeekContainingDate(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return DateUtility.getMidnightForDate(cal.getTime());
    }


    public static Date getLastDayOfMonthAfterDate(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.DATE, -1);
        return DateUtility.getMidnightForDate(cal.getTime());
    }
}
