package test.controller;

import org.easymock.EasyMock;
import org.junit.Test;
import shawley.Schedule;
import shawley.controller.ViewScheduleController;
import shawley.view.IScheduleView;

import java.util.Date;

public class ViewScheduleControllerTest {

    @Test
    public void testCreatingScheduleViewerControllerSetsUpViewWithTimeslotDataToDisplay() throws Exception {
        Schedule schedule = new Schedule(new Date());
        IScheduleView view = EasyMock.createMock(IScheduleView.class);
        view.setSchedule(schedule);
        EasyMock.expectLastCall().once();
        EasyMock.replay(view);
        new ViewScheduleController(schedule, view);
        EasyMock.verify(view);
    }


}