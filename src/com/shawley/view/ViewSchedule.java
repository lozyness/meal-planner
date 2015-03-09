package shawley.view;

import shawley.Schedule;
import shawley.TimeSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by laura on 05/03/15.
 */
public class ViewSchedule extends JFrame implements IScheduleView{
    private Map<Date, List<TimeSlot>> scheduledTimeSlots;
    private JTable timeslots;
    private DefaultTableModel model = new DefaultTableModel(0, 0);

    public static ViewSchedule start() {
        ViewSchedule view = new ViewSchedule();
        view.init();
        view.setVisible(true);
        return view;
    }

    public void init() {
        this.setTitle("Schedule");
        this.getContentPane().setLayout(new FlowLayout());
        this.model.addColumn("Date");
        this.model.addColumn("Morning");
        this.model.addColumn("Afternoon");
        this.model.addColumn("Evening");
        this.timeslots = new JTable(this.model);
        this.getContentPane().add(this.timeslots);
//        this.add(this.timeslots);
    }

    @Override
    public void setSchedule(Schedule schedule) {
        this.scheduledTimeSlots = schedule.getAllTimeSlots();
        this.addScheduleToTable(this.scheduledTimeSlots);
    }

    private void addScheduleToTable(Map<Date, List<TimeSlot>> timeslotsInSchedule) {
        Iterator<Date> iter = timeslotsInSchedule.keySet().iterator();
        while (iter.hasNext()) {
            Date date = iter.next();
            List<TimeSlot> timesForDate = timeslotsInSchedule.get(date);
            this.addTimeslotToTable(date, timesForDate);
        }
    }

    private void addTimeslotToTable(Date date, List<TimeSlot> timeslot) {
        Vector vector = new Vector();
        vector.add(date);
        vector.addAll(timeslot);
        this.model.addRow(vector);
    }

    public JTable getTable() {
        return this.timeslots;
    }
}
