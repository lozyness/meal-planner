package shawley.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MealPlanListView extends JPanel {

    private final DefaultTableModel model = new DefaultTableModel(0, 0);

    public MealPlanListView() {
        this.setSize(200, 400);
        this.setLayout(new BorderLayout());
        this.model.addColumn("From");
        this.model.addColumn("To");
        JTable table = new JTable(this.model);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Remove"));
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

}
