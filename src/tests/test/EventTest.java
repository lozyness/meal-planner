package test;

import org.junit.Test;
import shawley.Event;
import shawley.TimeSlot;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventTest {

    @Test
    public void checkEventDateCanBeSet() {
        Date date = new Date();
        Event event = new Event(date);
        assertEquals("Date not as set", date, event.getDate());
    }

    @Test
    public void checkEventCanExistInMorning() {
        Date date = new Date();
        Event event = new Event(date);
        event.addTimeSlot(TimeSlot.createMorningTimeSlot());
        assertTrue("Event not in morning", event.isMorning());
        assertFalse("Event in afternoon", event.isAfternoon());
        assertFalse("Event in evening", event.isEvening());
    }

    @Test
    public void checkEventCanExistInAfternoon() {
        Date date = new Date();
        Event event = new Event(date);
        event.addTimeSlot(TimeSlot.createAfternoonTimeSlot());
        assertFalse("Event in morning", event.isMorning());
        assertTrue("Event not in afternoon", event.isAfternoon());
        assertFalse("Event in evening", event.isEvening());
    }

    @Test
    public void checkEventCanExistInEvening() {
        Date date = new Date();
        Event event = new Event(date);
        event.addTimeSlot(TimeSlot.createEveningTimeSlot());
        assertFalse("Event in morning", event.isMorning());
        assertFalse("Event in afternoon", event.isAfternoon());
        assertTrue("Event not in afternoon", event.isEvening());
    }

    @Test
    public void checkEventCanExistInMorningAndAfternoon() {
        Date date = new Date();
        Event event = new Event(date);
        event.addTimeSlot(TimeSlot.createMorningTimeSlot());
        event.addTimeSlot(TimeSlot.createAfternoonTimeSlot());
        assertTrue("Event not in morning", event.isMorning());
        assertTrue("Event not in afternoon", event.isAfternoon());
        assertFalse("Event in afternoon", event.isEvening());
    }

    @Test
    public void getAllTimeslotsInEvent() {
        Event event = new Event(new Date());
        event.addTimeSlot(TimeSlot.createEveningTimeSlot());
        assertEquals(1, event.getTimeslots().size());
    }

}