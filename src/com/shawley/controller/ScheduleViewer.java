package shawley.controller;

import shawley.Schedule;
import shawley.view.IScheduleView;

/**
 * Created by laura on 04/03/15.
 */
public class ScheduleViewer {

    public ScheduleViewer(Schedule schedule, IScheduleView view) {
        view.setSchedule(schedule);
    }
}
