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

}