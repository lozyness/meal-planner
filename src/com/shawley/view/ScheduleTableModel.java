package shawley.view;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("WeakerAccess")
public class ScheduleTableModel extends DefaultTableModel {

//    private String dateColumnName = "Date";

    public ScheduleTableModel(@SuppressWarnings("SameParameterValue") int rows, @SuppressWarnings("SameParameterValue") int cols) {
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
