package optimex;
import java.io.*;
import java.util.List;

import javax.swing.*;
import jxl.write.*;
import jxl.*;
import java.awt.Color;

public class ExcelTableExporter {
    private File file;
    private JTable table;
    private String nombreTab;
    private ModeloTabla model;
    
    private List<JTable> tables;
    private List<ModeloTabla> models;
    private List<String> nombresTab;
    
    public ExcelTableExporter(JTable table, ModeloTabla model, File file, String nombreTab){
        this.file=file;
        this.table=table;
        this.nombreTab=nombreTab;
        this.model=model;
    }
    
    public ExcelTableExporter(List<JTable> tables, List<ModeloTabla> models, File file, List<String> nombresTab){
    	this.file=file;
    	this.tables=tables;
    	this.models=models;
    	this.nombresTab=nombresTab;
    }
    
    public Integer export(){
        try{
            if (table.getRowCount()>0){
                //Nuestro flujo de salida para apuntar a donde vamos a escribir
                DataOutputStream out=new DataOutputStream(new FileOutputStream(file));

                //Representa nuestro archivo en excel y necesita un OutputStream para saber donde va a colocar los datos
                WritableWorkbook w = Workbook.createWorkbook(out);
                WritableSheet s = w.createSheet(nombreTab, 0);
                WritableCellFormat fondoCabecera = new WritableCellFormat();
                fondoCabecera.setBackground(jxl.format.Colour.WHITE);
                WritableCellFormat fondoGrisClaro = new WritableCellFormat();
                fondoGrisClaro.setBackground(jxl.format.Colour.GREY_25_PERCENT);
                WritableCellFormat fondoGris = new WritableCellFormat();
                fondoGris.setBackground(jxl.format.Colour.GREY_40_PERCENT);
                WritableCellFormat fondoGrisLetraAzul = new WritableCellFormat();
                WritableFont textFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
                textFont.setColour(jxl.format.Colour.LIGHT_BLUE);
                fondoGrisLetraAzul.setBackground(jxl.format.Colour.GREY_40_PERCENT);
                fondoGrisLetraAzul.setFont(textFont);

                for(int i=0;i< table.getColumnCount();i++){
                    Object objeto=table.getColumnName(i);
                    Object objeto2=table.getValueAt(0,i);
                    if (String.valueOf(objeto).length() > 0){
                        s.setColumnView(i, String.valueOf(objeto).length()+10);
                    }else{
                        s.setColumnView(i, String.valueOf(objeto2).length()+10);
                    }
                    s.addCell(new Label(i, 0, String.valueOf(objeto),fondoCabecera));
                }
                for(int i=0;i< table.getColumnCount();i++){
                    for(int j=0;j<table.getRowCount();j++){
                        Object objeto = table.getValueAt(j,i);
                        Class clase = table.getColumnClass(i);
                        Color color = model.getCellColor(j,i);
                        Color colorLetra = model.getCellTextColor(j,i);
                        if (colorLetra == Color.BLUE){
                            if ((clase.toString().equals("class java.lang.Integer"))&(objeto!=null)){
                                s.addCell(new jxl.write.Number(i, j+1, (double)(Integer)objeto, fondoGrisLetraAzul));
                            }else if (objeto!=null){
                                s.addCell(new Label(i, j+1, String.valueOf(objeto), fondoGrisLetraAzul));
                            }else{
                                s.addCell(new Label(i, j+1, "", fondoGrisLetraAzul));
                            }
                        }else if (color == Color.GRAY){
                            if ((clase.toString().equals("class java.lang.Integer"))&(objeto!=null)){
                                s.addCell(new jxl.write.Number(i, j+1, (double)(Integer)objeto, fondoGris));
                            }else if (objeto!=null){
                                s.addCell(new Label(i, j+1, String.valueOf(objeto), fondoGris));
                            }else{
                                s.addCell(new Label(i, j+1, "", fondoGris));
                            }
                        }else{
                            if ((clase.toString().equals("class java.lang.Integer"))&(objeto!=null)){
                                s.addCell(new jxl.write.Number(i, j+1, (double)(Integer)objeto, fondoGrisClaro));
                            }else if (objeto!=null){
                                s.addCell(new Label(i, j+1, String.valueOf(objeto), fondoGrisClaro));
                            }else{
                                s.addCell(new Label(i, j+1, "", fondoGrisClaro));
                            }
                        }
                    }
                }
                //Como excel tiene muchas hojas esta crea o toma la hoja
                //Coloca el nombre del "tab" y el indice del tab
                w.write();
                //Cerramos el WritableWorkbook y DataOutputStream
                w.close();
                out.close();
                //si todo sale bien salimos de aqui con un true
                return 0;
            }else{
                return 1;
            }

        }catch(IOException ex){ex.printStackTrace();}
            catch(WriteException ex){ex.printStackTrace();}

        //Si llegamos hasta aqui algo salio mal
        return -1;
    } 
    
