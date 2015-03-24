package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.jemmy.operators.JMenuOperator;
import shawley.MealPlanner;
import shawley.view.MealPlanListView;
import shawley.view.ScheduleView;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MealPlannerTest {

    private MealPlanner application;
    private JFrameOperator window;

    private static final String MENU_NAME = "Menu";
    private static final String MENU_ITEM_NAME = "Preferences";

    @Before
    public void setup() {
        this.application = new MealPlanner();
        this.application.setupView();
        this.window = new JFrameOperator("Meal Planner");
    }

    @Test
    public void checkApplicationIsSizedCorrectly() {
        Dimension dim = this.window.getSize();
        assertEquals(new Dimension(750, 600), dim);
    }

    @Test
    public void checkApplicationHasCorrectLayout() {
        assertEquals(BorderLayout.class, this.window.getContentPane().getLayout().getClass());
    }

    @Test
    public void checkApplicationHasAMenuBar() {
        assertNotNull(this.getMenuBar());
    }

    @Test
    public void checkApplicationHasMenu() {
        assertNotNull(new JMenuOperator(this.window, MealPlannerTest.MENU_NAME));
    }

    @Test
    public void checkApplicationHasAPreferencesMenuItem() {
        JMenuOperator menu = new JMenuOperator(this.window, MealPlannerTest.MENU_NAME);
        JMenuItemOperator item =new JMenuItemOperator(menu.getItem(0));
        assertNotNull(item);
        assertEquals(MealPlannerTest.MENU_ITEM_NAME, item.getText());
    }

    @Test
    public void checkApplicationHasAPlanListArea() {
        String area = BorderLayout.WEST;
        Component panel = this.getComponentInArea(area);
        assertNotNull(panel);
        assertEquals(MealPlanListView.class, panel.getClass());
    }

    @Test
    public void checkApplicationHasAScheduleArea() {
        String area = BorderLayout.CENTER;
        Component panel = this.getComponentInArea(area);
        assertNotNull(panel);
        assertEquals(ScheduleView.class, panel.getClass());
    }

    @Test
    public void checkApplicationHasADetailArea() {
        String area = BorderLayout.EAST;
        Component panel = this.getComponentInArea(area);
        assertNotNull(panel);
        assertEquals(JPanel.class, panel.getClass());
    }

    @After
    public void tearDown() {
        this.window.dispose();
        this.application = null;
    }

    private JMenuBarOperator getMenuBar() {
        return new JMenuBarOperator(this.window);
    }

    private Component getComponentInArea(String area) {
        BorderLayout borderLayout = (BorderLayout) this.window.getContentPane().getLayout();
        return borderLayout.getLayoutComponent(area);
    }

}