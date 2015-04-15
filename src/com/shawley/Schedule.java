package shawley;

import java.util.*;

public class Schedule {
    private final Map<Date,List<TimeSlot>> timeSlots = new HashMap<>();
    private final List<TimeSlot> allTimeslots = new ArrayList<>();
    private static final Calendar cal = new GregorianCalendar();
    private Date from;
    private Date to;

    public Schedule(Date date) {
        this.from = date;
        this.to = date;
        this.addStandardTimeSlotListToDate(date);
    }

    public Schedule(Date from, Date to) {
        this.from = from;
        this.to = to;
        List<Date> dates = this.getDateListForDateRange(from, to);
        dates.forEach(this::addStandardTimeSlotListToDate);
    }

    private void addStandardTimeSlotListToDate(Date date) {
        this.addTimeSlotListToDate(date, this.createStandardDayTimeSlotList());
    }

    private void addTimeSlotListToDate(Date date, List<TimeSlot> timeSlotList) {
        date = this.getMidnight(date);
        this.timeSlots.put(date, timeSlotList);
        this.allTimeslots.addAll(timeSlotList);
    }

    private List<TimeSlot> createStandardDayTimeSlotList() {
        ArrayList<TimeSlot> times = new ArrayList<>();
        times.add(TimeSlot.createMorningTimeSlot());
        times.add(TimeSlot.createAfternoonTimeSlot());
        times.add(TimeSlot.createEveningTimeSlot());
        return times;
    }

    public List<TimeSlot> getTimeSlots() {
        return this.allTimeslots;
    }

    public List<TimeSlot> getTimeSlots(Date date) {
        date = this.getMidnight(date);
        return this.timeSlots.get(date);
    }

    private Date getMidnight(Date date) {
        cal.setTime(date);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Schedule createScheduleForDateRange(Date from, Date to) {
        return new Schedule(from, to);
    }

    private List<Date> getDateListForDateRange(Date from, Date to) {
        List<Date> dates = new ArrayList<>();
        cal.setTime(from);
        while (cal.getTime().before(to) || cal.getTime().equals(to))
        {
            Date result = cal.getTime();
            dates.add(result);
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public void updateScheduleGivenEventList(Date date, List<Event> eventList) {
        for (Event event : eventList) {
            this.removeEventTimeslotsFromSchedule(date, event);
        }
    }

    private void removeEventTimeslotsFromSchedule(Date date, Event event) {
        List<TimeSlot> eventTimeslots = event.getTimeslots();
        for (TimeSlot ts : eventTimeslots) {
            this.getTimeSlots(date).remove(ts);
            this.allTimeslots.remove(ts);
        }

    }

    public Map<Date, List<TimeSlot>> getAllTimeSlots() {
        return this.timeSlots;
    }

    public boolean hasMorningOnDate(Date date) {
        List<TimeSlot> times = this.getTimeSlots(date);
        return times.contains(TimeSlot.createMorningTimeSlot());
    }

    public boolean hasAfternoonOnDate(Date date) {
        List<TimeSlot> times = this.getTimeSlots(date);
        return times.contains(TimeSlot.createAfternoonTimeSlot());
    }

    public boolean hasEveningOnDate(Date date) {
        List<TimeSlot> times = this.getTimeSlots(date);
        return times.contains(TimeSlot.createEveningTimeSlot());
    }

    public Date getStartDate() {
        return this.from;
    }

    public Date getEndDate() {
        return this.to;
    }
}
