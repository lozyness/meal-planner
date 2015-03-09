package test;

import org.junit.Test;
import shawley.Event;
import shawley.Schedule;
import shawley.TimeSlot;

import java.util.*;

import static org.junit.Assert.*;

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
        Date[] dates = this.getScheduleForNumberOfDays(2);
        Schedule schedule = new Schedule(dates[0], dates[1]);
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
        Date[] dates = this.getScheduleForNumberOfDays(2);
        Schedule schedule = new Schedule(dates[0], dates[1]);
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

        Date[] dates = this.getScheduleForNumberOfDays(3);
        Date from = dates[0];
        Date mid = dates[1];
        Date to = dates[2];
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

    @Test
    public void testCanGenerateAvailableTimeslotsGivenAnEventList() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR + 3);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(event);
        Schedule schedule = new Schedule(from);
        schedule.updateScheduleGivenEventList(from, eventList);
        List<TimeSlot> timeslotList = schedule.getTimeSlots(from);
        assertEquals(2, timeslotList.size());
        assertTrue(timeslotList.contains(TimeSlot.createAfternoonTimeSlot()));
        assertTrue(timeslotList.contains(TimeSlot.createEveningTimeSlot()));
    }

    @Test
    public void checkGetAllTimeslotsReturnsExpectedValues() {
        Date[] dates = this.getScheduleForNumberOfDays(2);
        Schedule schedule = new Schedule(dates[0], dates[1]);
        Map<Date, List<TimeSlot>> timeslotMap = schedule.getAllTimeSlots();
        assertEquals(2, timeslotMap.keySet().size());
        Iterator<Date> iterator = timeslotMap.keySet().iterator();
        while (iterator.hasNext()) {
            List<TimeSlot> times = timeslotMap.get(iterator.next());
            assertEquals(3, times.size());
        }
    }

    private Date[] getScheduleForNumberOfDays(int numOfDays) {
        Date[] dates = new Date[numOfDays];
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        for(int i=0; i<numOfDays; i++) {
            dates[i] = cal.getTime();
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

}