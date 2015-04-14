package shawley.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MealPlanListView extends JPanel {

    public MealPlanListView() {
        this.setSize(200, 400);
        this.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(0, 0);
        model.addColumn("From");
        model.addColumn("To");
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Remove"));
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

}
