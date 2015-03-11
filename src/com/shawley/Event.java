package shawley;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private final Date from;
    private List<TimeSlot> timeslots = new ArrayList<>();

    public Event(Date date) {
        this.from = date;
        this.configureTimeslotsForDate(date, new Date());
    }

    public Event(Date from, Date to) {
        this.from = from;
        this.configureTimeslotsForDate(from, to);
    }

    private void configureTimeslotsForDate(Date from, Date to) {
        this.timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
    }

    public Date getDate() {
        return from;
    }

    public boolean isMorning() {
        return this.timeslots.contains(TimeSlot.createMorningTimeSlot());
    }

    public boolean isAfternoon() {
        return this.timeslots.contains(TimeSlot.createAfternoonTimeSlot());
    }

    public boolean isEvening() {
        return this.timeslots.contains(TimeSlot.createEveningTimeSlot());
    }

    public List<TimeSlot> getTimeslots() {
        return this.timeslots;
    }
}
