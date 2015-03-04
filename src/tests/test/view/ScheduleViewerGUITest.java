package test.view;

import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JFrameOperator;
import shawley.view.ScheduleView;

import static org.junit.Assert.fail;

/**
 * Created by laura on 04/03/15.
 */
public class ScheduleViewerGUITest {

    @Test
    public void testListComponentExists() {
        ScheduleView.start();
        String name = "Schedule";
        try {
            JFrameOperator window = new JFrameOperator(name);
        } catch (TimeoutExpiredException e) {
            fail("Frame with title "+name+" not opened. ");
        }
    }


}
