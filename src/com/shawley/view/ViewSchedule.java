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
    private Schedule schedule;
    private JTable timeslots;
    private DefaultTableModel model = new ScheduleTableModel(0, 0);

    public static ViewSchedule start() {
        ViewSchedule view = new ViewSchedule();
        view.init();
        view.setVisible(true);
        return view;
    }

    public void init() {
        this.setTitle("Schedule");
        Container pane = this.getContentPane();
        pane.setLayout(new FlowLayout());
        pane.setSize(400, 400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(450, 450);
        this.model.addColumn("Date");
        this.model.addColumn("Morning");
        this.model.addColumn("Afternoon");
        this.model.addColumn("Evening");
        this.timeslots = new JTable(this.model);
        this.timeslots.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.timeslots.setRowSelectionAllowed(false);
        this.timeslots.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(this.timeslots, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane);
        this.doLayout();
    }

    @Override
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        this.addScheduleToTable(this.schedule);
    }

    private void addScheduleToTable(Schedule schedule) {
        Map<Date, List<TimeSlot>> timeslotsInSchedule = schedule.getAllTimeSlots();
        Iterator<Date> iter = timeslotsInSchedule.keySet().iterator();
        while (iter.hasNext()) {
            Date date = iter.next();
            this.addDateToTable(date, schedule);
        }
    }

    private void addDateToTable(Date date, Schedule schedule) {
        Vector vector = new Vector();
        vector.add(date);
        vector.add(schedule.hasMorningOnDate(date));
        vector.add(schedule.hasAfternoonOnDate(date));
        vector.add(schedule.hasEveningOnDate(date));
        this.model.addRow(vector);
    }
}
