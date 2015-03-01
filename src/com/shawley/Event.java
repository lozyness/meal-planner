package shawley;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laura on 26/02/15.
 */
public class Event {
    private Date date;
    private List<TimeSlot> timeslots = new ArrayList<TimeSlot>();

    public Event(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void addTimeSlot(TimeSlot timeSlot) {
        this.timeslots.add(timeSlot);
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
