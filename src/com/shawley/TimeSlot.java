package shawley;

import java.util.*;

/**
 * Created by laura on 25/02/15.
 */
public class TimeSlot {

    private static final int MORNING = 0;
    private static final int AFTERNOON = 1;
    private static final int EVENING = 2;
    private static final int MISC = 3;
    public static final int MORNING_START_HOUR = 6;
    public static final int AFTERNOON_START_HOUR = 12;
    public static final int EVENING_START_HOUR = 17;
    public static final int EVENING_END_HOUR = 23;
    private static Date AFTERNOON_END_DATE;
    private static Date MORNING_END_DATE;
    private static Date MORNING_START_DATE;
    private static Date AFTERNOON_START_DATE;
    private static Date EVENING_START_DATE;
    private static Date EVENING_END_DATE;
    private static Calendar cal = new GregorianCalendar();

    //@TODO: break into two classes - Date and TimeSlot



    private int timeslot;

    private TimeSlot(int timeslot) {
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

    public static Date getMorningStartDate() {
        if(TimeSlot.MORNING_START_DATE == null) {
            Calendar cal = TimeSlot.cal;
            cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
            cal.setTime(TimeSlot.setDateToLowerLimitOfHour(cal.getTime()));
            TimeSlot.MORNING_START_DATE = cal.getTime();
        }
        return TimeSlot.MORNING_START_DATE;
    }

    public static Date getMorningEndDate() {
        if(TimeSlot.MORNING_END_DATE == null) {
            Calendar cal = TimeSlot.cal;
            cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR - 1);
            cal.setTime(TimeSlot.setDateToUpperLimitOfHour(cal.getTime()));
            TimeSlot.MORNING_END_DATE = cal.getTime();
        }
        return TimeSlot.MORNING_END_DATE;
    }

    public static Date getAfternoonStartDate() {
        if (TimeSlot.AFTERNOON_START_DATE == null) {
            Calendar cal = TimeSlot.cal;
            cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR);
            cal.setTime(TimeSlot.setDateToLowerLimitOfHour(cal.getTime()));
            TimeSlot.AFTERNOON_START_DATE = cal.getTime();
        }
        return TimeSlot.AFTERNOON_START_DATE;
    }

    public static Date getAfternoonEndDate() {
        if(TimeSlot.AFTERNOON_END_DATE == null) {
            Calendar cal = TimeSlot.cal;
            cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR - 1);
            cal.setTime(TimeSlot.setDateToUpperLimitOfHour(cal.getTime()));
            TimeSlot.AFTERNOON_END_DATE = cal.getTime();
        }
        return TimeSlot.AFTERNOON_END_DATE;
    }

    public static Date getEveningStartDate() {
        if (TimeSlot.EVENING_START_DATE == null) {
            Calendar cal = TimeSlot.cal;
            cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR);
            cal.setTime(TimeSlot.setDateToLowerLimitOfHour(cal.getTime()));
            TimeSlot.EVENING_START_DATE = cal.getTime();
        }
        return TimeSlot.EVENING_START_DATE;
    }

    public static Date getEveningEndDate() {
        if(TimeSlot.EVENING_END_DATE == null) {
            Calendar cal = TimeSlot.cal;
            cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_END_HOUR - 1);
            cal.setTime(TimeSlot.setDateToUpperLimitOfHour(cal.getTime()));
            TimeSlot.EVENING_END_DATE = cal.getTime();
        }
        return TimeSlot.EVENING_END_DATE;
    }

    private static Date setDateToUpperLimitOfHour(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        return cal.getTime();
    }

    private static Date setDateToLowerLimitOfHour(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Time Slot");
        switch (timeslot) {
            case MORNING:
                result.insert(0, "Morning ");
                break;
            case AFTERNOON:
                result.insert(0, "Afternoon ");
                break;
            case EVENING:
                result.insert(0, "Evening ");
                break;
            case MISC:
                result.insert(0, "Miscellaneous ");
                break;
            default:
                result.insert(0, "Unknown ");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) obj;

        if (timeslot != timeSlot.timeslot) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return timeslot;
    }

    public static List<TimeSlot> getTimeslotListForDateRange(Date from, Date to) {
        List<TimeSlot> result = new ArrayList<TimeSlot>();
        if(TimeSlot.dateInMorning(from, to)) {
            result.add(TimeSlot.createMorningTimeSlot());
        }
        if(TimeSlot.dateInAfternoon(from, to)) {
            result.add(TimeSlot.createAfternoonTimeSlot());
        }
        if(TimeSlot.dateInEvening(from, to)) {
            result.add(TimeSlot.createEveningTimeSlot());
        }
        return result;
    }

    private static boolean dateInMorning(Date from, Date to) {
        boolean result = false;
        if (TimeSlot.inMorning(from) || TimeSlot.inMorning(to)) {
            result = true;
        } else if (from.before(getMorningStartDate()) && to.after(getMorningEndDate())) {
            result = true;
        }
        return result;
    }

    private static boolean dateInAfternoon(Date from, Date to) {
        boolean result = false;
        if (TimeSlot.inAfternoon(from) || TimeSlot.inAfternoon(to)) {
            result = true;
        } else if (from.before(getAfternoonStartDate()) && to.after(getAfternoonEndDate())) {
            result = true;
        }
        return result;
    }

    private static boolean dateInEvening(Date from, Date to) {
        boolean result = false;
        if (TimeSlot.inEvening(from) || TimeSlot.inEvening(to)) {
            result = true;
        } else if (from.before(getMorningStartDate()) && to.after(getMorningEndDate())) {
            result = true;
        }
        return result;
    }

    private static boolean inEvening(Date date) {
        Date eveningStart = TimeSlot.getEveningStartDate();
        Date eveningEnd = TimeSlot.getEveningEndDate();
        return TimeSlot.inTimeFrame(date, eveningStart, eveningEnd);
    }

    private static boolean inMorning(Date date) {
        Date morningStart = TimeSlot.getMorningStartDate();
        Date morningEnd = TimeSlot.getMorningEndDate();
        return TimeSlot.inTimeFrame(date, morningStart, morningEnd);
    }

    private static boolean inAfternoon(Date date) {
        Date afternoonStart = TimeSlot.getAfternoonStartDate();
        Date afternoonEnd = TimeSlot.getAfternoonEndDate();
        return TimeSlot.inTimeFrame(date, afternoonStart, afternoonEnd);
    }

    private static boolean inTimeFrame(Date dateToCheck, Date from, Date to) {
        if(dateToCheck.equals(from)
                ||(dateToCheck.after(from) && dateToCheck.before(to))
                || dateToCheck.equals(to)) {
            return true;
        }
        return false;
    }

}
