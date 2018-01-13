package optimex;

import javax.swing.table.*;
import javax.swing.event.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import java.awt.Color;

public class ModeloTabla implements TableModel
{
    private static String mensaje1 = "Si quieres borrar, has de seleccionar las filas primero",
            mensaje2 = "¿Quieres borrar el dato ",
            mensaje3 = "¿Quieres borrar todas las filas seleccionadas?",
            mensaje4 = "Borrado",
            mensaje5 = "El dato ya existe o tiene un formato inválido",
            mensaje6 = "Tabla de datos";
    private RenderCelda renderer;
    private TableCellRenderer renderDefault;
    private Boolean coloreada = false;
    private static GestorEventosRollOver gestor;
    private static String[] botones = {"Aceptar","Cancelar"};
    private static String[] boton = {"Aceptar"};
    public int id = 1; // Identificador de fila
    
    public void ponerEspanol(){
        mensaje1 = "Si quieres borrar, has de seleccionar las filas primero";
        mensaje2 = "¿Quieres borrar el dato ";
        mensaje3 = "¿Quieres borrar todas las filas seleccionadas?";
        mensaje4 = "Borrado";
        botones[0] = "Aceptar";
        botones[1] = "Cancelar";
        boton[0] = "Aceptar";
    }
    
    public void ponerIngles(){
        mensaje1 = "If you want to delete, you must first select rows";
        mensaje2 = "Do you want to delete the data ";
        mensaje3 = "You want to delete all selected rows?";
        mensaje4 = "Erased";
        botones[0] = "Accept";
        botones[1] = "Cancel";
        boton[0] = "Accept";
    }
    
