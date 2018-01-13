package optimex;
import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.util.List;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

public class TXTImporter {
    private File file;
    private JTable table;
    private ModeloTabla modelo;
    private ControlTabla control;
    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
    private FileWriter fw = null;
    private PrintWriter pw = null;
    
    public TXTImporter(JTable table, ModeloTabla modelo, ControlTabla control, File file){
        this.file=file;
        this.table=table;
        this.modelo=modelo;
        this.control=control;
        this.archivo = file;
    }
    public Integer cargarTxt(){
    	System.out.println("cargarTxt");
        try{
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            StringTokenizer aux;

            if ((linea=br.readLine())!=null){//########### Tipos de la cabecera de la tabla ########
            	
                aux = new StringTokenizer(linea," ");
                int numeroColumnas = aux.countTokens();
                if (numeroColumnas == modelo.getColumnCount()){

                    String[] nombres = new String[numeroColumnas];
                    Class[] clases = new Class[numeroColumnas];
                    for(int i=0; i<numeroColumnas; i++){
                          nombres[i] = aux.nextToken();
                          System.out.println("cabecera = " + nombres[i]);
                          try {
                              clases[i] = Class.forName("java.lang.String");
                          } catch (ClassNotFoundException e){
                              e.printStackTrace();
                          }
                    }
                    
                    //Hago una copia eliminando el primer dato del array de la cabecera (Num.)
                    String[] nombres2 = new String[nombres.length-1];
                    Class[] clases2 = new Class[clases.length-1];
                    for (int i = 0; i < nombres.length-1; i++){
                    	nombres2[i] = nombres[i+1];
                    	clases2[i] = clases[i];
                    	System.out.println("CABECERA2 : " + nombres2[i]);
                    }
                    
                    modelo.setColumnNames(nombres);
                    modelo.setColumnClass(clases);
                    table.setModel(modelo);
                    control = new ControlTabla(modelo);
                    table.repaint();
                    
                    while((linea=br.readLine())!=null){ //##### Guardo en la tabla línea a línea ############
                        aux = new StringTokenizer(linea," ");
                        if (numeroColumnas == aux.countTokens()){
                            Object[] objetos = new Object[numeroColumnas];
                            for(int i=0; i<numeroColumnas; i++){
                                objetos[i] = aux.nextToken();
                            }
                            Fila nueva_fila = new Fila(objetos);
                            modelo.anhadeFila(nueva_fila, "noEjecutado");
                        }else{
                            return -1;
                        }
                    }
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }catch(IOException e){
            e.printStackTrace();
            return -1;
        }finally{
            try{
                if(null != fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    
    public Integer escribirTxt(){
        try{
            fw = new FileWriter (archivo);
            pw = new PrintWriter(fw);
            for( int columna = 0 ; columna < modelo.getColumnCount() ; columna++ ){
                String tipo = modelo.getColumnName( columna );
                if (columna == 0){
                    pw.print(tipo);
                }else{
                    pw.print(" "+tipo);
                }
            }
            pw.println();
            for( int fila = 0 ; fila < modelo.getRowCount() ; fila++ ){
                for( int columna = 0 ; columna < modelo.getColumnCount() ; columna++ ){ 
                    String dato = modelo.getValueAt(fila, columna).toString();
                    if (columna == 0){
                        pw.print(dato);
                    }else{
                        pw.print(" "+dato);
                    }
                }
                pw.println();
            }
            pw.flush();
            return 0;
        }catch(IOException e){
            e.printStackTrace();
            return -1;
        }finally{
            try{
                if(null != fr){
                    fw.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
