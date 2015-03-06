package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JListOperator;
import shawley.Schedule;
import shawley.TimeSlot;
import shawley.controller.ViewScheduleController;
import shawley.view.IScheduleView;
import shawley.view.ViewSchedule;

import static org.junit.Assert.*;

/**
 * Created by laura on 04/03/15.
 */
public class ViewScheduleTest {

    private JFrameOperator window;

    @Before
    public void setup() {
        ViewSchedule.start();
    }

    @Test
    public void testFrameExists() {
        String name = "Schedule Viewer";
        try {
            JFrameOperator window = new JFrameOperator(name);
        } catch (TimeoutExpiredException e) {
            System.out.print(e.getStackTrace());
        }
    }

    @Test
    public void testListComponentExists() {
        JFrameOperator window = new JFrameOperator("Schedule");
        try {
            JListOperator list = new JListOperator(window);
        } catch (TimeoutExpiredException e) {
            System.out.print(e.getStackTrace());
        }
    }

    @Test
    public void listItemsCanHaveTimeslotsSet() {
        Schedule schedule = new Schedule(2);
        JFrameOperator window = new JFrameOperator("Schedule");
        IScheduleView view = (IScheduleView) window.getWindow();
        view.setSchedule(schedule.getTimeSlots());
        JListOperator list = new JListOperator(window);
        assertEquals(6, list.getModel().getSize());
        assertNotNull(list.getModel().getElementAt(0));
        assertTrue(((TimeSlot) list.getModel().getElementAt(0)).isMorning());
    }

    @Test
    public void listItemsCanHaveTimeslotsSetThroughController() {
        Schedule schedule = new Schedule(2);
        JFrameOperator window = new JFrameOperator("Schedule");
        IScheduleView view = (IScheduleView) window.getWindow();
        ViewScheduleController controller = new ViewScheduleController(schedule, view);
        JListOperator list = new JListOperator(window);
        assertEquals(6, list.getModel().getSize());
        assertNotNull(list.getModel().getElementAt(0));
        assertTrue(((TimeSlot) list.getModel().getElementAt(0)).isMorning());
    }

    @After
    public void tearDown() {
        try {
            window.dispose();
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }
    }

}
