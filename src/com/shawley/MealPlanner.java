package shawley;

import shawley.view.MealPlanListView;
import shawley.view.ScheduleView;

import javax.swing.*;
import java.awt.*;

public class MealPlanner {

    private JFrame frame;

    public MealPlanner() {
        this.frame = new JFrame("Meal Planner");
        this.init(frame);
        frame.setSize(1000, 600);
    }

    private void init(JFrame frame) {
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        this.addPanels(container);
        this.addMenuBar(frame);
    }

    private void addPanels(Container container) {
        container.add(new JPanel(), BorderLayout.EAST);
    }

    public void setupScheduleView(JPanel scheduleView) {
        this.frame.getContentPane().add(scheduleView, BorderLayout.CENTER);
    }

    public void setupPlanListView(JPanel planListView) {
        this.frame.getContentPane().add(planListView, BorderLayout.WEST);
    }

    private void addMenuBar(JFrame appView) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem preferenceMenuItem = new JMenuItem("Preferences");
        menu.add(preferenceMenuItem);
        menuBar.add(menu);
        appView.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        MealPlanner application = new MealPlanner();
        application.setupView();
    }

    private void addPlannerListView() {
        MealPlanListView view = new MealPlanListView();
        this.setupPlanListView(view);
    }

    private void addScheduleView() {
        ScheduleView view = new ScheduleView();
        this.setupScheduleView(view);
    }

    public void setupView() {
        this.addPlannerListView();
        this.addScheduleView();
        this.frame.setVisible(true);
    }
}