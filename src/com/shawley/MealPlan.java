package shawley;

import java.util.*;

/**
 * Created by laura on 02/03/15.
 */
public class MealPlan {
    private Schedule schedule;

    private MealPlan(Date from, Date to) {
        this.schedule = new Schedule(from, to);
    }

    public static MealPlan createMealPlanForDateRange(Date from, Date to) {
        return new MealPlan(from, to);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void addEvent(Event event) {
        Date date = event.getDate();
        updateSchedule(date, event);
    }

    private void updateSchedule(Date date, Event event) {
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(event);
        this.schedule.updateScheduleGivenEventList(date, eventList);
    }
}
