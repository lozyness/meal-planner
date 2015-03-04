package test;

import org.easymock.EasyMock;
import org.junit.Test;
import shawley.Schedule;
import shawley.TimeSlot;
import shawley.controller.ScheduleViewer;
import shawley.view.ScheduleView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIPlannerTest {

    @Test
    public void testScheduleViewerDisplaysScheduleInAListFormat() throws Exception {
        Date date = new Date();
        Schedule schedule = new Schedule(new Date());
        Map<Date, List<TimeSlot>> timeslotsByDate = new HashMap<Date, List<TimeSlot>>();
        timeslotsByDate.put(date, schedule.getTimeSlots());
        ScheduleView view = EasyMock.createMock(ScheduleView.class);
        view.setSchedule(schedule);
        EasyMock.expectLastCall().once();
        EasyMock.replay(view);
        ScheduleViewer scheduleViewer = new ScheduleViewer(schedule, view);
        EasyMock.verify(view);
    }


}