    public Integer export2(){
        try{
            //Nuestro flujo de salida para apuntar a donde vamos a escribir
            DataOutputStream out=new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);           
        	
            int numHojas = tables.size();
        	
  		   	WritableCellFormat fondoCabecera = new WritableCellFormat();
  		   	fondoCabecera.setBackground(jxl.format.Colour.WHITE);
  		   	WritableCellFormat fondoGrisClaro = new WritableCellFormat();
  		   	fondoGrisClaro.setBackground(jxl.format.Colour.GREY_25_PERCENT);
  		   	WritableCellFormat fondoGris = new WritableCellFormat();
  		   	fondoGris.setBackground(jxl.format.Colour.GREY_40_PERCENT);
  		   	WritableCellFormat fondoGrisLetraAzul = new WritableCellFormat();
  		   	WritableFont textFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
  		   	textFont.setColour(jxl.format.Colour.LIGHT_BLUE);
  		   	fondoGrisLetraAzul.setBackground(jxl.format.Colour.GREY_40_PERCENT);
  		   	fondoGrisLetraAzul.setFont(textFont);
        	
        	for(int x=0; x<numHojas; x++){
        		WritableSheet s = w.createSheet(nombresTab.get(x), x);
     		   
        	   if (tables.get(x).getRowCount()>0){
        		   for(int i=0;i< tables.get(x).getColumnCount();i++){
        			   Object objeto=tables.get(x).getColumnName(i);
        			   Object objeto2=tables.get(x).getValueAt(0,i);
        			   if (String.valueOf(objeto).length() > 0){
        				   s.setColumnView(i, String.valueOf(objeto).length()+10);
        			   }else{
                        s.setColumnView(i, String.valueOf(objeto2).length()+10);
        			   }
        			   s.addCell(new Label(i, 0, String.valueOf(objeto),fondoCabecera));
        		   }
        		   for(int i=0;i< tables.get(x).getColumnCount();i++){
        			   for(int j=0;j<tables.get(x).getRowCount();j++){
        				   Object objeto = tables.get(x).getValueAt(j,i);
        				   Class clase = tables.get(x).getColumnClass(i);
        				   Color color = models.get(x).getCellColor(j,i);
        				   Color colorLetra = models.get(x).getCellTextColor(j,i);
        				   if (colorLetra == Color.BLUE){
        					   if ((clase.toString().equals("class java.lang.Integer"))&(objeto!=null)){
        						   s.addCell(new jxl.write.Number(i, j+1, (double)(Integer)objeto, fondoGrisLetraAzul));
        					   }else if (objeto!=null){
        						   s.addCell(new Label(i, j+1, String.valueOf(objeto), fondoGrisLetraAzul));
        					   }else{
        						   s.addCell(new Label(i, j+1, "", fondoGrisLetraAzul));
        					   }
        				   }else if (color == Color.GRAY){
        					   if ((clase.toString().equals("class java.lang.Integer"))&(objeto!=null)){
        						   s.addCell(new jxl.write.Number(i, j+1, (double)(Integer)objeto, fondoGris));
        					   }else if (objeto!=null){
        						   s.addCell(new Label(i, j+1, String.valueOf(objeto), fondoGris));
        					   }else{
        						   s.addCell(new Label(i, j+1, "", fondoGris));
        					   }
        				   }else{
        					   if ((clase.toString().equals("class java.lang.Integer"))&(objeto!=null)){
        						   s.addCell(new jxl.write.Number(i, j+1, (double)(Integer)objeto, fondoGrisClaro));
        					   }else if (objeto!=null){
        						   s.addCell(new Label(i, j+1, String.valueOf(objeto), fondoGrisClaro));
        					   }else{
        						   s.addCell(new Label(i, j+1, "", fondoGrisClaro));
        					   }
        				   }
        			   }
        		   }

        	   }
        	}
     	   //Como excel tiene muchas hojas esta crea o toma la hoja
            //Coloca el nombre del "tab" y el indice del tab
            w.write();
            //Cerramos el WritableWorkbook y DataOutputStream
            w.close();
            out.close();
            //si todo sale bien salimos de aqui con un true
            return 0;
        }catch(IOException ex){ex.printStackTrace();}
            catch(WriteException ex){ex.printStackTrace();}
        //Si llegamos hasta aqui algo salio mal
        return -1;
    } 
}
