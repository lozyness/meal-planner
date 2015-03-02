package test;

import org.junit.Test;
import shawley.TimeSlot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class TimeSlotTest {

    private TimeSlot timeslot;

    @Test
    public void canCreateMorningTimeslot() {
        this.timeslot = TimeSlot.createMorningTimeSlot();
        assertNotNull("Timeslot should have been instantiated", this.timeslot);
        assertTrue("Timeslot created is of wrong type", this.timeslot.isMorning());
        assertFalse("Timeslot created is of wrong type (afternoon)", this.timeslot.isAfternoon());
        assertFalse("Timeslot created is of wrong type (evening)", this.timeslot.isEvening());
        assertFalse("Timeslot created is of wrong type (misc)", this.timeslot.isMisc());
    }

    @Test
    public void canCreateAfternoonTimeslot() {
        this.timeslot = TimeSlot.createAfternoonTimeSlot();
        assertNotNull("Timeslot should have been instantiated", this.timeslot);
        assertTrue("Timeslot created is of wrong type", this.timeslot.isAfternoon());
        assertFalse("Timeslot created is of wrong type", this.timeslot.isMorning());
        assertFalse("Timeslot created is of wrong type (evening)", this.timeslot.isEvening());
        assertFalse("Timeslot created is of wrong type (misc)", this.timeslot.isMisc());
    }

    @Test
    public void canCreateEveningTimeslot() {
        this.timeslot = TimeSlot.createEveningTimeSlot();
        assertNotNull("Timeslot should have been instantiated", this.timeslot);
        assertTrue("Timeslot created is of wrong type (evening)", this.timeslot.isEvening());
        assertFalse("Timeslot created is of wrong type", this.timeslot.isMorning());
        assertFalse("Timeslot created is of wrong type", this.timeslot.isAfternoon());
        assertFalse("Timeslot created is of wrong type (misc)", this.timeslot.isMisc());
    }

    @Test
    public void canCreateMiscTimeslot() {
        this.timeslot = TimeSlot.createMiscTimeSlot();
        assertNotNull("Timeslot should have been instantiated", this.timeslot);
        assertTrue("Timeslot created is of wrong type (misc)", this.timeslot.isMisc());
        assertFalse("Timeslot created is of wrong type (evening)", this.timeslot.isEvening());
        assertFalse("Timeslot created is of wrong type", this.timeslot.isMorning());
        assertFalse("Timeslot created is of wrong type", this.timeslot.isAfternoon());
    }

    @Test
    public void toStringReturnsReadableAndAccurateStatement() {
        assertEquals("Morning Time Slot", TimeSlot.createMorningTimeSlot().toString());
        assertEquals("Afternoon Time Slot", TimeSlot.createAfternoonTimeSlot().toString());
        assertEquals("Evening Time Slot", TimeSlot.createEveningTimeSlot().toString());
        assertEquals("Miscellaneous Time Slot", TimeSlot.createMiscTimeSlot().toString());
    }

    @Test
    public void checkMorningStartDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 6);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Date morningStartDate = TimeSlot.getMorningStartDate();
        assertEquals("Should start at 05:00:00:00", date, morningStartDate);
    }

    @Test
    public void checkMorningEndDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date date = cal.getTime();
        Date morningEndDate = TimeSlot.getMorningEndDate();
        assertEquals("Should start at 10:59:59:99", date, morningEndDate);
    }

    @Test
    public void checkAfternoonStartDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Date afternoonStartDate = TimeSlot.getAfternoonStartDate();
        assertEquals("Should start at 11:00:00:00", date, afternoonStartDate);
    }

    @Test
    public void checkAfternoonEndDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 16);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date date = cal.getTime();
        Date afternoonEndDate = TimeSlot.getAfternoonEndDate();
        assertEquals("Should start at 15:59:59:59", date, afternoonEndDate);
    }

    @Test
    public void checkEveningStartDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Date eveningStartDate = TimeSlot.getEveningStartDate();
        assertEquals("Should start at 16:00:00:00", date, eveningStartDate);
    }

    @Test
    public void checkEveningEndDate() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 22);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date date = cal.getTime();
        Date eveningEndDate = TimeSlot.getEveningEndDate();
        assertEquals("Should start at 21:59:59:59", date, eveningEndDate);
    }

    @Test
    public void checkOneTimeslotGeneratedForMorningDateRange() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR - 1);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = TimeSlot.getMorningEndDate();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertEquals(1, timeslots.size());
        assertTrue(timeslots.get(0).isMorning());
    }

    @Test
    public void checkOneTimeslotGeneratedForMorningDateRangeWithToDateInMorning() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR-3);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR - 1);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = TimeSlot.getMorningEndDate();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertEquals(1, timeslots.size());
        assertTrue(timeslots.get(0).isMorning());
    }

    @Test
    public void checkOneTimeslotGeneratedForAfternoonDateRange() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR - 1);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = cal.getTime();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertEquals(1, timeslots.size());
        assertTrue(timeslots.get(0).isAfternoon());
    }

    @Test
    public void checkOneTimeslotGeneratedForEveningDateRange() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_END_HOUR);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = cal.getTime();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertEquals(1, timeslots.size());
        assertTrue(timeslots.get(0).isEvening());
    }

    @Test
    public void checkTwoTimeslotsGeneratedForMorningToAfternoonDateRange() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR - 1);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = cal.getTime();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertEquals(2, timeslots.size());
        assertTrue(timeslots.contains(TimeSlot.createMorningTimeSlot()));
        assertTrue(timeslots.contains(TimeSlot.createAfternoonTimeSlot()));
    }

    @Test
    public void checkTwoTimeslotsGeneratedForAfternoonToEveningDateRange() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.AFTERNOON_START_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = cal.getTime();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertEquals(2, timeslots.size());
        assertTrue(timeslots.contains(TimeSlot.createAfternoonTimeSlot()));
        assertTrue(timeslots.contains(TimeSlot.createEveningTimeSlot()));
    }

    @Test
    public void checkThreeTimeslotsGeneratedForMorningToEveningDateRange() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.MORNING_START_HOUR);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, TimeSlot.EVENING_START_HOUR);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);
        Date to = cal.getTime();
        List<TimeSlot> timeslots = TimeSlot.getTimeslotListForDateRange(from, to);
        assertTrue("Should have morning timeslot", timeslots.contains(TimeSlot.createMorningTimeSlot()));
        assertTrue("Should have afternoon timeslot",timeslots.contains(TimeSlot.createAfternoonTimeSlot()));
        assertTrue("Should have evening timeslot",timeslots.contains(TimeSlot.createEveningTimeSlot()));
    }

}