package test;

import org.junit.Test;
import shawley.TimeSlot;

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

}