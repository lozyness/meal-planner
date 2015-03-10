package test;

import org.junit.Test;
import shawley.Event;
import shawley.MealPlan;
import shawley.Schedule;
import shawley.TimeSlot;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MealPlanTest {
    //@TODO: Remove excessive duplication between tests

    @Test
    public void checkMealPlanCreatesAccurateDefaultSchedule() {
        int numOfDays = 3;
        int defaultTimeslotsPerDay = 3;
        Calendar cal = new GregorianCalendar();
        Date from = new Date();
        cal.setTime(from);
        cal.add(Calendar.DATE, numOfDays-1);
        Date to = cal.getTime();
        Schedule schedule = MealPlan.createMealPlanForDateRange(from, to).getSchedule();
        List<TimeSlot> times = schedule.getTimeSlots();
        assertEquals("Generated schedule does not have expected number of days",numOfDays*defaultTimeslotsPerDay, times.size());
    }

    @Test
    public void checkMealPlanScheduleIsUpdatedWithAnyEvents() {
        int numOfDays = 3;
        int defaultTimeslotsPerDay = 3;
        Calendar cal = new GregorianCalendar();
        Date from = new Date();
        cal.setTime(from);
        cal.set(Calendar.HOUR, TimeSlot.MORNING_START_HOUR);
        from = cal.getTime();
        cal.add(Calendar.HOUR, 1);
        Date to = cal.getTime();
        Event event = new Event(from, to);
        cal.add(Calendar.DATE, numOfDays - 1);
        to = cal.getTime();
        MealPlan plan = MealPlan.createMealPlanForDateRange(from, to);
        plan.addEvent(event);
        List<TimeSlot> times = plan.getSchedule().getTimeSlots();
        assertEquals("Generated schedule does not have expected number of days",numOfDays*defaultTimeslotsPerDay -1, times.size());
    }

}