package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import shawley.Schedule;
import shawley.view.ViewSchedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by laura on 04/03/15.
 */
public class ViewScheduleTest {

    private JFrameOperator window;
    private ViewSchedule view;

    @Before
    public void setup() {
        this.view = ViewSchedule.start();
        this.window = new JFrameOperator("Schedule");
    }

    @Test
    public void testTableComponentExists() {
        try {
            JTableOperator tableOperator = new JTableOperator(window);
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void tableHasExpectedNumberOfRowsForOneDay() {
        Schedule schedule = new Schedule(1);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(1, tableOperator.getRowCount());
    }

    @Test
    public void tableHasExpectedNumberOfRowsForTwoDays() {
        Schedule schedule = new Schedule(2);
        view.setSchedule(schedule);
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(2, tableOperator.getRowCount());
    }

    @Test
    public void tableHasExpectedNumberOfColumns() {
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(4, tableOperator.getColumnCount());
    }

//    @Test
//    public void listItemsCanHaveTimeslotsSet() {
//        Schedule schedule = new Schedule(2);
//        JFrameOperator window = new JFrameOperator("Schedule");
//        IScheduleView view = (IScheduleView) window.getWindow();
//        view.setSchedule(schedule.getTimeSlots());
//        JTableOperator tableOperator = new JTableOperator(window);
//        assertEquals(6, tableOperator.getRowCount());
//        assertNotNull(tableOperator.getValueAt(0,0));
//        assertTrue(((TimeSlot) list.getModel().getElementAt(0)).isMorning());
//    }

//    @Test
//    public void listItemsCanHaveTimeslotsSetThroughController() {
//        Schedule schedule = new Schedule(2);
//        JFrameOperator window = new JFrameOperator("Schedule");
//        IScheduleView view = (IScheduleView) window.getWindow();
//        ViewScheduleController controller = new ViewScheduleController(schedule, view);
//        JTableOperator tableOperator = new JTableOperator(window);
////        tableOperator.getRowCount();
//        assertEquals(6, tableOperator.getRowCount());
////        assertNotNull(list.getModel().getElementAt(0));
//        assertTrue(((TimeSlot) list.getModel().getElementAt(0)).isMorning());
//    }

    @After
    public void tearDown() {
        try {
            view.dispose();
            window.dispose();
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }
    }

}
