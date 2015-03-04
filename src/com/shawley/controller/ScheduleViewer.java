package shawley.controller;

import shawley.Schedule;
import shawley.view.ScheduleView;

/**
 * Created by laura on 04/03/15.
 */
public class ScheduleViewer {

    public ScheduleViewer(Schedule schedule, ScheduleView view) {
        view.setSchedule(schedule);
    }
}
