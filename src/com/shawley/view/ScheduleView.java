package shawley.view;

import shawley.Schedule;

import javax.swing.*;

/**
 * Created by laura on 04/03/15.
 */
public class ScheduleView extends JFrame implements IScheduleView{

    public ScheduleView() {
        super();
    }

    @Override
    public void setSchedule(Schedule schedule) {

    }

    public static void start() {
        ScheduleView view = new ScheduleView();
        view.setTitle("Schedule");
        view.setVisible(true);
    }
}
