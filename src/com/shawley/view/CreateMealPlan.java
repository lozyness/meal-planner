package shawley.view;

import shawley.utilities.DateUtility;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class CreateMealPlan extends JFrame implements IMealCreator {

    private SpinnerDateModel model;

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
        this.model = new SpinnerDateModel(today, today, today, Calendar.DATE);
        JSpinner from = new JSpinner(this.model);
        JSpinner to = new JSpinner(this.model);
        pane.add(from);
        pane.add(to);
    }

    public void setLatestDate(Date lastDate) {
        this.model.setEnd(DateUtility.getMidnightForDate(lastDate));
    }

}
