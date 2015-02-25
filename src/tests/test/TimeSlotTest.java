package test;

import org.junit.Test;
import shawley.TimeSlot;

import static org.junit.Assert.*;

public class TimeSlotTest {

    @Test
    public void canCreateMorningTimeslot() {
        TimeSlot timeslot = null;
        assertNotNull("Timeslot should have been instantiated", timeslot);
        assertTrue("Timeslot created is of wrong type", timeslot.isMorning());
    }

}