package shawley.view;

import shawley.TimeSlot;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by laura on 05/03/15.
 */
public class ViewSchedule extends JFrame implements IScheduleView{
    private List<TimeSlot> scheduledTimeSlots;
    private JList timeslotList;

    private static ViewSchedule view;

    public static void start() {
        ViewSchedule.view = new ViewSchedule();
        view.init();
        view.setVisible(true);
    }

    public void init() {
        view.setTitle("Schedule Viewer");
        view.getContentPane().setLayout(new FlowLayout());
        this.timeslotList = new JList(new Object[1]);
        this.add(this.timeslotList);
    }

    @Override
    public void setSchedule(List<TimeSlot> timeslotsInSchedule) {
        this.scheduledTimeSlots = timeslotsInSchedule;
        this.timeslotList.setListData(timeslotsInSchedule.toArray());
    }

    public static IScheduleView getScheduleViewWindow() {
        return ViewSchedule.view;
    }
}
