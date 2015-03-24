package shawley.view;

import shawley.Schedule;
import shawley.TimeSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ScheduleView extends JPanel implements IScheduleView{
    private JTable timeslots;
    private final DefaultTableModel model = new ScheduleTableModel(0, 0);

    public ScheduleView() {
        this.init();
        this.setVisible(true);
    }

    void init() {
        this.setLayout(new FlowLayout());
        this.setSize(400, 400);
        this.model.addColumn("Date");
        this.model.addColumn("Morning");
        this.model.addColumn("Afternoon");
        this.model.addColumn("Evening");
        this.timeslots = new JTable(this.model);
        this.timeslots.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.timeslots.setRowSelectionAllowed(false);
        this.timeslots.setAutoCreateRowSorter(true);
        this.timeslots.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(this.timeslots, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
    }

    @Override
    public void setSchedule(Schedule schedule) {
        this.addScheduleToTable(schedule);
    }

    private void addScheduleToTable(Schedule schedule) {
        Map<Date, List<TimeSlot>> timeslotsInSchedule = schedule.getAllTimeSlots();
        for (Date date : timeslotsInSchedule.keySet()) {
            this.addDateToTable(date, schedule);
        }
        List <RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        this.timeslots.getRowSorter().setSortKeys(sortKeys);
    }

    private void addDateToTable(Date date, Schedule schedule) {
        Vector<Object> vector = new Vector<>();
        vector.add(date);
        vector.add(schedule.hasMorningOnDate(date));
        vector.add(schedule.hasAfternoonOnDate(date));
        vector.add(schedule.hasEveningOnDate(date));
        this.model.addRow(vector);
    }
}
