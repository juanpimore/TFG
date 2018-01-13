
// CLASE PARA MOSTRAR LOS DATOS DE UNA CELDA EN UN TOOLTIPTEXT //

package optimex;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TooltipJTable extends JTable {

@Override
public String getToolTipText(MouseEvent e) {
    java.awt.Point p = e.getPoint();
    int rowIndex = rowAtPoint(p);
    int colIndex = columnAtPoint(p);
    int realColIndex = convertColumnIndexToModel(colIndex);
    TableModel model = getModel();

    if(model.getColumnCount()>colIndex){
        if(model.getColumnClass(colIndex).equals(String.class) && rowIndex != -1)
            return (String) model.getValueAt(rowIndex, realColIndex);
        if(model.getColumnClass(colIndex).equals(Boolean.class)) {
            Boolean checked = (Boolean) model.getValueAt(rowIndex, realColIndex);
            if(checked)
                return "Checked";
            else
                return "Unchecked";
            }
    }
    return "";
}

@Override
protected JTableHeader createDefaultTableHeader() {
    return new JTableHeader(columnModel) {

        @Override
        public String getToolTipText(MouseEvent e) {
            java.awt.Point p = e.getPoint();
            int index = columnModel.getColumnIndexAtX(p.x);
            int realIndex = columnModel.getColumn(index).getModelIndex();
            return getModel().getColumnName(realIndex);
        }
    };
}

public int packColumn(int columna, int margen){
    TableModel modelo = this.getModel();
    DefaultTableColumnModel colModelo = (DefaultTableColumnModel)this.getColumnModel();
    TableColumn column = colModelo.getColumn(columna);
    int width = 0;
    TableCellRenderer renderer = column.getHeaderRenderer();
    if (renderer == null){
        renderer = this.getTableHeader().getDefaultRenderer();
    }
    Component comp = renderer.getTableCellRendererComponent(this, column.getHeaderValue(), 
            false, false, 0, 0);
    width = comp.getPreferredSize().width;
    for (int r=0; r<this.getRowCount();r++){
        renderer = this.getCellRenderer(r, columna);
        comp = renderer.getTableCellRendererComponent(this, this.getValueAt(r, columna), 
                false, false, r, columna);
        width = Math.max(width, comp.getPreferredSize().width);
    }
    width += 2*margen;
    column.setPreferredWidth(width);
    return width;
}

public int packColumns(int margen){
    int devuelto = 0;
    for (int c=0; c<this.getColumnCount(); c++){
        devuelto += packColumn(c, margen);
    }
    return devuelto;
}

public int heigth(){
    return this.getRowHeight()*(this.getRowCount())+20;
}

}