package test;

import org.junit.Test;
import shawley.Schedule;
import shawley.TimeSlot;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScheduleTest {

    @Test
    public void testGenerateScheduleForTodayGeneratesRequiredTimeSlots() {
        Schedule schedule = new Schedule(new Date());
        List<TimeSlot> list = new ArrayList<TimeSlot>();
        list.add(TimeSlot.createMorningTimeSlot());
        list.add(TimeSlot.createAfternoonTimeSlot());
        list.add(TimeSlot.createEveningTimeSlot());
        assertEquals("Number of timeslots in schedule for one day not as expected", list.size(), schedule.getTimeSlots().size());
        assertEquals(list, schedule.getTimeSlots());
    }

    @Test
    public void testGenerateScheduleForTodayAndTomorrowGeneratesRequiredTimeSlots() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date today = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date tomorrow = cal.getTime();
        Schedule schedule = new Schedule(today, tomorrow);
        List<TimeSlot> list = new ArrayList<TimeSlot>();
        list.add(TimeSlot.createMorningTimeSlot());
        list.add(TimeSlot.createAfternoonTimeSlot());
        list.add(TimeSlot.createEveningTimeSlot());
        list.add(TimeSlot.createMorningTimeSlot());
        list.add(TimeSlot.createAfternoonTimeSlot());
        list.add(TimeSlot.createEveningTimeSlot());
        assertEquals("Number of timeslots in schedule for two days not as expected", list.size(), schedule.getTimeSlots().size());
        assertEquals(list, schedule.getTimeSlots());
    }

    @Test
    public void testCanRetrieveTodaysTimeSlots() {
        Schedule schedule = new Schedule(2);
        List<TimeSlot> list = new ArrayList<TimeSlot>();
        list.add(TimeSlot.createMorningTimeSlot());
        list.add(TimeSlot.createAfternoonTimeSlot());
        list.add(TimeSlot.createEveningTimeSlot());
        Date date = new Date();
        List<TimeSlot> returnedTimeSlots = schedule.getTimeSlots(new Date());
        assertEquals("Number of timeslots in schedule for one day not as expected", list.size(), returnedTimeSlots.size());
        assertEquals(list, returnedTimeSlots);
    }

    @Test
    public void testCanGenerateTimeSlotsForDateRange() {
        List<TimeSlot> list = new ArrayList<TimeSlot>();
        list.add(TimeSlot.createMorningTimeSlot());
        list.add(TimeSlot.createAfternoonTimeSlot());
        list.add(TimeSlot.createEveningTimeSlot());
        Calendar cal = new GregorianCalendar();
        Date from = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date mid = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();
        Schedule schedule = Schedule.createScheduleForDateRange(from, to);

        List<TimeSlot> slots = schedule.getTimeSlots(from);
        assertNotNull(slots);
        assertEquals("First day does not have expected number of timeslots", list.size(), schedule.getTimeSlots(from).size());
        slots = schedule.getTimeSlots(mid);
        assertNotNull(slots);
        assertEquals("Middle day does not have expected number of timeslots", list.size(), schedule.getTimeSlots(mid).size());
        slots = schedule.getTimeSlots(to);
        assertNotNull(slots);
        assertEquals("Last day does not have expected number of timeslots", list.size(), schedule.getTimeSlots(to).size());
        slots = schedule.getTimeSlots();
        assertNotNull(slots);
        assertEquals("Date range does not have expected number of timeslots", list.size() * 3, schedule.getTimeSlots().size());
    }
}