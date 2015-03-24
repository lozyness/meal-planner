package shawley.view;

import javax.swing.*;
import java.awt.*;

public class MealPlanListView extends JPanel {

    public MealPlanListView() {
        this.setSize(400, 600);
        this.setLayout(new BorderLayout());
        this.add(new JList<Component>(), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Remove"));
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

}
