package optimex;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCelda extends DefaultTableCellRenderer
{
    private static int topeLineas = 10000;
    static boolean mayor = true;
    static int optimoMarcado = -1;
    static TooltipJTable tablaPorcentajes;
    int fila, columna;
    Color[][] colorCelda = new Color[topeLineas][20];
    static boolean[] esColumnaOptima;
    
    
    public void setAlgoritmo(boolean mayorQue, int optimus, TooltipJTable tablaPorcentajes){
        mayor = mayorQue;
        optimoMarcado = optimus;
        this.tablaPorcentajes = tablaPorcentajes;
        esColumnaOptima = new boolean[tablaPorcentajes.getModel().getColumnCount()];
        for (int i=0; i<esColumnaOptima.length; i++){
            esColumnaOptima[i] = false;
        }
    }
    
    public void setAlgoritmo(boolean mayorQue, int optimus){
        mayor = mayorQue;
        optimoMarcado = optimus;
    }
    
    public void setOptimo(int optimus){
        optimoMarcado = optimus;
    }
    
    public Color getColorCelda(int fil, int col){
        if (fil<topeLineas){
            return colorCelda[fil][col];
        } else {
            return Color.LIGHT_GRAY;
        }
    } 
    
    public Color getColorTextoCelda(int fil, int col){
        if (esColumnaOptima[col]){
            return Color.BLUE;
        } else {
            return Color.BLACK;
        }
    }
    
    private static boolean esElOptimoFila(int fila, int columna, JTable tabla){
        boolean devuelto = true;
        if (tabla.getModel().getValueAt(fila, columna).toString().contains("InstantiationException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("IllegalAccessException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("SecurityException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("IllegalArgumentException")
        //    || tabla.getModel().getValueAt(fila, columna).toString().contains("InvocationTargetException")){
            || tabla.getModel().getValueAt(fila, columna).toString().contains("Tiempo excedido")){
            devuelto = false;
        } else if (mayor && optimoMarcado==-1){
            for (int i=1; i<tabla.getModel().getColumnCount(); i++){
                if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                        <Parser.StringADouble(tabla.getModel().getValueAt(fila, i).toString())
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("InstantiationException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalAccessException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("SecurityException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalArgumentException")
                      //  && !tabla.getModel().getValueAt(fila, i).toString().contains("InvocationTargetException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("Tiempo excedido")
                        ){
                    devuelto = false;
                }
            }
        } else if (!mayor && optimoMarcado==-1){
            for (int i=1; i<tabla.getModel().getColumnCount(); i++){
                if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                        >Parser.StringADouble(tabla.getModel().getValueAt(fila, i).toString())
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("InstantiationException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalAccessException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("SecurityException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalArgumentException")
                  //      && !tabla.getModel().getValueAt(fila, i).toString().contains("InvocationTargetException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("Tiempo excedido")
                        ){
                    devuelto = false;
                }
            }
        } else if (optimoMarcado!=-1){
            if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                   !=Parser.StringADouble(tabla.getModel().getValueAt(fila, optimoMarcado+1).toString())
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InstantiationException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalAccessException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("SecurityException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalArgumentException")
                //   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InvocationTargetException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("Tiempo excedido")
                    ){
                 devuelto = false;
            }
        }
        return devuelto;
    }
    
    
    private static boolean esElSuboptimoFila(int fila, int columna, JTable tabla){
        boolean devuelto = false;
        if (tabla.getModel().getValueAt(fila, columna).toString().contains("InstantiationException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("IllegalAccessException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("SecurityException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("IllegalArgumentException")
       //     || tabla.getModel().getValueAt(fila, columna).toString().contains("InvocationTargetException")){
            || tabla.getModel().getValueAt(fila, columna).toString().contains("Tiempo excedido")){
            devuelto = false;
        } else if (mayor && optimoMarcado==-1){
            for (int i=1; i<tabla.getModel().getColumnCount(); i++){
                if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                        <Parser.StringADouble(tabla.getModel().getValueAt(fila, i).toString())
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("InstantiationException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalAccessException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("SecurityException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalArgumentException")
                   //     && !tabla.getModel().getValueAt(fila, i).toString().contains("InvocationTargetException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("Tiempo excedido")
                        ){
                    devuelto = true;
                }
            }
        } else if (!mayor && optimoMarcado==-1){
            for (int i=1; i<tabla.getModel().getColumnCount(); i++){
                if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                        >Parser.StringADouble(tabla.getModel().getValueAt(fila, i).toString())
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("InstantiationException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalAccessException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("SecurityException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("IllegalArgumentException")
                      //  && !tabla.getModel().getValueAt(fila, i).toString().contains("InvocationTargetException")
                        && !tabla.getModel().getValueAt(fila, i).toString().contains("Tiempo excedido")
                        ){
                    devuelto = true;
                }
            }
        } else if (mayor && optimoMarcado!=-1){
            if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                   <Parser.StringADouble(tabla.getModel().getValueAt(fila, optimoMarcado+1).toString())
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InstantiationException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalAccessException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("SecurityException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalArgumentException")
               //    && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InvocationTargetException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("Tiempo excedido")
                    ){
                 devuelto = true;
            }
        } else if (!mayor && optimoMarcado!=-1){
            if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                   >Parser.StringADouble(tabla.getModel().getValueAt(fila, optimoMarcado+1).toString())
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InstantiationException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalAccessException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("SecurityException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalArgumentException")
                //   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InvocationTargetException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("Tiempo excedido")
                    ){
                 devuelto = true;
            }
        }
        return devuelto;
    } 
    
    private static boolean esElSuperoptimoFila(int fila, int columna, JTable tabla){
        boolean devuelto = false;
        if (tabla.getModel().getValueAt(fila, columna).toString().contains("InstantiationException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("IllegalAccessException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("SecurityException")
            || tabla.getModel().getValueAt(fila, columna).toString().contains("IllegalArgumentException")
          //  || tabla.getModel().getValueAt(fila, columna).toString().contains("InvocationTargetException")){
            || tabla.getModel().getValueAt(fila, columna).toString().contains("Tiempo excedido")){
            devuelto = false;
        } else if (mayor && optimoMarcado!=-1){
            if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                   >Parser.StringADouble(tabla.getModel().getValueAt(fila, optimoMarcado+1).toString())
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InstantiationException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalAccessException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("SecurityException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalArgumentException")
                //   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InvocationTargetException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("Tiempo excedido")
                    ){
                 devuelto = true;
            }
        } else if (!mayor && optimoMarcado!=-1){
            if (Parser.StringADouble(tabla.getModel().getValueAt(fila, columna).toString())
                   <Parser.StringADouble(tabla.getModel().getValueAt(fila, optimoMarcado+1).toString())
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InstantiationException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalAccessException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("SecurityException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("IllegalArgumentException")
                 //  && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("InvocationTargetException")
                   && !tabla.getModel().getValueAt(fila, optimoMarcado).toString().contains("Tiempo excedido")
                    ){
                 devuelto = true;
            }
        }
        return devuelto;
    } 
    
    private static boolean esColumnaOptima(int columna){
        boolean devuelto = false;
        if (esColumnaOptima.length != tablaPorcentajes.getModel().getColumnCount()){
            esColumnaOptima = new boolean[tablaPorcentajes.getModel().getColumnCount()];
            for (int i=0; i<esColumnaOptima.length; i++){
                esColumnaOptima[i] = false;
            }
        }
        if (tablaPorcentajes.getModel().getRowCount()>2 && tablaPorcentajes.getModel().getColumnCount()>columna){
            if (tablaPorcentajes.getModel().getValueAt(2, columna).toString().contains("100,00 %")){
                esColumnaOptima[columna] = true;
                devuelto = true;
            }else{
                esColumnaOptima[columna] = false;
                devuelto = false;
            }
        }
        return devuelto;
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if( value instanceof String ){
            
            int columnas = table.getColumnCount();
            String amount = (String) value;
            
            // ################### FILA SELECCIONADA ####################
            
            if (isSelected){
                cell.setBackground(Color.BLUE);
                cell.setForeground(Color.WHITE);
                
            // ##################### COLUMNA OPTIMA #####################
                
            }else if (esColumnaOptima(column)){
                cell.setBackground(Color.GRAY);
                cell.setForeground(Color.CYAN);
                if (row<topeLineas){
                    colorCelda[row][column] = Color.GRAY;
                }
                
            // ################### OPTIMO EN LA FILA ####################
                
            }else if (column > 0 && esElOptimoFila(row, column, table)){    
                cell.setBackground(Color.GRAY);
                cell.setForeground(Color.WHITE);
                if (row<topeLineas){
                    colorCelda[row][column] = Color.GRAY;
                }
                
            // ################ SUBOPTIMO EN LA FILA ####################
                
            }else if (column > 0 && esElSuboptimoFila(row, column, table)){    
                cell.setBackground(new Color(225, 240, 225));
                cell.setForeground(Color.BLACK);
                if (row<topeLineas){
                    colorCelda[row][column] = Color.LIGHT_GRAY;
                }
                
            // ################ SUPEROPTIMO EN LA FILA ##################
                
            }else if (column > 0 && esElSuperoptimoFila(row, column, table)){    
                cell.setBackground(new Color(223,109,109)); // antes ROJO claro (240,225,225)
                cell.setForeground(Color.BLACK);
                if (row<topeLineas){
                    colorCelda[row][column] = Color.LIGHT_GRAY;
                }
                
            // #################### EL RESTO DE CELDAS ##################
                
            }else{    
                cell.setBackground(new Color(240, 240, 240));
                cell.setForeground(Color.BLACK);
                if (row<topeLineas){
                    colorCelda[row][column] = Color.LIGHT_GRAY;
                }
            }
          
        }
        return cell;
    }
}