    public int getColumnCount() {
        return numeroColumnas;
    }
    
    
    public int getRowCount() {
        return datos.size();
    }
    
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fila aux;
        if (rowIndex<getRowCount() && columnIndex<getColumnCount()){
            aux = (Fila)(datos.get(rowIndex));
            return aux.dameCampoI(columnIndex);
        }else{
            return null;
        }
    }
    
    public void modificarOcultoAt(int rowIndex, String newOculto){
        if (conOcultos) {
            datosOcultos.set(rowIndex, newOculto);
        }
    }
    
    public void modificarOculto1At(int rowIndex, String newOculto){
        if (conOcultos2) {
            datosOcultos.set(rowIndex, newOculto);
        }
    }
    
    public void modificarOculto2At(int rowIndex, Integer newOculto){
        if (conOcultos2) {
            indicesOcultos.set(rowIndex, newOculto);
        }
    }
    
    public String getOcultoAt(int rowIndex) {
        return (String)datosOcultos.get(rowIndex);
    }
    
    public String getOculto1At(int rowIndex) {
        return (String)datosOcultos.get(rowIndex);
    }
    
    public Integer getOculto2At(int rowIndex) {
        return (Integer)indicesOcultos.get(rowIndex);
    }
    
    public String getFilaImprimible(int rowIndex) {
        Fila aux;
        String retorno = "";
        aux = (Fila)(datos.get(rowIndex));
        if (rowIndex<=this.getRowCount()){
            for (int i=1; i<aux.dameNumeroCampos(); i++){ //test03
                if (retorno == ""){
                    retorno = retorno+aux.dameCampoI(i);
                }else{
                    retorno = retorno+",  "+aux.dameCampoI(i);
                }
            }
            return retorno;
        }else{
            return null;
        }
    }
    
    public void borraFila (int fila)
    {
        datos.remove(fila);
        if (conOcultos){
             datosOcultos.remove(fila);
        }
        if (conOcultos2){
             datosOcultos.remove(fila);
             indicesOcultos.remove(fila);
        }
        TableModelEvent evento = new TableModelEvent (this, fila, fila, 
            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        avisaSuscriptores (evento);
    }
    
    public void reseteaTabla()
    {
        int filas = getRowCount();
        for (int i=filas; i>0; i--){
            datos.remove(i-1);
            if (conOcultos){
                 datosOcultos.remove(i-1);
            }
            if (conOcultos2){
                 datosOcultos.remove(i-1);
                 indicesOcultos.remove(i-1);
            }
            TableModelEvent evento = new TableModelEvent (this, i-1, i-1, 
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
            avisaSuscriptores (evento);
        }
    }
    
    public void resetTotal()
    {
        int filas = getRowCount();
        for (int i=filas; i>0; i--){
            datos.remove(i-1);
            if (conOcultos){
                 datosOcultos.remove(i-1);
            }
            if (conOcultos2){
                 datosOcultos.remove(i-1);
                 indicesOcultos.remove(i-1);
            }
            TableModelEvent evento = new TableModelEvent (this, i-1, i-1, 
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
            avisaSuscriptores (evento);
        }
        String[] nombres = {"",""};
        this.setColumnNames(nombres);
        this.inicializada = false;
    }
    
    public void borraFilaSeleccionada (Editor panel_codigo)
    {
        if (tabla.getSelectedRowCount() == 0){
            JLabel exito = new JLabel(mensaje1);
            JOptionPane.showOptionDialog(null, exito, mensaje4,
                JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(ModeloTabla.class.getResource("imagenes/borrar_sesion.gif")),
                boton, boton[0]);
        }
        if (tabla.getSelectedRowCount() == 1){
        	JLabel exito = new JLabel(mensaje2+this.getValueAt(tabla.getSelectedRow(), 0)+"?");
         //   JLabel exito = new JLabel(mensaje2+truncate(this.getFilaImprimible(tabla.getSelectedRow()),100)+"?");
            int opcion = JOptionPane.showOptionDialog(null, exito, mensaje4,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(ModeloTabla.class.getResource("imagenes/borrar_sesion.gif")),
                botones, botones[0]);
            if (opcion == JOptionPane.OK_OPTION){         //si elige que si
                      for (int i=tabla.getSelectedRowCount(); i>0; i--){
                            datos.remove(tabla.getSelectedRows()[i-1]);//se borra la fila
                            if (conOcultos){
                                datosOcultos.remove(tabla.getSelectedRows()[i-1]);
                            }
                            if (conOcultos2){
                                 datosOcultos.remove(tabla.getSelectedRows()[i-1]);
                                 indicesOcultos.remove(tabla.getSelectedRows()[i-1]);
                            }
                            TableModelEvent evento = new TableModelEvent (this, tabla.getSelectedRows()[i-1], tabla.getSelectedRows()[i-1], 
                            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
                            avisaSuscriptores (evento);
                      }
                      return;
            }else if (opcion == JOptionPane.CANCEL_OPTION){    //si elige cancelar
                        tabla.clearSelection();
                        return;                        //se cancela esta operacion
                    //en otro caso se continua con la operacion y no se borra
            }
        }
        if (tabla.getSelectedRowCount() > 1){
            JLabel exito = new JLabel(mensaje3);
            int opcion = JOptionPane.showOptionDialog(null, exito, mensaje4,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(ModeloTabla.class.getResource("imagenes/borrar_sesion.gif")),
                botones, botones[0]);
            if (opcion == JOptionPane.OK_OPTION){       //si elige que si
                      for (int i=tabla.getSelectedRowCount(); i>0; i--){
                            datos.remove(tabla.getSelectedRows()[i-1]);//se borra la fila
                            if (conOcultos){
                                datosOcultos.remove(tabla.getSelectedRows()[i-1]);
                            }
                            if (conOcultos2){
                                 datosOcultos.remove(tabla.getSelectedRows()[i-1]);
                                 indicesOcultos.remove(tabla.getSelectedRows()[i-1]);
                            }
                            TableModelEvent evento = new TableModelEvent (this, tabla.getSelectedRows()[i-1], tabla.getSelectedRows()[i-1], 
                            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
                            avisaSuscriptores (evento);
                      }
                      return;
            }else if (opcion == JOptionPane.CANCEL_OPTION){       //si elige cancelar
                      tabla.clearSelection();
                      return;                        //se cancela esta operacion
                    //en otro caso se continua con la operacion y no se borra
            }
        }
    }
    
    public int[] dameFilaSeleccionada(Editor panel_codigo){
    	int[] seleccion = null;

        if (tabla.getSelectedRowCount() == 0){
        //	System.out.println("No hay filas seleccionadas");
        }else if(tabla.getSelectedRowCount() > 1){
        	seleccion = new int[tabla.getSelectedRowCount()];
        	seleccion = tabla.getSelectedRows();
        //	System.out.println("Filas seleccionadas:" + tabla.getSelectedRowCount());  
        }else if(tabla.getSelectedRowCount() == 1){
        	seleccion = new int[1];
        	seleccion[0] = Integer.parseInt(this.getValueAt(tabla.getSelectedRow(), 0).toString());
        //	System.out.println("Fila seleccionada:" + this.getValueAt(tabla.getSelectedRow(), 0));
        }
        return seleccion;
    }
       
    // Método creado para que los datos introducidos por teclado, generados aleatoriamente o importados queden seleccionados directamente
    public void ponFilasSeleccionadas(int numero){ 
    	if(numero != 0){
    		int fin = tabla.getRowCount();
    		if(fin == numero){ //tabla vacía -> selecciono todas las filas
    			tabla.setRowSelectionInterval(0, numero - 1);
    		}else{ //tabla no vacía -> selecciono las últimas
    			int inicio = tabla.getRowCount() - numero;
    			tabla.setRowSelectionInterval(inicio, fin - 1);
    		}
    	}
    }
    
    public void actualizaId(){
    	id = 1;
    }
    
    public void anhadeFila (Fila nueva_fila)
    {
    //	System.out.println("ModeloTabla anhadeFila A");
        datos.add (nueva_fila);
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);
        avisaSuscriptores (evento);
    }
    
    public boolean anhadeFila (Fila nueva_fila, String oculto){
    //	System.out.println("\nModeloTabla anhadeFila B");
        conOcultos = true;
        
        //COMPROBACIÓN DE DUPLICIDADES
        boolean existe = PanelDatos.existeFila(nueva_fila);
        
        //VALIDACIÓN DE FORMATO 
        boolean filaCorrecta = esFilaCorrecta(nueva_fila);
       
        if(filaCorrecta == true){
        	if(existe != true){
        	//	System.out.println("******NO EXISTE"); 
        		
        		//Inserción de número de dato (id) en la tabla     		
        		nueva_fila.tomaCampoI(String.valueOf(id), 0);
        		id = id + 1;
        		datos.add (nueva_fila);
        		       		
        		TableModelEvent evento;
        		evento = new TableModelEvent (this, this.getRowCount()-1, this.getRowCount()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        		avisaSuscriptores (evento);
        		datosOcultos.add (oculto);
        	}else{
        	//	System.out.println("******EXISTE YA");
        	}
        }

        
        if((existe == false) && (filaCorrecta == true)){
        	return false;
        }else{
        	return true;
        }
    }
    
    public boolean esFilaCorrecta(Fila nueva_fila){
    	boolean resultado = true;
    	for(int o=1; o<nueva_fila.dameNumeroCampos();o++){
    		String campo = (String) nueva_fila.dameCampoI(o);    		
    		String nombreColumna = PanelDatos.modeloDatos.getColumnName(o);
    	    
    	  //Comprobación array:
    	    boolean array = nombreColumna.endsWith("[]");
    	    
    	    if(array){  //ARRAY   		       			 
        		//Comprobación tipo datos:
    	    	if(nombreColumna.equals("int[]")){
    	    		Pattern pat = Pattern.compile("\\{-?[0-9]{1,2147483647}[,0-9]{0,2147483647}\\}");
        			Matcher mat = pat.matcher(campo);
        			if(!mat.find()){     				           				
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}			
        		}else if(nombreColumna.equals("short[]")){
        			Pattern pat = Pattern.compile("\\{-?[0-9]{1,32767}\\}");
        			Matcher mat = pat.matcher(campo);
        			if (!mat.find()) {
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}
        		}else if(nombreColumna.equals("long[]")){
        			Pattern pat = Pattern.compile("\\{-?[0-9]{1,9223372036854775807}\\}");
        			Matcher mat = pat.matcher(campo);
        			if (!mat.find()) {
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}
        		}else if(nombreColumna.equals("double[]")){
        			Pattern pat = Pattern.compile("\\{-?[0-9.0-9]{1,333333333333333333333}[,0-9.0-9]*\\}");
        			Matcher mat = pat.matcher(campo);
        			if (!mat.find()) {
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}
        		}else if(nombreColumna.equals("float[]")){
        			Pattern pat = Pattern.compile("\\{-?[0-9.0-9]{1,333333333333333333333}[,0-9.0-9]*\\}");
        			Matcher mat = pat.matcher(campo);
        			if (!mat.find()) {        				          				
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}
        		}else if(nombreColumna.equals("boolean[]")){
        			Pattern pat = Pattern.compile("\\{(true|false){1,10000}(,true|,false){0,10000}\\}");
        			Matcher mat = pat.matcher(campo);
        			if (!mat.find()){
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}
        		}else if(nombreColumna.equals("char[]")){
        			Pattern pat = Pattern.compile("\\{[\\w]{1,1000000}[,\\w]{0,1000000}\\}", Pattern.UNICODE_CHARACTER_CLASS);
        			Matcher mat = pat.matcher(campo);
        			if (!mat.find()){
        				System.out.println("	*NO Válido");
        				resultado = false;
        				break;
        			}
        		}         		
    	    }else{
    	    	if(nombreColumna.equals("int")){
	    			Pattern pat = Pattern.compile("-?[0-9]{1,2147483647}");
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	}else if(nombreColumna.equals("short")){ 	
    	    		Pattern pat = Pattern.compile("-?[0-9.0-9]{1,32767}");
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	}else if(nombreColumna.equals("long")){ 	
    	    		Pattern pat = Pattern.compile("-?[0-9.0-9]{1,9223372036854775807}");
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	}else if(nombreColumna.equals("double")){ 	
    	    		Pattern pat = Pattern.compile("-?[0-9.0-9]{1,333333333333333333333}"); //double y float igual
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	}else if(nombreColumna.equals("float")){ 	
    	    		Pattern pat = Pattern.compile("-?[0-9.0-9]{1,333333333333333333333}");//double y float igual
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	}else if(nombreColumna.equals("boolean")){ 	
    	    		Pattern pat = Pattern.compile("true|false");
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	}else if(nombreColumna.equals("char")){ 	
    	    		Pattern pat = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
    				Matcher mat = pat.matcher(campo);
    				if (!mat.find()) {
    				    System.out.println("	*No Válido");
        				resultado = false;
        				break;
    				}
    	    	} 	    	
    	    }
    	}
    	return resultado;
    }
    
    public boolean comprobarTipo(String arrayDatos, String nombreColumna){
    	return true;
    }
    
    public static int contarCaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
}
    
    public void anhadeFila (Fila nueva_fila, String oculto, Integer indiceOculto)
    {
    //	System.out.println("ModeloTabla anhadeFila C");
        conOcultos2 = true;
        datos.add (nueva_fila);
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);
        avisaSuscriptores (evento);
        datosOcultos.add (oculto);
        indicesOcultos.add (indiceOculto);
    }
    
    public void addTableModelListener(TableModelListener l) {
        listeners.add (l);
    }
    
    
    public Class getColumnClass(int columnIndex) {
        if (columnIndex<this.getColumnCount()){
            try{
                return clases[columnIndex];
            }catch(NullPointerException e){
                return String.class;
            }
        }else{
            return null;
        }
    }
    
    public void setTable (JTable tabla){
        this.tabla = tabla;
    }
    
    //bea
    public JTable getTable(){
    	return tabla;
    }
    
    public void setColumnNames(String[] nombres){
                nombreColumnas=nombres;
                numeroColumnas=nombres.length;
                inicializada=true;
                TableModelEvent evento = new TableModelEvent (this,TableModelEvent.HEADER_ROW);
                avisaSuscriptores (evento);
    }
    
    public void setColumnClass(Class[] setclases){
                clases=setclases;
                TableModelEvent evento = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
                avisaSuscriptores (evento);
    }
    
    public boolean estaInicializadaTabla(){
        return inicializada;
    }
    
    public String getColumnName(int columnIndex) 
    {
        if (columnIndex<=numeroColumnas){
            return nombreColumnas[columnIndex];
        }else{
            return  null;
        }
    }
    
    public String[] getColumnNames() 
    {
        return nombreColumnas;
    }
    
    public void setCellEditable() {
    	editable = true;
    }
    
    public void setCellNonEditable() {
        editable = false;
    }
    
    public void setCellColor(boolean mayorQue, int optimus, TooltipJTable tablaPorcentajes) {
        coloreada = true;
        renderDefault = tabla.getDefaultRenderer(null);
        renderer = new RenderCelda();
        renderer.setAlgoritmo(mayorQue, optimus, tablaPorcentajes);
        try
        {
            tabla.setDefaultRenderer( Class.forName( "java.lang.String" ), renderer );
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
        TableModelEvent evento = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    public void setCellColor(boolean mayorQue, int optimus) {
        renderer.setAlgoritmo(mayorQue, optimus);
        TableModelEvent evento = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    public void setOptimoCellColor(int optimus){
        renderer.setOptimo(optimus);
        TableModelEvent evento = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    public void pararRender(){
        try
        {
            tabla.setDefaultRenderer( Class.forName( "java.lang.String" ), renderDefault );
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
        TableModelEvent evento = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    public void arrancarRender(){
        try
        {
            tabla.setDefaultRenderer( Class.forName( "java.lang.String" ), renderer );
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
        TableModelEvent evento = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    public Color getCellColor(int rowIndex, int columnIndex){
        if (coloreada){
            return renderer.getColorCelda(rowIndex, columnIndex);
        }else{
            return null;
        }
    }
    
    public Color getCellTextColor(int rowIndex, int columnIndex){
        if (coloreada){
            return renderer.getColorTextoCelda(rowIndex, columnIndex);
        }else{
            return null;
        }
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	String nombre = this.getColumnName(0);
    	if(nombre.equals("Num.")){ 	   	
    		switch (columnIndex) {
    			case 0:
    				return false;
    			default:
    				return true;
    		}
    	}
    	else return false;
    }
    
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
    
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) 
    {
    	boolean diferente = comprobarDiferente(aValue, rowIndex, columnIndex);
    	if(diferente == true){	
    		Fila aux;
    		aux = (Fila)(datos.get(rowIndex));
    		if ((columnIndex<=numeroColumnas)&(aValue.getClass()==clases[columnIndex])){
    			aux.tomaCampoI (aValue, columnIndex);
    			TableModelEvent evento = new TableModelEvent (this, rowIndex, rowIndex, 
    					columnIndex);
    			avisaSuscriptores (evento);
    		}
    	}
    }
  
    //Método que evalúa si en un intento de edición de un dato se introduce uno que ya existe en la tabla de datos
    public boolean comprobarDiferente(Object aValue, int rowIndex, int columnIndex) {  
    	boolean diferente = false;
    	int filaCoincidente = -1;
    	int contadorColumnasCoincidentes = 0;

    	for(int h=0;h<PanelDatos.modeloDatos.getRowCount();h++){
			if(h != rowIndex){
			//	System.out.println("fila= " + PanelDatos.modeloDatos.getValueAt(h, columnIndex));
				if(PanelDatos.modeloDatos.getValueAt(h, columnIndex).equals(aValue.toString())){
				//	System.out.println("existe en otra fila. compruebo resto de columnas");
					filaCoincidente = h;
					//Comprobación resto de columnas de la fila coincidente:
					for(int j=0; j< PanelDatos.modeloDatos.getColumnCount(); j++){
						if(j != columnIndex){
							if((PanelDatos.modeloDatos.getValueAt(rowIndex, j).equals(PanelDatos.modeloDatos.getValueAt(h, j)))){
							//	System.out.println("Coincide una columna");
								contadorColumnasCoincidentes = contadorColumnasCoincidentes + 1;
							//	System.out.println("contadorColumnasCoincidentes = " + contadorColumnasCoincidentes);
							//	System.out.println("PanelDatos.modeloDatos.getColumnCount()-1 --------- " + (PanelDatos.modeloDatos.getColumnCount()-1));
								if(contadorColumnasCoincidentes == (PanelDatos.modeloDatos.getColumnCount()-1)){
							//		System.out.println("Coinciden todas las columnas");
									JOptionPane.showMessageDialog(null, mensaje5, mensaje6, JOptionPane.WARNING_MESSAGE);  
									diferente = false;
									return diferente;
								}
							}else{
							//	System.out.println("No coinciden");
								diferente = true;					
							}
						}
					}
				}else{
    				diferente = true;
				}
			} 		
    	}
    	return diferente;
    }
    
    public void setRowAt(Fila fila, int rowIndex){
        for (int i=0; i<this.getColumnCount(); i++){
            this.setValueAt(fila.dameCampoI(i), rowIndex, i);
        }
    }
    
    public void InsertColumn(String nombreColumna, Class claseColumna){
        numeroColumnas++;
        
        String[] nuevoNombres;
        nuevoNombres = new String [numeroColumnas];
        nuevoNombres[0] = nombreColumna;
        int i,j;
        for (i=1; i<numeroColumnas; i++){
            nuevoNombres[i] = nombreColumnas[i-1];
        }              
        nombreColumnas = nuevoNombres;
        
        Class[] nuevoClases;
        nuevoClases = new Class [numeroColumnas];
        nuevoClases[0] = claseColumna;
        for (i=1; i<numeroColumnas; i++){
            nuevoClases[i] = clases[i-1];
        }
        clases = nuevoClases;
               
        Fila nuevaFila;
        Fila antiguaFila;
        int campos;
        Object[] objetos;
        int filas = this.getRowCount();
        for (i=0; i<filas; i++){
            antiguaFila = (Fila)(datos.get(i));
            campos = antiguaFila.dameNumeroCampos();
            objetos = new Object[campos+1];
            for (j=0; j<campos; j++){
                objetos[j] = antiguaFila.dameCampoI(j);
            }
            objetos[campos] = null;
            nuevaFila = new Fila(objetos);
            datos.add (nuevaFila);
        }
        for (i=0; i<filas; i++){
            datos.remove(0);
        }
        
        TableModelEvent evento = new TableModelEvent (this,TableModelEvent.HEADER_ROW);
        avisaSuscriptores (evento);
        TableModelEvent evento2 = new TableModelEvent (this,TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento2);
    }
    
    private void avisaSuscriptores (TableModelEvent evento)
    {
        int i;
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }
    
    // aviso al gestor de eventos de fila seleccionada para actualizar las otras tablas
    // ################################################################################
    public void escuchadorSeleccion(final GestorEventosRollOver gestor, TooltipJTable tabla){
        ListSelectionModel rowSM = tabla.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel lsm = (ListSelectionModel)e.getSource();

                if (lsm.isSelectionEmpty()) {
                    //no rows are selected
                } else {
                    int selectedRow = lsm.getMaxSelectionIndex();
                    gestor.avisoSeleccion(selectedRow);
                }
            }
        });
    }
    
    public String truncate(String s, int len) { 
        if (s == null) return null;
        return s.substring(0, Math.min(len, s.length()));
    }
    
    
    private LinkedList datos = new LinkedList();
    
    private LinkedList datosOcultos = new LinkedList();
    
    private LinkedList indicesOcultos = new LinkedList();
    
    private boolean conOcultos = false;
    
    private boolean conOcultos2 = false;
    
    private int numeroColumnas = 0;
    
    private String[] nombreColumnas = null;
    
    private Class[] clases;
    
    private LinkedList listeners = new LinkedList();
    
    private Boolean inicializada = false;
    
    private Boolean editable = false;
    
    private JTable tabla;



}

