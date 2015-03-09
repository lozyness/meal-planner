package test.view;

import org.easymock.EasyMock;
import org.junit.Test;
import shawley.Schedule;
import shawley.TimeSlot;
import shawley.controller.ViewScheduleController;
import shawley.view.IScheduleView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewScheduleControllerTest {

    @Test
    public void testCreatingScheduleViewerControllerSetsUpViewWithTimeslotDataToDisplay() throws Exception {
        Date date = new Date();
        Schedule schedule = new Schedule(new Date());
        Map<Date, List<TimeSlot>> timeslotsByDate = new HashMap<Date, List<TimeSlot>>();
        timeslotsByDate.put(date, schedule.getTimeSlots());
        IScheduleView view = EasyMock.createMock(IScheduleView.class);
        view.setSchedule(schedule);
        EasyMock.expectLastCall().once();
        EasyMock.replay(view);
        ViewScheduleController scheduleViewer = new ViewScheduleController(schedule, view);
        EasyMock.verify(view);
    }


}