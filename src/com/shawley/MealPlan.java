package shawley;

import java.util.*;

public class MealPlan {
    private final Schedule schedule;

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
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        this.schedule.updateScheduleGivenEventList(date, eventList);
    }
}
