package shawley.controller;

import shawley.Schedule;
import shawley.view.IScheduleView;
import shawley.view.ViewSchedule;

/**
 * Created by laura on 04/03/15.
 */
public class ViewScheduleController {

    public ViewScheduleController(Schedule schedule, IScheduleView view) {
        view.setSchedule(schedule.getTimeSlots());
    }

    public static void main(String[] args) {
        ViewSchedule.start();
        Schedule schedule = new Schedule(4);
        ViewScheduleController controller = new ViewScheduleController(schedule, ViewSchedule.getScheduleViewWindow());
    }
}
