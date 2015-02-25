package shawley;

/**
 * Created by laura on 25/02/15.
 */
public class TimeSlot {

    private static final int MORNING = 0;
    private static final int AFTERNOON = 1;
    private static final int EVENING = 2;
    private static final int MISC = 3;

    private int timeslot;

    public TimeSlot(int timeslot) {
        this.timeslot = timeslot;
    }

    private static TimeSlot createTimeSlot(int timeslot) {
        return new TimeSlot(timeslot);
    }

    public static TimeSlot createMorningTimeSlot() {
        return TimeSlot.createTimeSlot(TimeSlot.MORNING);
    }

    public static TimeSlot createAfternoonTimeSlot() {
        return TimeSlot.createTimeSlot(TimeSlot.AFTERNOON);
    }

    public boolean isMorning() {
        return this.timeslot == TimeSlot.MORNING;
    }

    public boolean isAfternoon() {
        return this.timeslot == TimeSlot.AFTERNOON;
    }

    public boolean isEvening() {
        return this.timeslot == TimeSlot.EVENING;
    }

    public boolean isMisc() {
        return this.timeslot == TimeSlot.MISC;
    }

    public static TimeSlot createEveningTimeSlot() {
        return TimeSlot.createTimeSlot(TimeSlot.EVENING);
    }

    public static TimeSlot createMiscTimeSlot() {
        return TimeSlot.createTimeSlot(TimeSlot.MISC);
    }
}
