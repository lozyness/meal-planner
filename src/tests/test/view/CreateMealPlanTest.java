package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JButtonOperator;
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
            this.getFromDateInput();
            this.getToDateInput();
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void checkInputComponentForFromDateIsForDates() {
        JSpinnerOperator spinner = getFromDateInput();
        assertEquals(SpinnerDateModel.class, spinner.getModel().getClass());
    }

    @Test
    public void checkDateInputForFromDateDefaultsToToday() {
        JSpinnerOperator spinner = getFromDateInput();
        Date defaultDate = (Date) spinner.getValue();
        Date today = DateUtility.getMidnightForDate(new Date());
        assertEquals(today, defaultDate);
    }

    @Test
    public void checkDateInputMaximumForFromDateInIsLastDayOfNextMonth() {
        JSpinnerOperator spinner = getFromDateInput();
        Date defaultDate = (Date) spinner.getValue();
        Date maxDate = (Date) spinner.getMaximum();
        assertEquals(DateUtility.getLastDayOfMonthAfterDate(defaultDate), maxDate);
    }

    @Test
    public void checkDateInputMinimumForFromDateIsTheDefaultDate() {
        JSpinnerOperator spinner = getFromDateInput();
        Date defaultDate = (Date) spinner.getValue();
        SpinnerDateModel model = (SpinnerDateModel) spinner.getModel();
        assertEquals(defaultDate, model.getStart());
    }

    @Test
    public void checkNextValueForFromDateIsTomorrow() {
        JSpinnerOperator spinner = getFromDateInput();
        Date today = DateUtility.getMidnightForDate(new Date());
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        Date tomorrow = cal.getTime();
        cal.add(Calendar.DATE, 10);
        Date nextDate = (Date) spinner.getNextValue();
        assertEquals(tomorrow, nextDate);
    }


    @Test
    public void checkInputComponentForToDateIsForDates() {
        JSpinnerOperator spinner = getToDateInput();
        assertEquals(SpinnerDateModel.class, spinner.getModel().getClass());
    }

    @Test
    public void checkToDateDefaultsToEndOfWeek() {
        Date expectedDate = DateUtility.getLastDayOfWeekForWeekContainingDate(new Date());
        JSpinnerOperator spinner = this.getToDateInput();
        assertEquals(expectedDate, spinner.getValue());
    }

    @Test
    public void checkDateInputMaximumForToDateInIsEndOfFirstWeekInTwoMonths() {
        JSpinnerOperator spinner = getToDateInput();
        Date defaultDate = (Date) spinner.getValue();
        Date maxDate = (Date) spinner.getMaximum();
        Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtility.getLastDayOfMonthAfterDate(defaultDate));
        Date end = DateUtility.getLastDayOfWeekForWeekContainingDate(cal.getTime());
        assertEquals(cal.getTime(), maxDate);
    }

    @Test
    public void checkDateInputMinimumForToDateIsTheDefaultDate() {
        JSpinnerOperator spinner = getToDateInput();
        Date defaultDate = (Date) spinner.getValue();
        SpinnerDateModel model = (SpinnerDateModel) spinner.getModel();
        assertEquals(defaultDate, model.getStart());
    }

    @Test
    public void checkNextValueForToDateIsFirstDayOfTwoWeeksAway() {
        JSpinnerOperator spinner = getToDateInput();
        Date today = new Date();
        Date nextWeek = DateUtility.getLastDayOfWeekForWeekContainingDate(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(nextWeek);
        cal.add(Calendar.DATE, 1);
        Date nextWeekPlusOneDay = cal.getTime();
        Date nextDate = (Date) spinner.getNextValue();
        assertEquals(nextWeekPlusOneDay, nextDate);
    }

    @Test
    public void checkFormatOfToDateIsDDMMYYY() {
        JSpinnerOperator spinner = this.getToDateInput();
        String actualDateFormat = "d/M/yy";
        String expectedDateFormat = ((JSpinner.DateEditor) spinner.getEditor()).getFormat().toPattern();
        assertEquals(expectedDateFormat, actualDateFormat);
    }

    @Test
    public void checkSubmitButtonExists() {
        try {
            JButtonOperator button = new JButtonOperator(this.window, "Submit");
        } catch (TimeoutExpiredException e) {
            fail();
        }
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