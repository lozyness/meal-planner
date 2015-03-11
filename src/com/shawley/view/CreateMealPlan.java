package shawley.view;

import shawley.utilities.DateUtility;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by laura on 10/03/15.
 */
public class CreateMealPlan extends JFrame implements IMealCreator {

    private Date maxDate;
    private SpinnerDateModel model;

    public static CreateMealPlan start() {
        CreateMealPlan view = new CreateMealPlan();
        view.init();
        view.setVisible(true);
        return view;
    }

    public void init() {
        this.setTitle("Create Meal Plan");

        Container pane = this.getContentPane();
        pane.setLayout(new FlowLayout());
        pane.setSize(400, 400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(450, 450);
        Date today = DateUtility.getMidnightForDate(new Date());
//        Calendar cal = new GregorianCalendar();
//        cal.setTime(today);
//        cal.add(Calendar.DATE, 1);
//        Date tomorrow = cal.getTime();
        this.model = new SpinnerDateModel(today, today, today, Calendar.DATE);
        JSpinner from = new JSpinner(this.model);
        JSpinner to = new JSpinner(this.model);
        pane.add(from);
        pane.add(to);
    }

    @Override
    public void setLatestDate(Date lastDate) {
        this.maxDate = DateUtility.getMidnightForDate(lastDate);
        this.model.setEnd(this.maxDate);
    }

}
