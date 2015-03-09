package shawley.controller;

import shawley.Schedule;
import shawley.view.IScheduleView;
import shawley.view.ViewSchedule;

import java.util.Date;

/**
 * Created by laura on 04/03/15.
 */
public class ViewScheduleController {

    public ViewScheduleController(Schedule schedule, IScheduleView view) {
        view.setSchedule(schedule);
    }

    public static void main(String[] args) {
        ViewSchedule view = ViewSchedule.start();
        Schedule schedule = new Schedule(new Date());
        ViewScheduleController controller = new ViewScheduleController(schedule, view);
    }
}
