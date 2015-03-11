package shawley.controller;

import shawley.Schedule;
import shawley.view.IScheduleView;
import shawley.view.ViewSchedule;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ViewScheduleController {

    public ViewScheduleController(Schedule schedule, IScheduleView view) {
        view.setSchedule(schedule);
    }

    public static void main(String[] args) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date from = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();
        Schedule schedule = new Schedule(from, to);

        ViewSchedule view = ViewSchedule.start();
        new ViewScheduleController(schedule, view);
    }
}
