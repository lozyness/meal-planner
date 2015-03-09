package shawley;

import java.util.*;

/**
 * Created by laura on 25/02/15.
 */
public class Schedule {
    private Map<Date,List<TimeSlot>> timeSlots = new HashMap<Date,List<TimeSlot>>();
    private List<TimeSlot> allTimeslots = new ArrayList<TimeSlot>();
    private static Calendar cal = new GregorianCalendar();

    public Schedule(int days) {
        this.cal.setTime(new Date());
        for(int i=0; i<days; i++) {
            Date date = this.cal.getTime();
            this.addStandardTimeSlotListToDate(date);
            this.cal.add(Calendar.DATE, 1);
        }
    }

    public Schedule(Date date) {
        this.addStandardTimeSlotListToDate(date);
    }

    public Schedule(Date from, Date to) {
        List<Date> dates = this.getDateListForDateRange(from, to);
        Iterator<Date> iter = dates.iterator();
        while(iter.hasNext()) {
            Date date = iter.next();
            this.addStandardTimeSlotListToDate(date);
        }
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
        ArrayList<TimeSlot> times = new ArrayList<TimeSlot>();
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
        List<Date> dates = new ArrayList<Date>();
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
        Iterator<Event> eventsIter = eventList.iterator();
        while (eventsIter.hasNext()) {
            Event event = eventsIter.next();
            this.removeEventTimeslotsFromSchedule(date, event);
        }
    }

    private void removeEventTimeslotsFromSchedule(Date date, Event event) {
        List<TimeSlot> eventTimeslots = event.getTimeslots();
        Iterator<TimeSlot> eventTimeslotIter = eventTimeslots.iterator();
        while(eventTimeslotIter.hasNext()) {
            TimeSlot ts = eventTimeslotIter.next();
            this.getTimeSlots(date).remove(ts);
        }

    }

    public Map<Date, List<TimeSlot>> getAllTimeSlots() {
        return this.timeSlots;
    }
}
