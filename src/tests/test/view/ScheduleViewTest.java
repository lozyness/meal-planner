package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import shawley.Schedule;
import shawley.utilities.DateUtility;
import shawley.view.ScheduleView;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ScheduleViewTest {

    private JFrameOperator window;
    private ScheduleView view;

    @Before
    public void setup() {
        String windowName = "Meal Planner";
        JFrame appView = new JFrame(windowName);
        this.view = new ScheduleView();
        appView.add(this.view);
        appView.setVisible(true);
        this.window = new JFrameOperator(windowName);
    }

    @Test
    public void testTableComponentExists() {
        try {
            assertNotNull(new JTableOperator(window));
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void tableHasExpectedNumberOfRowsForOneDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        Schedule schedule = new Schedule(from);
        this.view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(this.window);
        assertEquals(1, tableOperator.getRowCount());
    }

    @Test
    public void tableHasExpectedNumberOfRowsForTwoDays() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();
        Schedule schedule = new Schedule(from, to);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(2, tableOperator.getRowCount());
    }

    @Test
    public void tableHasExpectedDateForDay() {
        Date from = DateUtility.getMidnightForDate(new Date());

        Calendar cal = new GregorianCalendar();
        cal.setTime(from);
        Schedule schedule = new Schedule(from);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        Date date = (Date) tableOperator.getValueAt(0, 0);
        assertEquals(from, date);
    }

    @Test
    public void tableHasMorningTimeSlot() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        Schedule schedule = new Schedule(from);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        boolean morningTimeslot = (boolean) tableOperator.getValueAt(0, 1);
        assertEquals(true , morningTimeslot);
    }

    @Test
    public void tableHasAfternoonTimeSlot() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        Schedule schedule = new Schedule(from);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        boolean afternoonTimeslot = (boolean) tableOperator.getValueAt(0, 2);
        assertEquals(true , afternoonTimeslot);
    }

    @Test
    public void tableHasEveningTimeSlot() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        Schedule schedule = new Schedule(from);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        boolean eveningTimeslot = (boolean) tableOperator.getValueAt(0, 3);
        assertEquals(true , eveningTimeslot);
    }

    @Test
    public void tableHasExpectedDatesGivenTodayAndTomorrow() {
        Date from = DateUtility.getMidnightForDate(new Date());

        Calendar cal = new GregorianCalendar();
        cal.setTime(from);
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();
        Schedule schedule = new Schedule(from, to);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(2, tableOperator.getRowCount());
        Date today = (Date) tableOperator.getValueAt(0, 0);
        assertEquals(from, today);
        Date tomorrow = (Date) tableOperator.getValueAt(1, 0);
        assertEquals(to, tomorrow);
    }

    @Test
    public void tableHasExpectedDatesGivenTodayAndDayAfterNext() {
        Date from = DateUtility.getMidnightForDate(new Date());

        Calendar cal = new GregorianCalendar();
        cal.setTime(from);
        cal.add(Calendar.DATE, 1);
        Date mid = cal.getTime();

        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();

        Schedule schedule = new Schedule(from, to);
        view.setSchedule(schedule);

        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(3, tableOperator.getRowCount());
        Date firstDay = (Date) tableOperator.getValueAt(0,0);
        assertEquals(from, firstDay);
        Date middleDay = (Date) tableOperator.getValueAt(1,0);
        assertEquals(mid, middleDay);
        Date lastDay = (Date) tableOperator.getValueAt(2,0);
        assertEquals(to, lastDay);
    }

    @Test
    public void tableHasExpectedNumberOfColumns() {
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(4, tableOperator.getColumnCount());
    }

    @Test
    public void tableHasColumnNamedDate() {
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals("Date", tableOperator.getColumnName(0));
    }

    @Test
    public void tableHasColumnNamedMorning() {
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals("Morning", tableOperator.getColumnName(1));
    }

    @Test
    public void tableHasColumnNameAfternoon() {
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals("Afternoon", tableOperator.getColumnName(2));
    }

    @Test
    public void tableHasColumnNamedEvening() {
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals("Evening", tableOperator.getColumnName(3));
    }

    @After
    public void tearDown() {
        try {
            window.dispose();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
