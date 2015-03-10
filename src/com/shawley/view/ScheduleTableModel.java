package shawley.view;

import javax.swing.table.DefaultTableModel;

/**
 * Created by laura on 10/03/15.
 */
public class ScheduleTableModel extends DefaultTableModel {

//    private String dateColumnName = "Date";

    public ScheduleTableModel(int rows, int cols) {
        super(rows, cols);
    }


    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return false;
    }

}
