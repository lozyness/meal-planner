package test;

import org.junit.Test;
import shawley.Event;
import shawley.TimeSlot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void checkEventDateCanBeSet() {
        Date date = new Date();
        Event event = new Event(date);
        assertEquals("Date not as set", date, event.getDate());
    }

    @Test
    public void checkEventCanExistInMorning() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR+3);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        assertTrue("Event not in morning", event.isMorning());
        assertFalse("Event in afternoon", event.isAfternoon());
        assertFalse("Event in evening", event.isEvening());
    }

    @Test
    public void checkEventCanExistInAfternoon() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR+3);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        assertFalse("Event in morning", event.isMorning());
        assertTrue("Event not in afternoon", event.isAfternoon());
        assertFalse("Event in evening", event.isEvening());
    }

    @Test
    public void checkEventCanExistInEvening() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR+3);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        assertFalse("Event in morning", event.isMorning());
        assertFalse("Event in afternoon", event.isAfternoon());
        assertTrue("Event not in afternoon", event.isEvening());
    }

    @Test
    public void checkEventCanExistInMorningAndAfternoon() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR + 3);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        assertTrue("Event not in morning", event.isMorning());
        assertTrue("Event not in afternoon", event.isAfternoon());
        assertFalse("Event in afternoon", event.isEvening());
    }

    @Test
    public void getAllTimeslotsInEvent() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR+3);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        assertEquals(1, event.getTimeslots().size());
    }

}