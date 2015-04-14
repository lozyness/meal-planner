package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import shawley.view.MealPlanListView;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        JButtonOperator button = new JButtonOperator(this.window, "Add");
    }

    @Test
    public void checkPlanListHasEditButton() {
        JButtonOperator button = new JButtonOperator(this.window, "Edit");
    }

    @Test
    public void checkPlanListHasRemoveButton() {
        JButtonOperator button = new JButtonOperator(this.window, "Remove");
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

    private JTable getTableComponent() {
        JScrollPane pane = (JScrollPane) this.getComponentForArea(this.getListPanel(), BorderLayout.CENTER);
        Component[] components = pane.getViewport().getComponents();
        JTable table = (JTable) components[0];
        return table;
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = (JPanel) this.getComponentForArea(this.getListPanel(), BorderLayout.SOUTH);
        return buttonPanel;
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