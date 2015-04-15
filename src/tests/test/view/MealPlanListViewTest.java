package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import shawley.MealPlan;
import shawley.view.MealPlanListView;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class MealPlanListViewTest {

    private JFrameOperator window;
    private MealPlanListView view;

    @Before
    public void setup() {
        String windowName = "Meal Planner";
        JFrame appView = new JFrame(windowName);
        this.view = new MealPlanListView();
        appView.add(view);
        appView.setVisible(true);
        this.window = new JFrameOperator(windowName);
    }

    @Test
    public void checkPlanListHasButtonPanel() {
        assertNotNull(this.getButtonPanel());
    }

    @Test
    public void checkPlanListHasAddButton() {
        try {
            new JButtonOperator(this.window, "Add");
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void checkPlanListHasEditButton() {
        try {
            new JButtonOperator(this.window, "Edit");
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void checkPlanListHasRemoveButton() {
        try {
            new JButtonOperator(this.window, "Remove");
        } catch (TimeoutExpiredException e) {
            fail();
        }
    }

    @Test
    public void checkButtonPanelHoldsAddRemoveAndEditButtonsInOrder() {
        String[] expectedButtons = {"Add", "Edit", "Remove"};
        JPanel buttonPanel = this.getButtonPanel();
        Component[] components = buttonPanel.getComponents();
        assertEquals("Number of buttons not as expected", 3, components.length);
        for(int i=0; i<components.length; i++) {
            assertEquals(JButton.class, components[i].getClass());
            JButton button = (JButton) components[i];
            assertEquals(expectedButtons[i], button.getText());
        }
    }

    @Test
    public void checkTableComponentExists() {
        assertEquals(JTable.class, this.getTableComponent().getClass());
    }

    @Test
    public void checkTableComponentHasFromColumn() {
        JTableOperator table = new JTableOperator(this.window);
        assertEquals("From", table.getColumnName(0));
    }

    @Test
    public void checkTableComponentHasToColumn() {
        JTableOperator table = new JTableOperator(this.window);
        assertEquals("To", table.getColumnName(1));
    }

    @Test
    public void checkTableHasExpectedNumberOfRowsForOnePlan() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();
        List<MealPlan> planList = new ArrayList<>();
        planList.add(MealPlan.createMealPlanForDateRange(from, to));
        view.setMealPlanList(planList);
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(1, tableOperator.getRowCount());
    }

    @Test
    public void checkTableHasExpectedNumberOfRowsForTwoPlans() {
        List<MealPlan> planList = new ArrayList<MealPlan>();
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date from = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();
        MealPlan planOne = MealPlan.createMealPlanForDateRange(from, to);
        planList.add(planOne);
        cal.add(Calendar.DATE, 1);
        from = cal.getTime();
        cal.add(Calendar.DATE, 1);
        to = cal.getTime();
        MealPlan planTwo = MealPlan.createMealPlanForDateRange(from, to);
        planList.add(planTwo);
        view.setMealPlanList(planList);
        JTableOperator tableOperator = new JTableOperator(window);
        assertEquals(2, tableOperator.getRowCount());
    }

    private JTable getTableComponent() {
        JScrollPane pane = (JScrollPane) this.getComponentForArea(this.getListPanel(), BorderLayout.CENTER);
        Component[] components = pane.getViewport().getComponents();
        return (JTable) components[0];
    }

    private JPanel getButtonPanel() {
        return (JPanel) this.getComponentForArea(this.getListPanel(), BorderLayout.SOUTH);
    }

    private JPanel getListPanel() {
        return this.view;
    }

    private Component getComponentForArea(Container container, String area) {
        BorderLayout borderLayout = (BorderLayout) container.getLayout();
        return borderLayout.getLayoutComponent(area);
    }

    @After
    public void tearDown() {
        this.window.dispose();
    }

}