package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JSpinnerOperator;
import shawley.utilities.DateUtility;
import shawley.view.CreateMealPlan;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreateMealPlanTest {

    private CreateMealPlan view;
    private JFrameOperator window;

    @Before
    public void setup() {
        this.view = CreateMealPlan.start();
        this.window = new JFrameOperator("Create Meal Plan");
    }

    @Test
    public void checkInputComponentsExist() {
        try {
            JSpinnerOperator spinnerFrom = this.getFromDateInput();
            JSpinnerOperator spinnerTo = this.getToDateInput();
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void checkInputComponentIsForDates() {
        JSpinnerOperator spinner = getFromDateInput();
        assertEquals(new SpinnerDateModel().getClass(), spinner.getModel().getClass());
    }

    @Test
    public void checkDateInputDefaultsToToday() {
        JSpinnerOperator spinner = getFromDateInput();
        Date defaultDate = (Date) spinner.getValue();
        Date today = DateUtility.getMidnightForDate(new Date());
        assertEquals(today, defaultDate);
    }

    @Test
    public void checkDateInputMaximumInThreeMonths() {
        JSpinnerOperator spinner = getFromDateInput();
        Date today = DateUtility.getMidnightForDate(new Date());
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.MONTH, 4);
        cal.add(Calendar.DATE, (dayOfMonth)*-1);
        this.view.setLatestDate(cal.getTime());
        Date maxDate = (Date) spinner.getMaximum();
        assertEquals(cal.getTime(), maxDate);
    }

    @Test
    public void checkDateInputMinimumIsToday() {
        JSpinnerOperator spinner = getFromDateInput();
        Date today = DateUtility.getMidnightForDate(new Date());
        Date minDate = (Date) spinner.getMinimum();
        assertEquals(today, minDate);
    }

    @Test
    public void checkNextValueIsTomorrow() {
        JSpinnerOperator spinner = getFromDateInput();
        Date today = DateUtility.getMidnightForDate(new Date());
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        Date tomorrow = cal.getTime();
        cal.add(Calendar.DATE, 10);
        view.setLatestDate(cal.getTime());
        Date nextDate = (Date) spinner.getNextValue();
        assertEquals(tomorrow, nextDate);
    }

    private JSpinnerOperator getFromDateInput() {
        return new JSpinnerOperator(this.window, 0);
    }

    private JSpinnerOperator getToDateInput() {
        return new JSpinnerOperator(this.window, 1);
    }

    @After
    public void tearDown() {
        try{
            view.dispose();
            window.dispose();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}