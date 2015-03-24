package shawley.controller;

import shawley.Schedule;
import shawley.utilities.DateUtility;
import shawley.view.IScheduleView;
import shawley.view.ScheduleView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ViewScheduleController {

    public ViewScheduleController(Schedule schedule, IScheduleView view) {
        view.setSchedule(schedule);
    }

    public static void main(String[] args) {
        Date from = DateUtility.getMidnightForDate(new Date());

        Calendar cal = new GregorianCalendar();
        cal.setTime(from);
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();

        Schedule schedule = new Schedule(from, to);

        ScheduleView view = new ScheduleView();
        new ViewScheduleController(schedule, view);
    }
}
