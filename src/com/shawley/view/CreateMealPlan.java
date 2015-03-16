package shawley.view;

import shawley.utilities.DateUtility;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class CreateMealPlan extends JFrame implements IMealCreator {

    public static CreateMealPlan start() {
        CreateMealPlan view = new CreateMealPlan();
        view.init();
        view.setVisible(true);
        return view;
    }

    void init() {
        this.setTitle("Create Meal Plan");
        Container pane = this.getContentPane();
        pane.setLayout(new FlowLayout());
        pane.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(450, 450);
        Date today = DateUtility.getMidnightForDate(new Date());
        Date lastDayOfWeek = DateUtility.getLastDayOfWeekForWeekContainingDate(today);
        SpinnerDateModel model = this.getModel(today);
        JSpinner from = new JSpinner(model);
        JSpinner to = new JSpinner(this.getModel(lastDayOfWeek));
        JSpinner.DateEditor de = new JSpinner.DateEditor(to, "d/M/yy");
        to.setEditor(de);
        pane.add(from);
        pane.add(to);
        JButton submit = new JButton("Submit");
        pane.add(submit);
    }

    private SpinnerDateModel getModel(Date baseDate) {
        Date nextMonth = DateUtility.getLastDayOfMonthAfterDate(baseDate);
        SpinnerDateModel model = new SpinnerDateModel();
        model.setStart(baseDate);
        model.setValue(baseDate);
        model.setEnd(nextMonth);
        return model;
    }

}
