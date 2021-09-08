package main;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MyJTable extends JTable {

    public MyJTable(TableModel dm) {
        super(dm);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
