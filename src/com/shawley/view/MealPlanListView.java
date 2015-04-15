package shawley.view;

import shawley.MealPlan;
import shawley.Schedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MealPlanListView extends JPanel {
    private DefaultTableModel model;

//    private List<MealPlan> mealPlanList = new ArrayList<MealPlan>();


    public MealPlanListView() {
        this.setSize(200, 400);
        this.setLayout(new BorderLayout());
        model = new DefaultTableModel(0, 0);
        model.addColumn("From");
        model.addColumn("To");
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Remove"));
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addMealPlan(MealPlan plan) {
        Schedule schedule = plan.getSchedule();
        this.addEntry(schedule.getStartDate(), schedule.getEndDate());
    }

    private void addEntry(Date from, Date to) {
        Vector<Object> vector = new Vector<>();
        vector.add(from);
        vector.add(to);
        this.model.addRow(vector);
    }

    public void setMealPlanList(List<MealPlan> planList) {
        Iterator<MealPlan> iter = planList.iterator();
        while (iter.hasNext()) {
            addMealPlan(iter.next());
        }
    }
}
