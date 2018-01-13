package optimex;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.List;

import javax.swing.event.*;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;

import javax.swing.border.*;
import java.lang.reflect.*;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class PanelDatos extends JPanel {
    
    private static GestorEventosRollOver gestor_eventos;
    private Ventana ventana;
    public static Editor panel_codigo;
    public static JTextArea panel_linea;
    public static PanelEditor panel_botonesEditor;
    public static JPanel panel_editor;
    public static JLabel label;
    public static JPanel panel_editorCompleto;
    public static JScrollPane scroll_codigo;
    public static JTabbedPane panel_tablas;
    public static JPanel tabla_resultado, tabla_historico, tabla_resumen_numerico, panelDatos, panelDatos2, tabla_datos, tabla_resumen_grafico;
    public static JLabel titulo_diagrama1;
    public static ModeloTabla modelo1, modelo2, modelo3, modeloDatos, modeloDatos2;
    public static ControlTabla control1, control2, control3, controlDatos, controlDatos2, controlEJ;
    public static TooltipJTable tabla1, tabla2, tabla3, tablaEJ;
    public static TooltipJTable tablaDatos, tablaDatos2;
    public static JScrollPane scroll1, scroll2, scroll3, scrollDatos, scrollDatos2, scrollEJ;
    public JSplitPane panelVert;
    private static Boolean enIngles;
    private static Boolean maximizar = true;
    private static String mensaje1 = "La tabla está vacía", mensaje2 = "¿Quieres borrar: ",
            mensaje3 = "Elige un archivo:", mensaje4 = "Fichero .xml", mensaje4a = "Imagen .jpg",
            		mensaje4b = "Imagen .png",mensaje4c = "Imagen .gif",
            mensaje5 = "Selecciona el numero de variables",
            mensaje6 = "Numero de variables", mensaje7 = "Selecciona el tipo de cada variable",
            mensaje8 = "No se ha importado porque no coincide la signatura",
            mensaje9 = "Ha fallado la importación de los datos",
            mensaje10 = "Método", mensaje11 = "Datos de entrada",
            mensaje12 = "Resultado", mensaje13 = "Medida",
            mensaje14 = "Introduce los datos",
            mensaje16 = "Tabla de datos", 
    		mensaje17 = "Datos por teclado",
            mensaje18 = "Resultado",
            mensaje20 = "No se ha generado porque no coincide la signatura",
            mensaje21 = "Exportado con éxito", mensaje22 = "La tabla está vacía",
            mensaje23 = "Resultado de la exportación", mensaje24 = "Selecciona los datos a ejecutar",
            mensaje25 = "¿Seguro que quieres borrar los datos de sesión?", mensaje26 = "Borrado",
            mensaje27 = "Exportada tabla con éxito", mensaje28 = "La exportación ha fallado",
            mensaje29 = "Exportación", mensaje30 = "Código", mensaje31 = "Tablas",
            mensaje32 = "Fichero excel .xls", 
            mensaje33 = "Núm. ejecuciones", 
            mensaje34 = "% soluciones subóptimas",
            mensaje35 = "% soluciones óptimas", 
            mensaje36 = "% soluciones sobreóptimas", 
            mensaje37 = "% valor medio no óptimo",
            mensaje38 = "% valor sobreóptimo extremo", 
            mensaje39 = "% valor subóptimo extremo",
            mensaje40 = "No hay Optimo", mensaje41 = "Fichero .txt",  
    		mensaje42 = "Filas añadidas: ", 
    		mensaje43 = "Filas añadidas: 1",
    		mensaje43a = "El dato ya existe o tiene un formato inválido",
    		mensaje44 = "Fichero importado correctamente",
    		mensaje44a = mensaje44 + " \n" + mensaje42,
    		mensaje39a = "% Casos superóptimos",
    		mensaje39b = "% Casos óptimos",
    		mensaje39c = "% Casos subóptimos",
    		mensaje40a = "% Media",
    		mensaje40b = "% Subóptima",
    		mensaje40c = "% Superóptima",
    		mensaje41a = "% de resultados óptimos",
    		mensaje41b = "Average and extreme valuess";
	
    private static String t1 = "Tabla resultado", t2 = "Tabla histórica", t3 = "Tabla resumida", t4 = "Tabla datos";
    private static File currentDirectoryXml = null;    //el directorio actual, ninguno por defecto
    private static File currentDirectoryExcel = null;  //el directorio actual, ninguno por defecto
    private static Aleatorios aleator;
    private static int[] contadorOptimosMaximos;
    private static int[] contadorOptimosMinimos;
    private static int[] contadorSuboptimosMaximos;
    private static int[] contadorSuboptimosMinimos;
    private static int[] contadorSuperoptimosMaximos;
    private static int[] contadorSuperoptimosMinimos;
    private static int optimus, optimusAutomatico;
    private static double[] sumatoriosDesviacion;
    private static float[] desviacionesMaximaInferior;
    private static float[] desviacionesMaximaSuperior;
    private static String[] botones = {"Aceptar","Cancelar"};
    private static String[] boton = {"Aceptar"};
    private static int height;
    private static int width;
    private static int widthTabla;
    private static int widthTabla2;
    private static XMLEstado estado;
    private static RecuerdaTopes recordador; //########### PARA RECORDAR TOPES EN NUMEROS ALEATORIOS ############
    private static JPanel panelito;
    private static JButton boton1, boton2;
    private static RSyntaxTextArea textArea;
    
    static Font font = new Font("Tahoma", Font.PLAIN, 11); 
    static String[] titulo_casos1 = new String[3];
   	static String[] titulo_casos2 = new String[3];
    
    private static final TableModelListener escuchador = new TableModelListener(){ 
         public void tableChanged(TableModelEvent evt) {
             modeloDatos2.modificarOcultoAt(evt.getLastRow(), "noEjecutado");
         }
    };
    private static final JFrame frame = new JFrame();
    
    //private static modificarDatos md;
        
    public PanelDatos(GestorEventosRollOver gestor_eventos, Ventana ventana) {
        this.gestor_eventos = gestor_eventos;        
        this.ventana = ventana;
        optimus = -1;
        optimusAutomatico = -1;
      
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            enIngles = false;
        } else {
            enIngles = true;
        }
        File directorioExcel = new File(estado.dameDirectorioExcel());
        if (directorioExcel.isDirectory()){
            currentDirectoryExcel = directorioExcel;
        } 
        File directorioXml = new File(estado.dameDirectorioXml());
        if (directorioXml.isDirectory()){
            currentDirectoryXml = directorioXml;
        } 
        PanelCodigo(gestor_eventos);
        recordador = new RecuerdaTopes();
        
    }
    
    public static void Eventualizar (JPanel componente, 
            final GestorEventosRollOver gestor_eventos){
                componente.addMouseListener(gestor_eventos);
    }
    
    public void PanelCodigo (final GestorEventosRollOver gestor_eventos){
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        height = pantalla.height;
        width = pantalla.width;
        
        /********************************************************/
        
        if (estado.dameIdioma().equals("espanol")){
            ponerEspanolRelajado();
        } else {
            ponerInglesRelajado();
        }
 /*       panel_linea = new JTextArea();
        panel_linea.setEditable(false);
        Font f = new Font ("Courier",Font.PLAIN,12);
        panel_linea.setFont(f);
        panel_linea.setBackground(Color.lightGray);
        panel_linea.setForeground(Color.black);
        panel_linea.setPreferredSize(new Dimension(30,height/2));
        panel_linea.setMaximumSize(new Dimension(30,99999));*/
        
        panel_codigo = new Editor(gestor_eventos);
        panel_codigo.setEnabled(false);
        panel_codigo.setVentana(this.ventana);
        panel_codigo.setLineWrap(false);     
        
        panel_editor = new JPanel();
        panel_editor.setLayout(new BoxLayout(panel_editor,BoxLayout.X_AXIS));
        panel_editor.add(PanelDatos.panel_codigo.sp); 
     //   panel_editor.add(panel_linea);
     //   panel_editor.add(panel_codigo);
               
        scroll_codigo = new JScrollPane();
        scroll_codigo.setViewportView(panel_editor);
        scroll_codigo.setPreferredSize(new Dimension(width/2,height));

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
        panel_botonesEditor = new PanelEditor(gestor_eventos, ventana);
        panel_editorCompleto = new JPanel();
        panel_editorCompleto.setBorder(new TitledBorder(mensaje30));
        panel_editorCompleto.setLayout(new BoxLayout(panel_editorCompleto,BoxLayout.Y_AXIS));
        panel_editorCompleto.add(scroll_codigo);
        panel_editorCompleto.add(panel_botonesEditor);
                     
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
        panel_tablas = new JTabbedPane();
        panel_tablas.setBorder(new TitledBorder(mensaje31));
        panel_tablas.setPreferredSize(new Dimension(width/2,height));
        
        // implementacion de la tabla de resultados
        //-----------------------------------------  
        tabla_resultado = new JPanel();
        tabla_resultado.setLayout(new GridLayout(1,1));  
        modelo1 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modelo1.ponerEspanol();
        } else {
            modelo1.ponerIngles();
        }
        control1 = new ControlTabla(modelo1);
        scroll1 = new JScrollPane();
        tabla1 = new TooltipJTable();
        tabla1.setModel(modelo1);
        tabla1.setForeground(Color.BLACK);
        tabla1.setBackground(new Color(240, 240, 240));
        tabla1.setFillsViewportHeight(true);
        modelo1.setTable(tabla1);
        scroll1.setViewportView(tabla1);
        scroll1.setColumnHeaderView(tabla1.getTableHeader());
        tabla_resultado.add(scroll1);
        //-----------------------------------------
        
        // implementacion de la tabla de historico
        //-----------------------------------------    
        tabla_historico = new JPanel();
        tabla_historico.setLayout(new GridLayout(1,1));    
        modelo2 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modelo2.ponerEspanol();
        } else {
            modelo2.ponerIngles();
        }
        control2 = new ControlTabla(modelo2);
        scroll2 = new JScrollPane();
        tabla2 = new TooltipJTable();
        tabla2.setModel(modelo2);
        tabla2.setForeground(Color.BLACK);
        tabla2.setBackground(new Color(240, 240, 240));
        tabla2.setFillsViewportHeight(true);
        modelo2.setTable(tabla2);
        scroll2.setViewportView(tabla2);
        scroll2.setColumnHeaderView(tabla2.getTableHeader());
        tabla_historico.add(scroll2);
        //-----------------------------------------
        
        // implementacion de la tabla de resumen numérico
        //-----------------------------------------------
        tabla_resumen_numerico = new JPanel();
        tabla_resumen_numerico.setLayout(new GridLayout(1,1));
        modelo3 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modelo3.ponerEspanol();
        } else {
            modelo3.ponerIngles();
        }
        control3 = new ControlTabla(modelo3);
        scroll3 = new JScrollPane();
        tabla3 = new TooltipJTable();
        tabla3.setModel(modelo3);
        tabla3.setForeground(Color.BLACK);
        tabla3.setBackground(new Color(240, 240, 240));
        tabla3.setFillsViewportHeight(true);
        modelo3.setTable(tabla3);
        scroll3.setViewportView(tabla3);
        scroll3.setColumnHeaderView(tabla3.getTableHeader());
        tabla_resumen_numerico.add(scroll3);
        //-----------------------------------------
        
        // implementacion de la tabla de resumen gráfico
        //-----------------------------------------------
        tabla_resumen_grafico = new JPanel();
      //  tabla_resumen_grafico.setLayout(new GridLayout(1,1)); //1207
        tabla_resumen_grafico.setLayout(new FlowLayout());
        //-----------------------------------------
        
        // implementacion de la tabla de datos
        //-----------------------------------------   
        panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(1,1));  
        modeloDatos = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modeloDatos.ponerEspanol();
        } else {
            modeloDatos.ponerIngles();
        }
        controlDatos = new ControlTabla(modeloDatos);
        scrollDatos = new JScrollPane();
        tablaDatos = new TooltipJTable();
        tablaDatos.setModel(modeloDatos);
        tablaDatos.setForeground(Color.BLACK);
        tablaDatos.setBackground(new Color(240, 240, 240));
        tablaDatos.setSelectionForeground(Color.BLACK); // antes WHITE
        tablaDatos.setSelectionBackground(new Color(153,204,255)); // antes BLUE
        tablaDatos.setFillsViewportHeight(true);
        tablaDatos.setAutoResizeMode(TooltipJTable.AUTO_RESIZE_OFF);//
        widthTabla = tablaDatos.packColumns(2);//
        modeloDatos.setTable(tablaDatos);
        scrollDatos.setViewportView(tablaDatos);
        scrollDatos.setColumnHeaderView(tablaDatos.getTableHeader());
        panelDatos.add(scrollDatos);       
        //-----------------------------------------
        
        // implementacion de la tabla de datos2
        //-----------------------------------------       
        panelDatos2 = new JPanel();
        panelDatos2.setLayout(new GridLayout(1,1));  
        modeloDatos2 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modeloDatos2.ponerEspanol();
        } else {
            modeloDatos2.ponerIngles();
        }
        controlDatos2 = new ControlTabla(modeloDatos2);
        scrollDatos2 = new JScrollPane();
        tablaDatos2 = new TooltipJTable();
        tablaDatos2.setModel(modeloDatos2);
        tablaDatos2.setForeground(Color.BLACK);
        tablaDatos2.setBackground(new Color(240, 240, 240));
        tablaDatos2.setSelectionForeground(Color.WHITE);
        tablaDatos2.setSelectionBackground(Color.BLUE);
        tablaDatos2.setFillsViewportHeight(true);
    //    tablaDatos2.setAutoResizeMode(TooltipJTable.AUTO_RESIZE_OFF);
        widthTabla2 = tablaDatos2.packColumns(2);
        modeloDatos2.setTable(tablaDatos2);
        scrollDatos2.setViewportView(tablaDatos2);
        scrollDatos2.setColumnHeaderView(tablaDatos2.getTableHeader());
        panelDatos2.add(scrollDatos2);
        
        // implementacion de la tabla datos para colocar en el panel de las pestañas
        // JPanel creado nuevo para mostrar los datos con los que se trabaja en un problema:      
        tabla_datos = new JPanel();
        tabla_datos.setLayout(new GridLayout(1,1));  
        modeloDatos.setCellEditable();
        controlEJ = new ControlTabla(modeloDatos);
        scrollEJ = new JScrollPane();
        tablaEJ = new TooltipJTable();
        tablaEJ.setModel(modeloDatos);
        tablaEJ.setForeground(Color.BLACK);
        tablaEJ.setBackground(new Color(240, 240, 240));
        tablaEJ.setFillsViewportHeight(true);
        modeloDatos.setTable(tablaEJ);
        scrollEJ.setViewportView(tablaEJ);
        scrollEJ.setColumnHeaderView(tablaEJ.getTableHeader()); 
        tabla_datos.add(scrollEJ);

        Eventualizar(tabla_datos, gestor_eventos);
        Eventualizar(tabla_resultado,gestor_eventos);
        Eventualizar(tabla_historico,gestor_eventos);
        Eventualizar(tabla_resumen_numerico,gestor_eventos);
        Eventualizar(tabla_resumen_grafico,gestor_eventos);
        if (estado.dameIdioma().equals("espanol")){
            panel_tablas.addTab( "Datos", null, tabla_datos, "Datos" ); 
        	panel_tablas.addTab( "Resultado", null, tabla_resultado, "Resultado" );
            panel_tablas.addTab( "Histórica", null, tabla_historico, "Histórica" );
            panel_tablas.addTab( "Resumen numérico", null, tabla_resumen_numerico, "Resumen numérico" ); 
            panel_tablas.addTab( "Resumen gráfico", null, tabla_resumen_grafico, "Resumen gráfico" ); 
        } else {      	
            panel_tablas.addTab( "Data", null, tabla_datos, "Data" );
            panel_tablas.addTab( "Result", null, tabla_resultado, "Result" );
            panel_tablas.addTab( "Historical", null, tabla_historico, "Historical" );
            panel_tablas.addTab( "Numerical summary", null, tabla_resumen_numerico, "Numerical summary" );
            panel_tablas.addTab( "Graphic summary", null, tabla_resumen_grafico, "Graphic summary" ); 
        }
        panel_tablas.setSelectedIndex(0);
        
        panelVert = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
        panelVert.setLeftComponent( panel_editorCompleto );
        panelVert.setRightComponent( panel_tablas );
        panelVert.setOneTouchExpandable(true);
        panelVert.setDividerLocation(width/2);
        panelVert.setDividerSize(15);
        this.add(panelVert);
        
        //######################################################

        panelito = new JPanel();
        panelito.setLayout(new BoxLayout(panelito,BoxLayout.X_AXIS));

        boton1 = new JButton(botones[1]);
        boton2 = new JButton(botones[0]);

        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tablaDatos2.isEditing()){
                    TableCellEditor editor = tablaDatos2.getCellEditor();
                    editor.stopCellEditing();
                }
                modeloDatos2.setCellNonEditable();
                modeloDatos2.removeTableModelListener(escuchador);
                tablaDatos2.tableChanged(null);
                tablaDatos2.setColumnSelectionAllowed(false);
                tablaDatos2.setRowSelectionAllowed(true);
                tablaDatos2.clearSelection();
                frame.setVisible(false);
                frame.dispose();
            }
          });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaDatos2.isEditing()){
                    TableCellEditor editor = tablaDatos2.getCellEditor();
                    editor.stopCellEditing();
                }
                modeloDatos2.setCellNonEditable();
                modeloDatos2.removeTableModelListener(escuchador);
                tablaDatos2.tableChanged(null);
                tablaDatos2.setColumnSelectionAllowed(false);
                tablaDatos2.setRowSelectionAllowed(true);
                tablaDatos2.clearSelection();
                //############ COPIA DE DATOS #####################################
                resetearTabla(4);
                for (int f=0; f<modeloDatos2.getRowCount(); f++){
                    Object[] paraFil = new Object[dameNumeroColumnas(5)];
                    for (int c=0; c<dameNumeroColumnas(5); c++){
                        paraFil[c] = modeloDatos2.getValueAt(f, c);
                    }
                    Fila fil = new Fila(paraFil);
                    modeloDatos.anhadeFila (fil, modeloDatos2.getOcultoAt(f));
                }
                //#################################################################
                frame.setVisible(false);
                frame.dispose();
            }
          });

        //######################################################
        JLabel icono = new JLabel();
        icono.setIcon(new ImageIcon(PanelDatos.class.getResource("imagenes/DatosModificar.png")));
        JPanel panelIcono = new JPanel();
        panelIcono.add(icono, BorderLayout.NORTH);

        panelito.add(Box.createHorizontalGlue());
        panelito.add(boton1);
        panelito.add(Box.createHorizontalStrut(4));
        panelito.add(boton2);

        frame.getContentPane().add(panelIcono, BorderLayout.WEST);
        frame.getContentPane().add(panelDatos2, BorderLayout.EAST);
        frame.getContentPane().add(panelito, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle(mensaje16);
        frame.setAlwaysOnTop(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                if (tablaDatos2.isEditing()){
                    TableCellEditor editor = tablaDatos2.getCellEditor();
                    editor.stopCellEditing();
                }
                modeloDatos2.setCellNonEditable();
                modeloDatos2.removeTableModelListener(escuchador);
                tablaDatos2.tableChanged(null);
                tablaDatos2.setColumnSelectionAllowed(false);
                tablaDatos2.setRowSelectionAllowed(true);
                tablaDatos2.clearSelection();
                frame.setVisible(false);
                frame.dispose();
            };
        });
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        //######################################################
        
    }
    
public static void ActualizarLineas(int lineas){
    
    Point posicionInterna = scroll_codigo.getViewport().getViewPosition();

    panel_linea.setText("");
    for (int i=1; i<lineas+1; i++){
        if (i<10){
            panel_linea.append("   "+i+"\n");
        }else if (i<100){
            panel_linea.append("  "+i+"\n");
        }else if (i<1000){
            panel_linea.append(" "+i+"\n");
        }else{
            panel_linea.append(""+i+"\n");
        }
    }
    
    SwingUtilities.invokeLater(new devolverScroll(scroll_codigo, posicionInterna));
    
};

private static void ponerEspanolRelajado(){
    enIngles = false;
    mensaje1 = "La tabla está vacía";
    mensaje2 = "¿Quieres borrar: ";
    mensaje3 = "Elige un archivo:";
    mensaje4 = "Fichero .xml";
    mensaje5 = "Selecciona el numero de variables";
    mensaje6 = "Numero de variables";
    mensaje7 = "Selecciona los tipos de las variables";
    mensaje8 = "No se ha importado porque no coincide la signatura";
    mensaje9 = "La importación de los datos ha fallado";
    mensaje10 = "Método";
    mensaje11 = "Datos de entrada";
    mensaje12 = "Resultado";
    mensaje13 = "Medida";
    mensaje14 = "Introduce los datos";
    mensaje16 = "Tabla de datos";
    mensaje17 = "Datos por teclado";
    mensaje18 = "Resultado";
    mensaje20 = "No se ha generado porque no coincide la signatura";
    mensaje21 = "Exportado con éxito";
    mensaje22 = "La tabla está vacía";
    mensaje23 = "Resultado de la exportación";
    mensaje24 = "Selecciona los datos a ejecutar";
    mensaje25 = "¿Seguro que quieres borrar los datos de sesión?";
    mensaje26 = "Borrado";
    mensaje27 = "Exportada tabla con éxito";
    mensaje28 = "La exportación ha fallado";
    mensaje29 = "Exportación";
    mensaje30 = "Código";
    mensaje31 = "Tablas";
    mensaje32 = "Fichero excel .xls";
    mensaje33 = "Núm. ejecuciones";
    mensaje34 = "% soluciones subóptimas";
    mensaje35 = "% soluciones óptimas";
    mensaje36 = "% soluciones sobreóptimas";
    mensaje37 = "% valor medio no óptimo";
    mensaje38 = "% valor sobreóptimo extremo";
    mensaje39 = "% valor subóptimo extremo";
    mensaje40 = "No hay Optimo";
    mensaje41 = "Fichero .txt";
    
    mensaje39a = "% Casos superóptimos";
    mensaje39b = "% Casos óptimos";
    mensaje39c = "% Casos subóptimos";
	mensaje40a = "% Media";
	mensaje40b = "% Subóptima";
	mensaje40c = "% Superóptima";
	mensaje41a = "% de resultados óptimos";
	mensaje41b = "Valores medios y extremos";
    
    t1 = "Tabla resultado";
    t2 = "Tabla histórica";
    t3 = "Tabla resumida";
    t4 = "Tabla datos";
    botones[0] = "Aceptar";
    botones[1] = "Cancelar";
    boton[0] = "Aceptar";
}

private static void ponerInglesRelajado(){
    enIngles = true;
    mensaje1 = "The table is empty";
    mensaje2 = "Do you want to erase: ";
    mensaje3 = "Chooses a file:";
    mensaje4 = "File .xml";
    mensaje4a = "Image .jpg";
    mensaje4b = "Image .png";
    mensaje4c = "Image .gif";
    mensaje5 = "Selects the number of variables";
    mensaje6 = "Number of variables";
    mensaje7 = "Selects the types of the variables";
    mensaje8 = "The data were not imported because the types of the variables does not coincide";
    mensaje9 = "The import of the data has failed";
    mensaje10 = "Num.";
    mensaje10 = "Method";
    mensaje11 = "Inputs";
    mensaje12 = "Result";
    mensaje13 = "Measure";
    mensaje14 = "Enter the data";
    mensaje16 = "Data table";
    mensaje17 = "Keyboard Data";
    mensaje18 = "Result";
    mensaje20 = "The data have not been generated because the types of the variables does not coincide";
    mensaje21 = "Exported successfully";
    mensaje22 = "The table is empty";
    mensaje23 = "Export Result";
    mensaje24 = "Selects data to run";
    mensaje25 = "Are you sure you want to delete the session data?";
    mensaje26 = "Erased";
    mensaje27 = "Successfully exported table";
    mensaje28 = "The export has failed";
    mensaje29 = "Export";
    mensaje30 = "Code";
    mensaje31 = "Tables";
    mensaje32 = "Excel file .xls";
    mensaje33 = "Num. executions";
    mensaje34 = "% suboptimal solutions";
    mensaje35 = "% optimal solutions";
    mensaje36 = "% superoptimal solutions";
    mensaje37 = "% average no optimal value";
    mensaje38 = "% superoptimal extreme value";
    mensaje39 = "% suboptimal extreme value";
    mensaje40 = "No optimal";
    mensaje41 = "File .txt";
    
    mensaje39a = "% Superoptimal cases";
    mensaje39b = "% Optimal cases";
    mensaje39c = "% Suboptimal cases";
	mensaje40a = "% Mean";
	mensaje40b = "% Suboptimal";
	mensaje40c = "% Superoptimal";
	mensaje41a = "% of optimal results";
	mensaje41b = "Average and extreme values";
    
    t1 = "Result table";
    t2 = "Historical table";
    t3 = "Summarized table";
    t4 = "Data table";
    botones[0] = "Accept";
    botones[1] = "Cancel";
    boton[0] = "Accept";
}
    
public static void ponerEspanol(){
    enIngles = false;
    panel_tablas.setTitleAt(0, "Datos");
    panel_tablas.setToolTipTextAt(0, "Datos");
    panel_tablas.setTitleAt(1, "Resultado");
    panel_tablas.setToolTipTextAt(1, "Resultado");
    panel_tablas.setTitleAt(2, "Histórica");
    panel_tablas.setToolTipTextAt(2, "Histórica");
    panel_tablas.setTitleAt(3, "Resumen numérico");
    panel_tablas.setToolTipTextAt(3, "Resumen numérico");
    panel_tablas.setTitleAt(4, "Resumen gráfico");
    panel_tablas.setToolTipTextAt(4, "Resumen gráfico");
    panel_codigo.ponerEspanol();
    modelo1.ponerEspanol();
    modelo2.ponerEspanol();
    modelo3.ponerEspanol();
    String[] nombres = new String[3];
    nombres[0] = "Método";
    nombres[1] = "Datos de entrada";
    nombres[2] = "Resultado";
    if (modelo1.estaInicializadaTabla()){
        modelo1.setColumnNames(nombres);
    }
    int columnas = modelo3.getColumnCount();
    String[] nombres3 = new String[columnas];
    for (int i=0; i<columnas; i++){
        nombres3[i] = modelo3.getColumnName(i);
        nombres3[i] = nombres3[i].replace("Measure", "Medida");
    }
    
    if(titulo_casos1[0] != null){ //Comprobación gráficas generadas
    	actualizarGraficas(); //añadido para cambio de idioma en gráficas
    }
    	
    modelo3.setColumnNames(nombres3);
    mensaje1 = "La tabla está vacía";
    mensaje2 = "¿Quieres borrar: ";
    mensaje3 = "Elige un archivo:";
    mensaje4 = "Fichero .xml";
    mensaje5 = "Selecciona el numero de variables";
    mensaje6 = "Numero de variables";
    mensaje7 = "Selecciona los tipos de las variables";
    mensaje8 = "No se ha importado porque no coincide la signatura";
    mensaje9 = "La importación de los datos ha fallado";
    mensaje10 = "Método";
    mensaje11 = "Datos de entrada";
    mensaje12 = "Resultado";
    mensaje13 = "Medida";
    mensaje14 = "Introduce los datos";
    mensaje16 = "Tabla de datos";
    mensaje17 = "Datos por teclado";
    mensaje18 = "Resultado";
    mensaje20 = "No se ha generado porque no coincide la signatura";
    mensaje21 = "Exportado con éxito";
    mensaje22 = "La tabla está vacía";
    mensaje23 = "Resultado de la exportación";
    mensaje24 = "Selecciona los datos a ejecutar";
    mensaje25 = "¿Seguro que quieres borrar los datos de sesión?";
    mensaje26 = "Borrado";
    mensaje27 = "Exportada tabla con éxito";
    mensaje28 = "La exportación ha fallado";
    mensaje29 = "Exportación";
    mensaje30 = "Código";
    mensaje31 = "Tablas";
    mensaje32 = "Fichero excel .xls";
    mensaje33 = "Núm. ejecuciones";
    mensaje34 = "% soluciones subóptimas";
    mensaje35 = "% soluciones óptimas";
    mensaje36 = "% soluciones sobreóptimas";
    mensaje37 = "% valor medio no óptimo";
    mensaje38 = "% valor sobreóptimo extremo";
    mensaje39 = "% valor subóptimo extremo";
    mensaje40 = "No hay Optimo";
    mensaje41 = "Ficheros txt";
    panel_editorCompleto.setBorder(new TitledBorder(mensaje30));
    panel_tablas.setBorder(new TitledBorder(mensaje31));
    t1 = "Tabla resultado";
    t2 = "Tabla histórica";
    t3 = "Tabla resumida";
    t4 = "Tabla datos";
    botones[0] = "Aceptar";
    botones[1] = "Cancelar";
    boton[0] = "Aceptar";
    actualizarMaximMinim(maximizar);
    boton1.setText(botones[1]);
    boton2.setText(botones[0]);
    frame.setTitle(mensaje16);
  //  tabla1.packColumns(2); //Comentado para eliminar parpadeo
    tabla3.packColumns(2);
}

public static void ponerIngles(){
    enIngles = true;
    panel_tablas.setTitleAt(0, "Data");
    panel_tablas.setToolTipTextAt(0, "Data");
    panel_tablas.setTitleAt(1, "Result");
    panel_tablas.setToolTipTextAt(1, "Result");
    panel_tablas.setTitleAt(2, "Historical");
    panel_tablas.setToolTipTextAt(2, "Historical");
    panel_tablas.setTitleAt(3, "Numerical summary");
    panel_tablas.setToolTipTextAt(3, "Numerical summary");
    panel_tablas.setTitleAt(4, "Graphic summary");
    panel_tablas.setToolTipTextAt(4, "Graphic summary");
    panel_codigo.ponerIngles();
    modelo1.ponerIngles();
    modelo2.ponerIngles();
    modelo3.ponerIngles();
    String[] nombres = new String[3];
    nombres[0] = "Method";
    nombres[1] = "Inputs";
    nombres[2] = "Result";
    if (modelo1.estaInicializadaTabla()){
        modelo1.setColumnNames(nombres);
    }
    int columnas = modelo3.getColumnCount();
    String[] nombres3 = new String[columnas];
    for (int i=0; i<columnas; i++){
        nombres3[i] = modelo3.getColumnName(i);
        nombres3[i] = nombres3[i].replace("Medida", "Measure");
    }
    
    if(titulo_casos1[0] != null){ //Comprobación gráficas generadas
    	actualizarGraficas(); //añadido para cambio de idioma en gráficas
    }
    
    modelo3.setColumnNames(nombres3);
    mensaje1 = "The table is empty";
    mensaje2 = "Do you want to erase: ";
    mensaje3 = "Chooses a file:";
    mensaje4 = "File .xml";
    mensaje5 = "Selects the number of variables";
    mensaje6 = "Number of variables";
    mensaje7 = "Selects the types of the variables";
    mensaje8 = "The data were not imported because the types of the variables does not coincide";
    mensaje9 = "The import of the data has failed";
    mensaje10 = "Method";
    mensaje11 = "Inputs";
    mensaje12 = "Result";
    mensaje13 = "Measure";
    mensaje14 = "Enter the data";
    mensaje16 = "Data table";
    mensaje17 = "Keyboard Data";
    mensaje18 = "Result";
    mensaje20 = "The data have not been generated because the types of the variables does not coincide";
    mensaje21 = "Exported successfully";
    mensaje22 = "The table is empty";
    mensaje23 = "Export Result";
    mensaje24 = "Selects data to run";
    mensaje25 = "Are you sure you want to delete the session data?";
    mensaje26 = "Erased";
    mensaje27 = "Successfully exported table";
    mensaje28 = "The export has failed";
    mensaje29 = "Export";
    mensaje30 = "Code";
    mensaje31 = "Tables";
    mensaje32 = "Excel file .xls";
    mensaje33 = "Num. executions";
    mensaje34 = "% suboptimal solutions";
    mensaje35 = "% optimal solutions";
    mensaje36 = "% superoptimal solutions";
    mensaje37 = "% average no optimal value";
    mensaje38 = "% superoptimal extreme value";
    mensaje39 = "% suboptimal extreme value";
    mensaje40 = "No optimal";
    mensaje41 = "File .txt";
   
    panel_editorCompleto.setBorder(new TitledBorder(mensaje30));
    panel_tablas.setBorder(new TitledBorder(mensaje31));
    t1 = "Result table";
    t2 = "Historical table";
    t3 = "Summarized table";
    t4 = "Data table";
    botones[0] = "Accept";
    botones[1] = "Cancel";
    boton[0] = "Accept";
    actualizarMaximMinim(maximizar);
    boton1.setText(botones[1]);
    boton2.setText(botones[0]);
    frame.setTitle(mensaje16);
 //   tabla1.packColumns(2); //Comentado para eliminar parpadeo
    tabla3.packColumns(2);
}

public static void generaGrafico1(){
    if (estado.dameIdioma().equals("espanol")){
        ponerEspanolRelajado();
    } else {
        ponerInglesRelajado();
    }
    
    titulo_casos1[0] = mensaje39a;
    titulo_casos1[1] = mensaje39b;
    titulo_casos1[2] = mensaje39c;
    
	// MODELO DE DATOS    	        
    String[] nombres_metodos = new String[PanelDatos.modelo3.getColumnCount()-1];
    String[] porcentajes_texto = new String[3];
    double[] porcentajes = new double[3];
    
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for(int i = 0; i<nombres_metodos.length; i++){
    	nombres_metodos[i] = PanelDatos.modelo3.getColumnName(i+1);
        
        porcentajes_texto[0] = PanelDatos.modelo3.getValueAt(1, i+1).toString();
        porcentajes_texto[0] = porcentajes_texto[0].substring(0,porcentajes_texto[0].length()-2);
        porcentajes_texto[0] = porcentajes_texto[0].replace(',', '.');          
        porcentajes[0] = Double.parseDouble(porcentajes_texto[0]);  
      
        porcentajes_texto[1] = PanelDatos.modelo3.getValueAt(2, i+1).toString();
        porcentajes_texto[1] = porcentajes_texto[1].substring(0,porcentajes_texto[1].length()-2);
        porcentajes_texto[1] = porcentajes_texto[1].replace(',', '.');          
        porcentajes[1] = Double.parseDouble(porcentajes_texto[1]);      
          
        porcentajes_texto[2] = PanelDatos.modelo3.getValueAt(3, i+1).toString();
        porcentajes_texto[2] = porcentajes_texto[2].substring(0,porcentajes_texto[2].length()-2);
        porcentajes_texto[2] = porcentajes_texto[2].replace(',', '.');          
        porcentajes[2] = Double.parseDouble(porcentajes_texto[2]);    
        
        dataset.addValue(porcentajes[0], titulo_casos1[2], nombres_metodos[i]); //superóptimos
        dataset.addValue(porcentajes[1], titulo_casos1[1], nombres_metodos[i]); //óptimos
        dataset.addValue(porcentajes[2], titulo_casos1[0], nombres_metodos[i]); //subóptimos           
    }
    
    //GRÁFICO 
    JFreeChart chart = ChartFactory.createStackedBarChart(
            mensaje41a,  // chart title
            "",                  // domain axis label
            "",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
              
    chart.getTitle().setFont(font);       
    chart.getLegend().setItemFont(font);
          
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    plot.setBackgroundPaint(Color.LIGHT_GRAY);
        
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setRange(0, 1);
    rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
    rangeAxis.setTickLabelsVisible(true);
        
    StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();

    renderer.setRenderAsPercentages(true);
    renderer.setDrawBarOutline(false);
    renderer.setBaseItemLabelsVisible(true);
    renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
             
    //Colores de fondo
    renderer.setSeriesPaint(0, Color.yellow);
    renderer.setSeriesPaint(1, Color.green);
    renderer.setSeriesPaint(2, Color.red);
        
    //GRÁFICO AL COMPONENTE
    ChartPanel panel = new ChartPanel(chart);

    int height = PanelDatos.tabla_resumen_grafico.getHeight();
    int width = PanelDatos.tabla_resumen_grafico.getWidth();
    double nuevo_width = width * 0.45;
    double nuevo_height = height * 0.5;
    panel.setPreferredSize(new Dimension((int) nuevo_width, (int) nuevo_height)); 
     
    PanelDatos.tabla_resumen_grafico.add(panel);
}

public static void generaGrafico2(){
    if (estado.dameIdioma().equals("espanol")){
        ponerEspanolRelajado();
    } else {
        ponerInglesRelajado();
    }
    BoxAndWhiskerCategoryDataset dataset = createDataset();   
    JFreeChart chart = createChart((BoxAndWhiskerCategoryDataset) dataset);
    
    chart.getTitle().setFont(font);   
    chart.removeLegend();
    
    ChartPanel panel = new ChartPanel(chart, false);   
    panel.setPreferredSize(new Dimension(500, 270));   
    
    int height = PanelDatos.tabla_resumen_grafico.getHeight();
    int width = PanelDatos.tabla_resumen_grafico.getWidth();
    double nuevo_width = width * 0.45;
    double nuevo_height = height * 0.5;
    panel.setPreferredSize(new Dimension((int) nuevo_width, (int) nuevo_height)); 
    
    PanelDatos.tabla_resumen_grafico.add(panel);
}

private static BoxAndWhiskerCategoryDataset createDataset() {   
    final int CATEGORY_COUNT = PanelDatos.modelo3.getColumnCount()-1;           
    DefaultBoxAndWhiskerCategoryDataset result = new DefaultBoxAndWhiskerCategoryDataset();   
   /* for (int c = 0; c < CATEGORY_COUNT; c++) {   
    	//List values = createValueList(0, 20.0, VALUE_COUNT);   
        //result.add(values, "Series " + s, PanelDatos.modelo3.getColumnName(c+1));   
        BoxAndWhiskerItem item = new BoxAndWhiskerItem(null, 0, 100, 86.91, 60.47, 100, 0, 0, null);
        //mean, median, first quartile boundary, third quartile boundary, minimum regular, maximum regular, a minimum outlier, maximum outlier, list of outlier values
        result.add(item, "Series ", PanelDatos.modelo3.getColumnName(c+1));   
    }*/     
    
    String[] nombres_metodos = new String[CATEGORY_COUNT];
    String[] porcentajes_texto = new String[3];
    double[] porcentajes = new double[3];
    for(int i = 0; i<nombres_metodos.length; i++){
    	nombres_metodos[i] = PanelDatos.modelo3.getColumnName(i+1);
        
        porcentajes_texto[0] = PanelDatos.modelo3.getValueAt(4, i+1).toString();
        porcentajes_texto[0] = porcentajes_texto[0].substring(0,porcentajes_texto[0].length()-2);
        porcentajes_texto[0] = porcentajes_texto[0].replace(',', '.');          
        porcentajes[0] = Double.parseDouble(porcentajes_texto[0]);  
      //  System.out.println("porcentajes[0] = " + porcentajes[0]);

        porcentajes_texto[1] = PanelDatos.modelo3.getValueAt(5, i+1).toString();
        porcentajes_texto[1] = porcentajes_texto[1].substring(0,porcentajes_texto[1].length()-2);
        porcentajes_texto[1] = porcentajes_texto[1].replace(',', '.');          
        porcentajes[1] = Double.parseDouble(porcentajes_texto[1]); 
      //  System.out.println("porcentajes[1] = " + porcentajes[1]);
        
        porcentajes_texto[2] = PanelDatos.modelo3.getValueAt(6, i+1).toString();
        porcentajes_texto[2] = porcentajes_texto[2].substring(0,porcentajes_texto[2].length()-2);
        porcentajes_texto[2] = porcentajes_texto[2].replace(',', '.');          
        porcentajes[2] = Double.parseDouble(porcentajes_texto[2]); 
      //  System.out.println("porcentajes[2] = " + porcentajes[2]);
        
        BoxAndWhiskerItem item;
        if((porcentajes[0] == 0.0) && (porcentajes[1] == 0.0) && (porcentajes[2] == 0.0)){
        //	System.out.println("Gráfica caso óptimo");
        	item = new BoxAndWhiskerItem(null, 0, 100, 100-porcentajes[0],100-porcentajes[1], 100, 0, 0, null);
            //mean, median, first quartile boundary, third quartile boundary, minimum regular, maximum regular, a minimum outlier, maximum outlier, list of outlier values        
        }else if(porcentajes[2] > 0){
        //	System.out.println("Gráfica caso sobreóptimo");
        	if(PanelDatos.maximizar){
        		//item = new BoxAndWhiskerItem(null, 0, 100, porcentajes[0], 100, porcentajes[2], 0, 0, null);
        		item = new BoxAndWhiskerItem(null, 0, porcentajes[0], 100, 100, porcentajes[2], 0, 0, null);
        	}else{
        		item = new BoxAndWhiskerItem(null, 0, porcentajes[0], 100, 100, porcentajes[2], 0, 0, null);
        	}
        }else if(porcentajes[1] > 0){ //para minimizar
        //	System.out.println("Gráfica minimizar1 ");
        	item = new BoxAndWhiskerItem(null, 0, porcentajes[0], 100, 100, porcentajes[1], 0, 0, null);
        }else{
        //	System.out.println("Gráfica caso subóptimo");
        	item = new BoxAndWhiskerItem(null, 0, 100, porcentajes[0], porcentajes[1], 100, 0, 0, null);
        }
        
        result.add(item, "Series ", PanelDatos.modelo3.getColumnName(i+1));   
         
    }
    return result;   
}   

private static JFreeChart createChart(BoxAndWhiskerCategoryDataset dataset) {   
    
    CategoryAxis domainAxis = new CategoryAxis("");   
    NumberAxis rangeAxis = new NumberAxis("%");   
    CategoryItemRenderer renderer = new BoxAndWhiskerRenderer();   
    CategoryPlot plot = new CategoryPlot(   
        dataset, domainAxis, rangeAxis, renderer   
    );   
    JFreeChart chart = new JFreeChart(mensaje41b, plot);   
       
    chart.setBackgroundPaint(Color.white);   

    plot.setBackgroundPaint(Color.lightGray);   
    plot.setDomainGridlinePaint(Color.white);   
    plot.setDomainGridlinesVisible(true);   
    plot.setRangeGridlinePaint(Color.white);   

    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());      
       
    return chart;   
       
} 

public static void actualizarGraficas(){
	gestor_eventos.DesactivarGraficas();
	generaGrafico1();
	generaGrafico2();
}

public static int[] ordenarAscendente(double[] array){
	double[] copia = new double[array.length];
	int[] orden = new int[array.length];
	for(int k=0; k<array.length; k++){
		copia[k] = array[k];
	}
	
	Arrays.sort(array);
	for(int b=0; b<array.length; b++){
		System.out.println("array ordenado " + array[b]);
	}
	
	
	for(int p=0; p<array.length; p++){
		for(int m=0; m<array.length; m++){
			if(copia[p] == array[m]){
				orden[m] = p;
			}
		}
	}

	return orden;
}

public static int dameNumeroColumnas (int tabla){
    int retorno = 0;
    if (tabla == 1){
        retorno = modelo1.getColumnCount();
    } else if (tabla == 2){
        retorno = modelo2.getColumnCount();
    } else if (tabla == 3){
        retorno = modelo3.getColumnCount();
    } else if (tabla == 4){
        retorno = modeloDatos.getColumnCount();
    } else if (tabla == 5){
        retorno = modeloDatos2.getColumnCount();
    }
    return retorno;
}

public static int dameNumeroFilas (int tabla){
    int retorno = 0;
    if (tabla == 1){
        retorno = modelo1.getRowCount();
    } else if (tabla == 2){
        retorno = modelo2.getRowCount();
    } else if (tabla == 3){
        retorno = modelo3.getRowCount();
    } else if (tabla == 4){
        retorno = modeloDatos.getRowCount();
    } else if (tabla == 5){
        retorno = modeloDatos2.getRowCount();
    }
    return retorno;
}

public static String[] dameNombresColumnasTabla(int tabla){
    String[] retorno = null;
    if (tabla == 1){
        int columnas = modelo1.getColumnCount();
        String[] nombres = new String[columnas];
        for (int i = 0 ; i < columnas ; i++){
            nombres[i] = modelo1.getColumnName(i);
        }
        retorno = nombres;
    } else if (tabla == 2){
        int columnas = modelo2.getColumnCount();
        String[] nombres = new String[columnas];
        for (int i = 0 ; i < columnas ; i++){
            nombres[i] = modelo2.getColumnName(i);
        }
        retorno = nombres;
    } else if (tabla == 3){
        int columnas = modelo3.getColumnCount();
        String[] nombres = new String[columnas];
        for (int i = 0 ; i < columnas ; i++){
            nombres[i] = modelo3.getColumnName(i);
        }
        retorno = nombres;
    } else if (tabla == 4){
        int columnas = modeloDatos.getColumnCount();
        String[] nombres = new String[columnas];
        for (int i = 0 ; i < columnas ; i++){
            nombres[i] = modeloDatos.getColumnName(i);
        }
        retorno = nombres;
    } else if (tabla == 5){
        int columnas = modeloDatos2.getColumnCount();
        String[] nombres = new String[columnas];
        for (int i = 0 ; i < columnas ; i++){
            nombres[i] = modeloDatos2.getColumnName(i);
        }
        retorno = nombres;
    }
    return retorno;
}

public static Class[] dameTiposTabla(int tabla){
    Class[] retorno = null;
    if (tabla == 1){
        int columnas = modelo1.getColumnCount();
        Class[] clases = new Class[columnas];
        for (int i = 0 ; i < columnas ; i++){
            clases[i] = modelo1.getColumnClass(i);
        }
        retorno = clases;
    } else if (tabla == 2){
        int columnas = modelo2.getColumnCount();
        Class[] clases = new Class[columnas];
        for (int i = 0 ; i < columnas ; i++){
            clases[i] = modelo2.getColumnClass(i);
        }
        retorno = clases;
    } else if (tabla == 3){
        int columnas = modelo3.getColumnCount();
        Class[] clases = new Class[columnas];
        for (int i = 0 ; i < columnas ; i++){
            clases[i] = modelo3.getColumnClass(i);
        }
        retorno = clases;
    } else if (tabla == 4){
        int columnas = modeloDatos.getColumnCount();
        Class[] clases = new Class[columnas];
        for (int i = 0 ; i < columnas ; i++){
            try {
                if (modeloDatos.getColumnName(i).equals("byte[][]")){
                    clases[i] = Class.forName("[[B");
                } else if (modeloDatos.getColumnName(i).equals("byte[]")){
                    clases[i] = Class.forName("[B");
                } else if (modeloDatos.getColumnName(i).equals("byte")){
                    clases[i] = byte.class;
                } else if (modeloDatos.getColumnName(i).equals("Byte[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Byte;");
                } else if (modeloDatos.getColumnName(i).equals("Byte[]")){
                    clases[i] = Class.forName("[Ljava.lang.Byte;");
                } else if (modeloDatos.getColumnName(i).equals("Byte")){
                    clases[i] = Class.forName("java.lang.Byte");
                } else if (modeloDatos.getColumnName(i).equals("short[][]")){
                    clases[i] = Class.forName("[[S");
                } else if (modeloDatos.getColumnName(i).equals("short[]")){
                    clases[i] = Class.forName("[S");
                } else if (modeloDatos.getColumnName(i).equals("short")){
                    clases[i] = short.class;
                } else if (modeloDatos.getColumnName(i).equals("Short[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Short;");
                } else if (modeloDatos.getColumnName(i).equals("Short[]")){
                    clases[i] = Class.forName("[Ljava.lang.Short;");
                } else if (modeloDatos.getColumnName(i).equals("Short")){
                    clases[i] = Class.forName("java.lang.Short");
                } else if (modeloDatos.getColumnName(i).equals("int[][]")){
                    clases[i] = Class.forName("[[I");
                } else if (modeloDatos.getColumnName(i).equals("int[]")){
                    clases[i] = Class.forName("[I");
                } else if (modeloDatos.getColumnName(i).equals("int")){
                    clases[i] = int.class;
                } else if (modeloDatos.getColumnName(i).equals("Integer[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Integer;");
                } else if (modeloDatos.getColumnName(i).equals("Integer[]")){
                    clases[i] = Class.forName("[Ljava.lang.Integer;");
                } else if (modeloDatos.getColumnName(i).equals("Integer")){
                    clases[i] = Class.forName("java.lang.Integer");
                } else if (modeloDatos.getColumnName(i).equals("long[][]")){
                    clases[i] = Class.forName("[[J");
                } else if (modeloDatos.getColumnName(i).equals("long[]")){
                    clases[i] = Class.forName("[J");
                } else if (modeloDatos.getColumnName(i).equals("long")){
                    clases[i] = long.class;
                } else if (modeloDatos.getColumnName(i).equals("Long[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Long;");
                } else if (modeloDatos.getColumnName(i).equals("Long[]")){
                    clases[i] = Class.forName("[Ljava.lang.Long;");
                } else if (modeloDatos.getColumnName(i).equals("Long")){
                    clases[i] = Class.forName("java.lang.Long");
                } else if (modeloDatos.getColumnName(i).equals("float[][]")){
                    clases[i] = Class.forName("[[F");
                } else if (modeloDatos.getColumnName(i).equals("float[]")){
                    clases[i] = Class.forName("[F");
                } else if (modeloDatos.getColumnName(i).equals("float")){
                    clases[i] = float.class;
                } else if (modeloDatos.getColumnName(i).equals("Float[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Float;");
                } else if (modeloDatos.getColumnName(i).equals("Float[]")){
                    clases[i] = Class.forName("[Ljava.lang.Float;");
                } else if (modeloDatos.getColumnName(i).equals("Float")){
                    clases[i] = Class.forName("java.lang.Float");
                } else if (modeloDatos.getColumnName(i).equals("double[][]")){
                    clases[i] = Class.forName("[[D");
                } else if (modeloDatos.getColumnName(i).equals("double[]")){
                    clases[i] = Class.forName("[D");
                } else if (modeloDatos.getColumnName(i).equals("double")){
                    clases[i] = double.class;
                } else if (modeloDatos.getColumnName(i).equals("Double[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Double;");
                } else if (modeloDatos.getColumnName(i).equals("Double[]")){
                    clases[i] = Class.forName("[Ljava.lang.Double;");
                } else if (modeloDatos.getColumnName(i).equals("Double")){
                    clases[i] = Class.forName("java.lang.Double");
                } else if (modeloDatos.getColumnName(i).equals("boolean[][]")){
                    clases[i] = Class.forName("[[Z");
                } else if (modeloDatos.getColumnName(i).equals("boolean[]")){
                    clases[i] = Class.forName("[Z");
                } else if (modeloDatos.getColumnName(i).equals("boolean")){
                    clases[i] = boolean.class;
                } else if (modeloDatos.getColumnName(i).equals("Boolean[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Boolean;");
                } else if (modeloDatos.getColumnName(i).equals("Boolean[]")){
                    clases[i] = Class.forName("[Ljava.lang.Boolean;");
                } else if (modeloDatos.getColumnName(i).equals("Boolean")){
                    clases[i] = Class.forName("java.lang.Boolean");
                } else if (modeloDatos.getColumnName(i).equals("char[][]")){
                    clases[i] = Class.forName("[[C");
                } else if (modeloDatos.getColumnName(i).equals("char[]")){
                    clases[i] = Class.forName("[C");
                } else if (modeloDatos.getColumnName(i).equals("char")){
                    clases[i] = char.class;
                } else if (modeloDatos.getColumnName(i).equals("Character[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Character;");
                } else if (modeloDatos.getColumnName(i).equals("Character[]")){
                    clases[i] = Class.forName("[Ljava.lang.Character;");
                } else if (modeloDatos.getColumnName(i).equals("Character")){
                    clases[i] = Class.forName("java.lang.Character");
                } else if (modeloDatos.getColumnName(i).equals("String[][]")){
                    //clases[i] = Class.forName("[[LString");//[Ljava.lang.String;
                    clases[i] = Class.forName("[[Ljava.lang.String;");
                } else if (modeloDatos.getColumnName(i).equals("String[]")){
                    clases[i] = Class.forName("[Ljava.lang.String;");
                } else if (modeloDatos.getColumnName(i).equals("String")){
                    clases[i] = Class.forName("java.lang.String");
                }
                
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        retorno = clases;
    } else if (tabla == 5){
        int columnas = modeloDatos2.getColumnCount();
        Class[] clases = new Class[columnas];
        for (int i = 0 ; i < columnas ; i++){
            try {
                if (modeloDatos2.getColumnName(i).equals("byte[][]")){
                    clases[i] = Class.forName("[[B");
                } else if (modeloDatos2.getColumnName(i).equals("byte[]")){
                    clases[i] = Class.forName("[B");
                } else if (modeloDatos2.getColumnName(i).equals("byte")){
                    clases[i] = byte.class;
                } else if (modeloDatos2.getColumnName(i).equals("Byte[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Byte;");
                } else if (modeloDatos2.getColumnName(i).equals("Byte[]")){
                    clases[i] = Class.forName("[Ljava.lang.Byte;");
                } else if (modeloDatos2.getColumnName(i).equals("Byte")){
                    clases[i] = Class.forName("java.lang.Byte");
                } else if (modeloDatos2.getColumnName(i).equals("short[][]")){
                    clases[i] = Class.forName("[[S");
                } else if (modeloDatos2.getColumnName(i).equals("short[]")){
                    clases[i] = Class.forName("[S");
                } else if (modeloDatos2.getColumnName(i).equals("short")){
                    clases[i] = short.class;
                } else if (modeloDatos2.getColumnName(i).equals("Short[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Short;");
                } else if (modeloDatos2.getColumnName(i).equals("Short[]")){
                    clases[i] = Class.forName("[Ljava.lang.Short;");
                } else if (modeloDatos2.getColumnName(i).equals("Short")){
                    clases[i] = Class.forName("java.lang.Short");
                } else if (modeloDatos2.getColumnName(i).equals("int[][]")){
                    clases[i] = Class.forName("[[I");
                } else if (modeloDatos2.getColumnName(i).equals("int[]")){
                    clases[i] = Class.forName("[I");
                } else if (modeloDatos2.getColumnName(i).equals("int")){
                    clases[i] = int.class;
                } else if (modeloDatos2.getColumnName(i).equals("Integer[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Integer;");
                } else if (modeloDatos2.getColumnName(i).equals("Integer[]")){
                    clases[i] = Class.forName("[Ljava.lang.Integer;");
                } else if (modeloDatos2.getColumnName(i).equals("Integer")){
                    clases[i] = Class.forName("java.lang.Integer");
                } else if (modeloDatos2.getColumnName(i).equals("long[][]")){
                    clases[i] = Class.forName("[[J");
                } else if (modeloDatos2.getColumnName(i).equals("long[]")){
                    clases[i] = Class.forName("[J");
                } else if (modeloDatos2.getColumnName(i).equals("long")){
                    clases[i] = long.class;
                } else if (modeloDatos2.getColumnName(i).equals("Long[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Long;");
                } else if (modeloDatos2.getColumnName(i).equals("Long[]")){
                    clases[i] = Class.forName("[Ljava.lang.Long;");
                } else if (modeloDatos2.getColumnName(i).equals("Long")){
                    clases[i] = Class.forName("java.lang.Long");
                } else if (modeloDatos2.getColumnName(i).equals("float[][]")){
                    clases[i] = Class.forName("[[F");
                } else if (modeloDatos2.getColumnName(i).equals("float[]")){
                    clases[i] = Class.forName("[F");
                } else if (modeloDatos2.getColumnName(i).equals("float")){
                    clases[i] = float.class;
                } else if (modeloDatos2.getColumnName(i).equals("Float[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Float;");
                } else if (modeloDatos2.getColumnName(i).equals("Float[]")){
                    clases[i] = Class.forName("[Ljava.lang.Float;");
                } else if (modeloDatos2.getColumnName(i).equals("Float")){
                    clases[i] = Class.forName("java.lang.Float");
                } else if (modeloDatos2.getColumnName(i).equals("double[][]")){
                    clases[i] = Class.forName("[[D");
                } else if (modeloDatos2.getColumnName(i).equals("double[]")){
                    clases[i] = Class.forName("[D");
                } else if (modeloDatos2.getColumnName(i).equals("double")){
                    clases[i] = double.class;
                } else if (modeloDatos2.getColumnName(i).equals("Double[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Double;");
                } else if (modeloDatos2.getColumnName(i).equals("Double[]")){
                    clases[i] = Class.forName("[Ljava.lang.Double;");
                } else if (modeloDatos2.getColumnName(i).equals("Double")){
                    clases[i] = Class.forName("java.lang.Double");
                } else if (modeloDatos2.getColumnName(i).equals("boolean[][]")){
                    clases[i] = Class.forName("[[Z");
                } else if (modeloDatos2.getColumnName(i).equals("boolean[]")){
                    clases[i] = Class.forName("[Z");
                } else if (modeloDatos2.getColumnName(i).equals("boolean")){
                    clases[i] = boolean.class;
                } else if (modeloDatos2.getColumnName(i).equals("Boolean[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Boolean;");
                } else if (modeloDatos2.getColumnName(i).equals("Boolean[]")){
                    clases[i] = Class.forName("[Ljava.lang.Boolean;");
                } else if (modeloDatos2.getColumnName(i).equals("Boolean")){
                    clases[i] = Class.forName("java.lang.Boolean");
                } else if (modeloDatos2.getColumnName(i).equals("char[][]")){
                    clases[i] = Class.forName("[[C");
                } else if (modeloDatos2.getColumnName(i).equals("char[]")){
                    clases[i] = Class.forName("[C");
                } else if (modeloDatos2.getColumnName(i).equals("char")){
                    clases[i] = char.class;
                } else if (modeloDatos2.getColumnName(i).equals("Character[][]")){
                    clases[i] = Class.forName("[[Ljava.lang.Character;");
                } else if (modeloDatos2.getColumnName(i).equals("Character[]")){
                    clases[i] = Class.forName("[Ljava.lang.Character;");
                } else if (modeloDatos2.getColumnName(i).equals("Character")){
                    clases[i] = Class.forName("java.lang.Character");
                } else if (modeloDatos2.getColumnName(i).equals("String[][]")){
                    //clases[i] = Class.forName("[[LString");//[Ljava.lang.String;
                    clases[i] = Class.forName("[[Ljava.lang.String;");
                } else if (modeloDatos2.getColumnName(i).equals("String[]")){
                    clases[i] = Class.forName("[Ljava.lang.String;");
                } else if (modeloDatos2.getColumnName(i).equals("String")){
                    clases[i] = Class.forName("java.lang.String");
                }
                
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        retorno = clases;
    }
    return retorno;
}

public static void inicializarTabla1(){
    
        modelo1 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modelo1.ponerEspanol();
        } else {
            modelo1.ponerIngles();
        }
        modelo1.setTable(tabla1);
        String[] nombres = new String[3];
        Class[] tipos = new Class[3];
        try {
            nombres[0] = mensaje10;
            tipos[0] = Class.forName("java.lang.String");
            nombres[1] = mensaje11;
            tipos[1] = Class.forName("java.lang.String");
            nombres[2] = mensaje12;
            tipos[2] = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        modelo1.setColumnNames(nombres);
        modelo1.setColumnClass(tipos);
        tabla1.setModel(modelo1);
        control1 = new ControlTabla(modelo1);
        tabla1.repaint();
}

public static void inicializarTabla2(String[] metodos, String tipo){
        contadorOptimosMaximos = new int[metodos.length-1];
        contadorOptimosMinimos = new int[metodos.length-1];
        contadorSuboptimosMaximos = new int[metodos.length-1];
        contadorSuboptimosMinimos = new int[metodos.length-1];
        contadorSuperoptimosMaximos = new int[metodos.length-1];
        contadorSuperoptimosMinimos = new int[metodos.length-1];
        sumatoriosDesviacion = new double[metodos.length-1];
        desviacionesMaximaInferior = new float[metodos.length];
        desviacionesMaximaSuperior = new float[metodos.length];
        for (int j=0; j<metodos.length-1; j++){
            contadorOptimosMaximos[j] = 0;
            contadorOptimosMinimos[j] = 0;
            contadorSuboptimosMaximos[j] = 0;
            contadorSuboptimosMinimos[j] = 0;
            contadorSuperoptimosMaximos[j] = 0;
            contadorSuperoptimosMinimos[j] = 0;
            sumatoriosDesviacion [j] = 0;
            desviacionesMaximaInferior [j] = 0;
            desviacionesMaximaSuperior [j] = 0;
        }
    
        modelo2 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modelo2.ponerEspanol();
        } else {
            modelo2.ponerIngles();
        }
        modelo2.setTable(tabla2);
        String[] nombres = new String[metodos.length];
        Class[] tipos = new Class[metodos.length];
        
        for (int i=0; i<metodos.length; i++){

            
            String nombre = metodos[i].toString();
            
            nombre = nombre.replace("class", "");
            nombre = nombre.replace("public", "");
            nombre = nombre.replace("private", "");
            nombre = nombre.replace("static", "");
            nombre = nombre.replace("java.", "");
            nombre = nombre.replace("lang.", "");
            nombre = nombre.replace(".", "");
            nombre = nombre.replace(" ", "");
            
            nombres[i] = nombre;
            
            try {
                tipos[i] = Class.forName("java.lang.String");
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            
        }
        nombres[0] = "Num.";
        modelo2.setColumnNames(nombres);
        modelo2.setColumnClass(tipos);
        tabla2.setModel(modelo2);
        control2 = new ControlTabla(modelo2);
        tabla2.repaint();
        modelo2.setCellColor(maximizar, optimus, tabla3);
        modelo2.escuchadorSeleccion(gestor_eventos, tabla2);
}

public static void inicializarTabla3(String[] metodos){
    
        modelo3 = new ModeloTabla();
        if (estado.dameIdioma().equals("espanol")){
            modelo3.ponerEspanol();
        } else {
            modelo3.ponerIngles();
        }
        modelo3.setTable(tabla3);
        String[] nombres = new String[metodos.length];
        Class[] tipos = new Class[metodos.length];
        
        nombres[0] = mensaje13;
        for (int i=0; i<metodos.length; i++){
            
            if (i == 0){
                nombres[i] = mensaje13;
            }else{
                String nombre = metodos[i].toString();
            
                nombre = nombre.replace("class", "");
                nombre = nombre.replace("public", "");
                nombre = nombre.replace("private", "");
                nombre = nombre.replace("static", "");
                nombre = nombre.replace("java.", "");
                nombre = nombre.replace("lang.", "");
                nombre = nombre.replace(".", "");
            
                nombres[i] = nombre;
            }
            
            try {
                tipos[i] = Class.forName("java.lang.String");
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            
        }
        modelo3.setColumnNames(nombres);
        modelo3.setColumnClass(tipos);
        tabla3.setModel(modelo3);
        control3 = new ControlTabla(modelo3);
        tabla3.repaint();
}

public static void inicializarTablaDatos(String[] cabecera, Class[] clases){
    modeloDatos.setColumnNames(cabecera);
    modeloDatos.setColumnClass(clases);
    tablaDatos.setModel(modeloDatos);
    controlDatos = new ControlTabla(modeloDatos);
    
    modeloDatos2.setColumnNames(cabecera);
    modeloDatos2.setColumnClass(clases);
    tablaDatos2.setModel(modeloDatos2);
    controlDatos2 = new ControlTabla(modeloDatos2);
}

public static Boolean sonTiposIguales(Class[] nuevos, int tabla){
    int num = 0;
    boolean iguales = true;
    int numColum = dameNumeroColumnas(tabla) - 1;

    if (nuevos.length == numColum){
         for (int i = 0; i < nuevos.length; i++){

             if (nuevos[i] != dameTiposTabla(tabla)[i+1]){ //test03 añadido +1
                 iguales = false;
             }
         }
    }else{
         iguales = false;
    }
    return iguales;
}

public static Boolean sonTiposIguales(ModeloTabla modelo1, ModeloTabla modelo2){
    int num = 0;
    boolean iguales = true;
    if (modelo1.getColumnCount() == modelo2.getColumnCount()){
         for (int i = 0; i < modelo1.getColumnCount(); i++){ 
             if (!(modelo1.getColumnName(i).replace(" ", "").equals(modelo2.getColumnName(i).replace(" ", "")))){
                 iguales = false;
             }
         }
    }else{
         iguales = false;
    }
    return iguales;
}

public static Boolean estaInicializadaTabla(int tabla){
    boolean retorno = false;
    if (tabla == 1){
        retorno = modelo1.estaInicializadaTabla();
    } else if (tabla == 2){
        retorno = modelo2.estaInicializadaTabla();
    } else if (tabla == 3){
        //retorno = modelo3.estaInicializadaTabla();
        if (modelo3.getRowCount()>0){
            retorno = true;
        }else{
            retorno = false;
        }
    }
    return retorno;
}

public static void insertarTabla(Object[] objetos, int tabla){
    if (tabla == 1){
        Fila nueva_fila = new Fila(objetos);
        modelo1.anhadeFila(nueva_fila);
    } else if (tabla == 2){
        Fila nueva_fila = new Fila(objetos);
        modelo2.anhadeFila(nueva_fila);
        //################ SCROLL A LA ULTIMA FILA AL INSERTAR #################
        int row =  tabla2.getRowCount () - 1;
        
        try{
            SwingUtilities.invokeAndWait(new actualizarContadoresAlInsertar());
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        Rectangle rect = tabla2.getCellRect(row, 0, true);
        tabla2.scrollRectToVisible(rect);
        tabla2.clearSelection();
        tabla2.setRowSelectionInterval(row, row);
        tabla2.clearSelection();
        //######################################################################
    } else if (tabla == 3){
        Fila nueva_fila = new Fila(objetos);
        modelo3.anhadeFila(nueva_fila);
    } else if (tabla == 4){
        Fila nueva_fila = new Fila(objetos);
        modeloDatos.anhadeFila(nueva_fila);
    } else if (tabla == 5){
        Fila nueva_fila = new Fila(objetos);
        modeloDatos2.anhadeFila(nueva_fila);
    }
    System.out.println("11111");
    gestor_eventos.ActivarTablaConDatos();
}

public static void insertarTabla(Object[] objetos, int tabla, String oculto){
    if (tabla == 1){
        Fila nueva_fila = new Fila(objetos);
        modelo1.anhadeFila(nueva_fila, oculto);
    } else if (tabla == 2){
        Fila nueva_fila = new Fila(objetos);
        modelo2.anhadeFila(nueva_fila, oculto);
        //################ SCROLL A LA ULTIMA FILA AL INSERTAR #################
        int row =  tabla2.getRowCount () - 1;
        
        try{
            SwingUtilities.invokeAndWait(new actualizarContadoresAlInsertar());
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        Rectangle rect = tabla2.getCellRect(row, 0, true);
        tabla2.scrollRectToVisible(rect);
        tabla2.clearSelection();
        tabla2.setRowSelectionInterval(row, row);
        tabla2.clearSelection();
        //######################################################################
    } else if (tabla == 3){
        Fila nueva_fila = new Fila(objetos);
        modelo3.anhadeFila(nueva_fila, oculto);
    } else if (tabla == 4){
        Fila nueva_fila = new Fila(objetos);
        modeloDatos.anhadeFila(nueva_fila, oculto);
    } else if (tabla == 5){
        Fila nueva_fila = new Fila(objetos);
        modeloDatos2.anhadeFila(nueva_fila, oculto);
    }
    System.out.println("22222");
    gestor_eventos.ActivarTablaConDatos();
}

public static void insertarTabla(Object[] objetos, int tabla, String oculto, Integer indiceOculto){
	if (tabla == 1){
        Fila nueva_fila = new Fila(objetos);
        modelo1.anhadeFila(nueva_fila, oculto, indiceOculto);
    } else if (tabla == 2){
        Fila nueva_fila = new Fila(objetos);
        modelo2.anhadeFila(nueva_fila, oculto, indiceOculto);
        //################ SCROLL A LA ULTIMA FILA AL INSERTAR #################
        int row =  tabla2.getRowCount () - 1;
        try{
            SwingUtilities.invokeAndWait(new actualizarContadoresAlInsertar());
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        Rectangle rect = tabla2.getCellRect(row, 0, true);
        tabla2.scrollRectToVisible(rect);
        tabla2.clearSelection();
        tabla2.setRowSelectionInterval(row, row);
        tabla2.clearSelection();
        //######################################################################
    } else if (tabla == 3){
        Fila nueva_fila = new Fila(objetos);
        modelo3.anhadeFila(nueva_fila, oculto, indiceOculto);
    } else if (tabla == 4){
        Fila nueva_fila = new Fila(objetos);
        modeloDatos.anhadeFila(nueva_fila, oculto, indiceOculto);
    } else if (tabla == 5){
        Fila nueva_fila = new Fila(objetos);
        modeloDatos2.anhadeFila(nueva_fila, oculto, indiceOculto);
    }
}

public static boolean existeFila(Fila fila){
//	System.out.println("existeFila");
	
	int coincidencias = 0;
	//System.out.println("Dato a insertar: " );
	for(int i=0;i<fila.dameNumeroCampos();i++){
	//	System.out.println(fila.dameCampoI(i));
	}
	//Recorrer modelo de datos y compara 
	if (modeloDatos.getRowCount() != 0){
	//	System.out.println("SÍ HAY DATOS");
		int numeroCampos = fila.dameNumeroCampos();
	//	System.out.println("numeroCampos= " + numeroCampos);
		String []insertar = new String[numeroCampos];
		
		for(int i=1;i<numeroCampos;i++){ // =0 test01
			insertar[i] = fila.dameCampoI(i).toString();
		//	System.out.println("insertar[i]= " + insertar[i]);
		}

		for(int row=0; row<modeloDatos.getRowCount(); row++){
			coincidencias = 0;
		//	System.out.println("modelo1.getColumnCount()= " + modelo1.getColumnCount());
			for(int column=1; column<numeroCampos;column++){
			//	System.out.println("row=" + row + " column=" + column);
				String existente = modeloDatos.getValueAt(row, column).toString();
			//	System.out.println(" insertar:" + insertar[column]);
			//	System.out.println(" existente:" + existente);

				if(insertar[column].equals(existente)){
			//		System.out.println("Coincidencia en columna!");
					coincidencias = coincidencias + 1;
				}				
			}
			//System.out.println(" coincidencias="+coincidencias + " modelo1.getColumnCount()="+modelo1.getColumnCount());
			if(coincidencias == numeroCampos-1){  //coincidencias == modelo1.getColumnCount()
		//		System.out.println("Coincidencia de fila completa");
				return true;
			}
		}
		return false;
	}
	else{
		return false;
	}
}

public static void resetearTabla(int tabla){
    if (tabla == 1){
        modelo1.reseteaTabla();
    } else if (tabla == 2){
        modelo2.reseteaTabla();
    } else if (tabla == 3){
        modelo3.reseteaTabla();
    } else if (tabla == 4){
        modeloDatos.reseteaTabla();
    } else if (tabla == 5){
        modeloDatos2.reseteaTabla();
    }
    gestor_eventos.ComprobarSiTablasConDatos();
}

public static void resetTotalTablas(){
        modelo1.resetTotal();
        modelo2.resetTotal();
        modelo3.resetTotal();
        modeloDatos.resetTotal();
        modeloDatos2.resetTotal();
        gestor_eventos.DesactivarTablaConDatos();
}

public static void pararEjecucion(){
	if (!Editor.dameParado() == true){
		Editor.ponParado(true);
	}
	//Editor.tarea_dameMetodos3.currentThread().interrupt();
}

public static void borrarEnTabla(int tabla){
    if (tabla == 1){
        modelo1.borraFilaSeleccionada (panel_codigo);
    } else if (tabla == 2){
        modelo2.borraFilaSeleccionada (panel_codigo);
        actualizarTablas();
    } else if (tabla == 3){ //#############BORRAR SESION####################
        JLabel exito = new JLabel(mensaje25);
        int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje26,
             JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(PanelDatos.class.getResource("imagenes/borrar_sesion.gif")),
             botones, botones[0]);
        if (respuesta == JOptionPane.OK_OPTION){
             resetearTabla(1);
             resetearTabla(2);
             resetearTabla(3);
             resetearTabla(4);
             resetearTabla(5);
             actualizarTablas();
             PanelDatos.modeloDatos.actualizaId(); 
             gestor_eventos.DesactivarExportacionDatos();
             gestor_eventos.DesactivarBorraFilasDatos();
             gestor_eventos.DesactivarEjecuciones();
             gestor_eventos.DesactivarTablaConDatos(); 
             gestor_eventos.DesactivarGraficas(); 
             panel_tablas.setSelectedIndex(0);
        }
    }else if (tabla == 4){ //borrar datos
    	modeloDatos.borraFilaSeleccionada(panel_codigo);

    	if (modeloDatos.getRowCount() == 0){
    		 gestor_eventos.DesactivarExportacionDatos();
    		 gestor_eventos.DesactivarBorraFilasDatos();
    		 gestor_eventos.DesactivarEjecuciones();
    	}
    }
    gestor_eventos.ComprobarSiTablasConDatos();
}

public static int[] dameSeleccion(int tabla){
	int[] seleccion = null;
    if (tabla == 4){
    	seleccion = modeloDatos.dameFilaSeleccionada(panel_codigo);
    }
    return seleccion;
}

public static String getFilaImprimible(int tabla, int fila){
    if (tabla == 1){
        return modelo1.getFilaImprimible(fila);
    } else if (tabla == 2){
        return modelo2.getFilaImprimible(fila);
    } else if (tabla == 3){
        return modelo3.getFilaImprimible(fila);
    } else if (tabla == 4){
        return modeloDatos.getFilaImprimible(fila);
    } else if (tabla == 5){
        return modeloDatos2.getFilaImprimible(fila);
    } else {
        return "";
    }
}

public static void exportarDatos(){
	//System.out.println("exportarDatos");
    JFileChooser fc;
    File f;
    boolean result = false;

    //presenta un dialogo modal para que el usuario seleccione un archivo
    fc = getJFileChooserXml_export();    //se obtiene un JFileChooser
    int state = fc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
        f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
        if (fc.getFileFilter().getDescription().contentEquals(mensaje4)){
            f = new File(f.getAbsolutePath()+".xml");
            setCurrentDirectoryXml(new File(carpetaDelArchivo(f)));
            XMLImporter xmlimporter = new XMLImporter(tablaDatos, modeloDatos, controlDatos, f);
            Integer resultado = xmlimporter.escribirXml();
            if (resultado == 0){
                JLabel exito = new JLabel(mensaje27);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }else{
                JLabel exito = new JLabel(mensaje28);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }
        }else if(fc.getFileFilter().getDescription().contentEquals(mensaje41)){
            f = new File(f.getAbsolutePath()+".txt");                    
            setCurrentDirectoryXml(new File(carpetaDelArchivo(f)));
            
            TXTImporter txtimporter = new TXTImporter(tablaDatos, modeloDatos, controlDatos, f);
            Integer resultado = txtimporter.escribirTxt();
            if (resultado == 0){
                JLabel exito = new JLabel(mensaje27);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }else{
                JLabel exito = new JLabel(mensaje28);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }
        }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4a)){
        	if(!f.exists()){
        		f = new File(f.getAbsolutePath()+".jpg");
        		setCurrentDirectoryXml(new File(carpetaDelArchivo(f)));
        	}
        //	int[] datosElegidos = PanelDatos.dameFilasSeleccionadas2(true);

        	tablaDatos.setSize(tablaDatos.getPreferredSize());
       
        	//Creamos una Imagen con el tamaño del componente
        	BufferedImage imagen = new BufferedImage(tablaDatos.getWidth(), tablaDatos.getHeight(), BufferedImage.TYPE_INT_RGB);
        	//Hacemos que el componente se pinte en el Graphics de la imagen
        	tablaDatos.paint(imagen.getGraphics());
        	try{
            	//Guardamos la imagen      
        		result = ImageIO.write(imagen, "jpg", f);
        		System.out.println("RESULT: " + result);        	
        	}catch(Exception e){
        		System.out.println("Error: " + e);
        	} 
        	if (result == true){
        		JLabel exito = new JLabel(mensaje27);
        		JOptionPane.showOptionDialog(null, exito, mensaje29,
        				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
        				new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
        				boton, boton[0]);
        	}else{
        		JLabel exito = new JLabel(mensaje28);
        		JOptionPane.showOptionDialog(null, exito, mensaje29,
        				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
        				new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
        				boton, boton[0]);
        	}
        }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4b)){
        	if(!f.exists()){
        		f = new File(f.getAbsolutePath()+".png");
        		setCurrentDirectoryXml(new File(carpetaDelArchivo(f)));
        	}
        	
        	tablaDatos.setSize(tablaDatos.getPreferredSize());

        	BufferedImage imagen = new BufferedImage(tablaDatos.getPreferredSize().width, tablaDatos.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
        	tablaDatos.paint(imagen.getGraphics());
        	try{
        		result = ImageIO.write(imagen, "png", f);
        		System.out.println("RESULT: " + result);
        	}catch(Exception e){
        		System.out.println("Error: " + e);
        	}                    
            if (result == true){
                JLabel exito = new JLabel(mensaje27);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }else{
                JLabel exito = new JLabel(mensaje28);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }
        }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4c)){
        	if(!f.exists()){
        		f = new File(f.getAbsolutePath()+".gif");
        		setCurrentDirectoryXml(new File(carpetaDelArchivo(f)));
        	}
            
        	tablaDatos.setSize(tablaDatos.getPreferredSize());
        	
        	BufferedImage imagen = new BufferedImage(tablaDatos.getWidth(), tablaDatos.getHeight(), BufferedImage.TYPE_INT_RGB);
        	tablaDatos.paint(imagen.getGraphics());
        	try{
        		result = ImageIO.write(imagen, "gif", f);
        		System.out.println("RESULT: " + result);
        	}catch(Exception e){
        		System.out.println("Error: " + e);
        	} 
                  
            if (result = true){
                JLabel exito = new JLabel(mensaje27);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }else{
                JLabel exito = new JLabel(mensaje28);
                JOptionPane.showOptionDialog(null, exito, mensaje29,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }
    }    
    }
}

public static void exportarTabla(int tabla){
    JFileChooser fc;
    File f;
    boolean result = false;
    
    if (tabla == 1){
        //presenta un dialogo modal para que el usuario seleccione un archivo
        fc = getJFileChooserExcelImages();    //se obtiene un JFileChooser      
        int state = fc.showSaveDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
            f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
            
            if(fc.getFileFilter().getDescription().contentEquals(mensaje4a)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".jpg");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	BufferedImage imagen = new BufferedImage(tabla1.getWidth(), (int) tabla1.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            	tabla1.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "jpg", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
            				boton, boton[0]);
            	}
            	
            }
            else if(fc.getFileFilter().getDescription().contentEquals(mensaje4b)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".png");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	BufferedImage imagen = new BufferedImage(tabla1.getWidth(), tabla1.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
            	tabla1.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "png", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
            				boton, boton[0]);
            	}
            }
            else if(fc.getFileFilter().getDescription().contentEquals(mensaje4c)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".gif");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	BufferedImage imagen = new BufferedImage(tabla1.getWidth(), tabla1.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
            	tabla1.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "gif", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
            				boton, boton[0]);
            	}
            }           
            else if (fc.getFileFilter().getDescription().contentEquals(mensaje32)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".xls");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	ExcelTableExporter excelExporter = new ExcelTableExporter(tabla1, modelo1, f,t1);
            	Integer resultado = excelExporter.export();     
                if (resultado == 0) {
                    JLabel exito = new JLabel(mensaje21);
                    JOptionPane.showOptionDialog(null, exito, mensaje23,
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
                        boton, boton[0]);
                }
                if (resultado == 1) {
                    JLabel exito = new JLabel(mensaje22);
                    JOptionPane.showOptionDialog(null, exito, mensaje23,
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_result.png")),
                        boton, boton[0]);
                }
            }

        }
    }else if (tabla == 2){
        //presenta un dialogo modal para que el usuario seleccione un archivo      
        fc = getJFileChooserExcelImages();    //se obtiene un JFileChooser      
        int state = fc.showSaveDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
            f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
            
            if(fc.getFileFilter().getDescription().contentEquals(mensaje4a)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".jpg");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	BufferedImage imagen = new BufferedImage(tabla2.getWidth(), (int) tabla2.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            	tabla2.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "jpg", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	}
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}
            }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4b)){   
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".png");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	BufferedImage imagen = new BufferedImage(tabla2.getWidth(), (int) tabla2.getPreferredSize().getHeight(),  BufferedImage.TYPE_INT_RGB);
            	tabla2.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "png",f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}
            	
            }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4c)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".gif");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	BufferedImage imagen = new BufferedImage(tabla2.getWidth(), (int) tabla2.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            	tabla2.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "gif", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}
            }else if(fc.getFileFilter().getDescription().contentEquals(mensaje32)){    
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".xls");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	ExcelTableExporter excelExporter = new ExcelTableExporter(tabla2,modelo3, f,t2);
            	Integer resultado = excelExporter.export();
            	if (resultado == 0) {
            		JLabel exito = new JLabel(mensaje21);
            		JOptionPane.showOptionDialog(null, exito, mensaje23,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]); 
            	}
            	if (resultado == 1) {
            		JLabel exito = new JLabel(mensaje22);
            		JOptionPane.showOptionDialog(null, exito, mensaje23,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_hist.png")),
            				boton, boton[0]);
            	}
            }
        }	                    
    } else if (tabla == 3){	
    	//presenta un dialogo modal para que el usuario seleccione un archivo      
        fc = getJFileChooserExcelImages();    //se obtiene un JFileChooser      
        int state = fc.showSaveDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
            f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
            
            if(fc.getFileFilter().getDescription().contentEquals(mensaje4a)){
            	f = new File(f.getAbsolutePath()+".jpg");
            	setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	
            	BufferedImage imagen = new BufferedImage(tabla3.getWidth(), (int) tabla3.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            	tabla3.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "jpg", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}
            }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4b)){
            	f = new File(f.getAbsolutePath()+".png");
            	setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	
            	BufferedImage imagen = new BufferedImage(tabla3.getWidth(), (int) tabla3.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            	tabla3.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "png", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}
            }else if(fc.getFileFilter().getDescription().contentEquals(mensaje4c)){
            	f = new File(f.getAbsolutePath()+".gif");
            	setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	
            	BufferedImage imagen = new BufferedImage(tabla3.getWidth(), (int) tabla3.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            	tabla3.paint(imagen.getGraphics());
            	try{
            		result = ImageIO.write(imagen, "gif", f);
            	}catch(Exception e){
            		System.out.println("Error: " + e);
            	} 
            	if (result == true){
            		JLabel exito = new JLabel(mensaje27);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}else{
            		JLabel exito = new JLabel(mensaje28);
            		JOptionPane.showOptionDialog(null, exito, mensaje29,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}
            }else if(fc.getFileFilter().getDescription().contentEquals(mensaje32)){
            	if(!f.exists()){
            		f = new File(f.getAbsolutePath()+".xls");
            		setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            	}
            	ExcelTableExporter excelExporter = new ExcelTableExporter(tabla3, modelo3, f, t3);
            	Integer resultado = excelExporter.export();
            	if (resultado == 0) {
            		JLabel exito = new JLabel(mensaje21);
            		JOptionPane.showOptionDialog(null, exito, mensaje23,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]); 
            	}
            	if (resultado == 1) {
            		JLabel exito = new JLabel(mensaje22);
            		JOptionPane.showOptionDialog(null, exito, mensaje23,
            				JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            				new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_summary.png")),
            				boton, boton[0]);
            	}
            }
        }
        
    } else if(tabla == 4){
        fc = getJFileChooserExcel();    //se obtiene un JFileChooser
        
        int state = fc.showSaveDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
            f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
            if(!f.exists()){
            	f = new File(f.getAbsolutePath()+".xls");
            	setCurrentDirectoryExcel(new File(carpetaDelArchivo(f)));
            }
            
            List<JTable> tables = new ArrayList<JTable>();
            tables.add(tablaDatos);
            tables.add(tabla1);
            tables.add(tabla2);
            tables.add(tabla3);
            
            List<ModeloTabla> models = new ArrayList<ModeloTabla>();
            models.add(modeloDatos);
            models.add(modelo1);
            models.add(modelo3);
            models.add(modelo3);
            
            List<String> nombresTab = new ArrayList<String>();
            nombresTab.add("Datos");
            nombresTab.add("Resultado");
            nombresTab.add("Histórica");
            nombresTab.add("Resumen");
       
            ExcelTableExporter excelExporter = new ExcelTableExporter(tables,  models, f, nombresTab);           
            Integer resultado = excelExporter.export2();
            
            if (resultado == 0) {
                JLabel exito = new JLabel(mensaje21);
                JOptionPane.showOptionDialog(null, exito, mensaje23,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_todo.png")),
                    boton, boton[0]); 
            }
            if (resultado == 1) {
                JLabel exito = new JLabel(mensaje22);
                JOptionPane.showOptionDialog(null, exito, mensaje23,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/exportar_todo.png")),
                    boton, boton[0]);
            }
        }
    }
}

public static void tecladoDatos(){
	
    if (estado.dameIdioma().equals("espanol")){
        mensaje43 = "Filas añadidas: 1";
        mensaje43a = "El dato ya existe o tiene un formato inválido";
    } else {
        mensaje43 = "Added rows: 1"; 
        mensaje43a = "The data already exists or its format is invalid";
    }
    
    Object[] objetos = new Object[dameNumeroColumnas(4)];
    JPanel panelito = new JPanel();
    panelito.setLayout(new BoxLayout(panelito,BoxLayout.X_AXIS));
    panelito.setBorder(new TitledBorder(mensaje14));
    JLabel[] tipos = new JLabel[dameNumeroColumnas(4)];
    JERoundTextField[] datos = new JERoundTextField[dameNumeroColumnas(4)];
    String[] nombres = new String[dameNumeroColumnas(4)];
    nombres = dameNombresColumnasTabla(4);
    for (int i=1; i<dameNumeroColumnas(4); i++){ //test01
  		tipos[i] = new JLabel(nombres[i]);
   		datos[i] = new JERoundTextField();
        datos[i].setPreferredSize(new Dimension(140,20));
        if(nombres[i].equals("int[]")){
        	datos[i].setToolTipText("Ej. {1,90,-5,439}");
        }else if(nombres[i].equals("int")){
        	datos[i].setToolTipText("Ej. 43");
        }else if(nombres[i].equals("double[]") || nombres[i].equals("float[]")){
        	datos[i].setToolTipText("Ej. {7.3,5.0,-4}");
        }else if(nombres[i].equals("double")){
        	datos[i].setToolTipText("Ej. 45.6");
        }else if(nombres[i].equals("boolean[]")){
        	datos[i].setToolTipText("Ej. {true,false,false}");
        }else if(nombres[i].equals("boolean")){
        	datos[i].setToolTipText("Ej. true");
        }else if(nombres[i].equals("float[]")){
        	datos[i].setToolTipText("Ej. {8.23,4,-45}");
        }else if(nombres[i].equals("float")){
        	datos[i].setToolTipText("Ej. 8.23");
        }else if(nombres[i].equals("char")){
        	datos[i].setToolTipText("Ej. %");
        }else if(nombres[i].equals("char[]")){
        	datos[i].setToolTipText("Ej. {%,$}");
        }else if(nombres[i].equals("short[]")){
        	datos[i].setToolTipText("Ej. {12,-16}");
        }else if(nombres[i].equals("short")){
        	datos[i].setToolTipText("Ej. 12");
        }else if(nombres[i].equals("long[]")){
        	datos[i].setToolTipText("Ej. {6172,-1,234}");
        }else if(nombres[i].equals("long")){
        	datos[i].setToolTipText("Ej. 6549");
        } 
         
        datos[i].setText("");
        panelito.add(tipos[i]);
        panelito.add(datos[i]);
    }

    int respuesta = JOptionPane.showOptionDialog(null, panelito, mensaje17,
             JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(PanelDatos.class.getResource("imagenes/datos_teclado.gif")),
             botones, botones[0]);
    
    if (respuesta == JOptionPane.OK_OPTION){
        boolean datoCompleto = true;
        String[] paraFila = new String[dameNumeroColumnas(4)];
        for (int i=1; i<dameNumeroColumnas(4); i++){ //test01
            paraFila[i] = datos[i].getText();
            if (datos[i].getText().contentEquals("")){
                datoCompleto = false;
            }
        }        
        
        if (datoCompleto){
            Fila fila = new Fila(paraFila);
            //resetearTabla(1);
            //resetearTabla(4);
            boolean existe = modeloDatos.anhadeFila (fila, "noEjecutado");
            
            //############ COPIA DE DATOS #####################################
            resetearTabla(5);
            for (int f=0; f<modeloDatos.getRowCount(); f++){
                Object[] paraFil = new Object[dameNumeroColumnas(4)];
                for (int c=0; c<dameNumeroColumnas(4); c++){
                    paraFil[c] = modeloDatos.getValueAt(f, c);
                }
                Fila fil = new Fila(paraFil);
                modeloDatos2.anhadeFila (fil, modeloDatos.getOcultoAt(f));
            }
            //#################################################################
            //??????????????????????????????????????????????????????????????????
            widthTabla = tablaDatos.packColumns(2);
            if (widthTabla+30<width-200 && tablaDatos.heigth()+30<height-200){
                panelDatos.setPreferredSize(new Dimension(widthTabla+12, tablaDatos.heigth()+12));
            }else if (widthTabla+30<width-200){
                panelDatos.setPreferredSize(new Dimension(widthTabla+25,height-200));
            }else if (tablaDatos.heigth()+30<height-200){
                panelDatos.setPreferredSize(new Dimension(width-200,tablaDatos.heigth()+25));
            }else{
                panelDatos.setPreferredSize(new Dimension(width-200, height-200));
            }
            //??????????????????????????????????????????????????????????????????
            
            if(Editor.metodosElegidos != null){
                System.out.println("---TAMAÑO DE MÉTODOS ELEGIDOS = " + Editor.metodosElegidos.length);
            	if(Editor.metodosElegidos.length > 0){
            		System.out.println("Datos añadidos y métodos seleccionados -> Activo ejecuciones");
            		gestor_eventos.ActivarEjecuciones();
            	}else{
            		System.out.println("Datos añadidos pero métodos NO seleccionados -> NO Activo ejecuciones");
            	}
            }else{
            	System.out.println("---Datos añadidos pero métodos NO seleccionados -> NO Activo ejecuciones");
            }
            
            if(existe == false){
            	System.out.println("FILA NO EXISTE \n"); 
                
                //Fila añadida marcada como seleccionada
                modeloDatos.ponFilasSeleccionadas(1);
                
            	JOptionPane.showMessageDialog(null, mensaje43, mensaje16, JOptionPane.INFORMATION_MESSAGE);  
            	gestor_eventos.ActivarExportacionDatos(); 
            	gestor_eventos.ActivarBorraFilasDatos(); 
            }else{
            	System.out.println("FILA EXISTE o DATOS INVÁLIDOS \n");          
            	JOptionPane.showMessageDialog(null, mensaje43a, mensaje16, JOptionPane.WARNING_MESSAGE);  
            }

            tablaDatos.setColumnSelectionAllowed(false);
            tablaDatos.setRowSelectionAllowed(true);
            tablaDatos.clearSelection();
            reseteaIndices();          

        }
    }
}

public static void modificarDatos(){
    if (!frame.isVisible()){
        
        //############ COPIA DE DATOS #####################################
        resetearTabla(5);
        for (int f=0; f<modeloDatos.getRowCount(); f++){
            Object[] paraFil = new Object[dameNumeroColumnas(4)];
            for (int c=0; c<dameNumeroColumnas(4); c++){
                paraFil[c] = modeloDatos.getValueAt(f, c);
            }
            Fila fil = new Fila(paraFil);
            modeloDatos2.anhadeFila (fil, modeloDatos.getOcultoAt(f));
        }
        //#################################################################

        modeloDatos2.setCellEditable();
        //??????????????????????????????????????????????????????????????????????????
        widthTabla2 = tablaDatos2.packColumns(2);
        if (widthTabla2+30<width-200 && tablaDatos2.heigth()+30<height-200){
            panelDatos2.setPreferredSize(new Dimension(widthTabla2+12, tablaDatos2.heigth()+12));
        }else if (widthTabla2+30<width-200){
            panelDatos2.setPreferredSize(new Dimension(widthTabla2+25,height-200));
        }else if (tablaDatos2.heigth()+30<height-200){
            panelDatos2.setPreferredSize(new Dimension(width-200,tablaDatos2.heigth()+25));
        }else{
            panelDatos2.setPreferredSize(new Dimension(width-200, height-200));
        }
        
        modeloDatos2.addTableModelListener( escuchador ); 
        //######################################################
        Integer[] indices = dameIndicesSeleccionados();
        int scrollInicio = 0;
        int scrollFin = 0;
        if (indices.length==0){
            if (modeloDatos2.getRowCount()>0){
                tablaDatos2.addRowSelectionInterval(modeloDatos2.getRowCount()-1, modeloDatos2.getRowCount()-1);
                scrollInicio = modeloDatos2.getRowCount()-1;
                scrollFin = modeloDatos2.getRowCount()-1;
            }
        }else{
            for (int i=0; i<indices.length; i++){
                if (indices[i]<modeloDatos2.getRowCount()&&indices[i]!=-1){
                    tablaDatos2.addRowSelectionInterval(indices[i], indices[i]);
                }
            }
            scrollInicio = indices[0];
            scrollFin = indices[indices.length-1];
        }
        Rectangle rect = tablaDatos2.getCellRect(scrollInicio, 0, true);
        tablaDatos2.scrollRectToVisible(rect);
        rect = tablaDatos2.getCellRect(scrollFin, 0, true);
        tablaDatos2.scrollRectToVisible(rect);
        //######################################################
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}

public static void aleatorioDatos(){
    if (estado.dameIdioma().equals("espanol")){
        mensaje42 = "Filas añadidas: ";
    } else {
        mensaje42 = "Added rows: "; 
    }
    
	boolean existe = false;
	int filas_inicio = modeloDatos.getRowCount();
			
    //############ COPIA DE DATOS #####################################
    resetearTabla(5);
    for (int f=0; f<modeloDatos.getRowCount(); f++){
        Object[] paraFil = new Object[dameNumeroColumnas(4)];
        for (int c=0; c<dameNumeroColumnas(4); c++){
            paraFil[c] = modeloDatos.getValueAt(f, c);
        }
        Fila fil = new Fila(paraFil);
        existe = modeloDatos2.anhadeFila (fil, modeloDatos.getOcultoAt(f));
    }
    //#################################################################
    
    Aleatorios aleatorio = new Aleatorios(recordador, panel_codigo, modeloDatos2, tablaDatos2, controlDatos2);
    Integer resultado = aleatorio.tablaAleatoria();
 
    if ((resultado == 0)&&(sonTiposIguales(modeloDatos2, modeloDatos))) {
        
        //resetearTabla(1);
        
        //############ COPIA DE DATOS #####################################
        //resetearTabla(4); 
        for (int f=0; f<modeloDatos2.getRowCount(); f++){
            Object[] paraFil = new Object[dameNumeroColumnas(5)];
            for (int c=0; c<dameNumeroColumnas(5); c++){
                paraFil[c] = modeloDatos2.getValueAt(f, c);
            }
            Fila fil = new Fila(paraFil);                        
            modeloDatos.anhadeFila (fil, modeloDatos2.getOcultoAt(f));
        }

        //#################################################################
        //??????????????????????????????????????????????????????????????????
        widthTabla = tablaDatos.packColumns(2);
        if (widthTabla+30<width-200 && tablaDatos.heigth()+30<height-200){
            panelDatos.setPreferredSize(new Dimension(widthTabla+12, tablaDatos.heigth()+12));
        }else if (widthTabla+30<width-200){
            panelDatos.setPreferredSize(new Dimension(widthTabla+25,height-200));
        }else if (tablaDatos.heigth()+30<height-200){
            panelDatos.setPreferredSize(new Dimension(width-200,tablaDatos.heigth()+25));
        }else{
            panelDatos.setPreferredSize(new Dimension(width-200, height-200));
        }
        //??????????????????????????????????????????????????????????????????
        
        if(existe == false){
        	//System.out.println("FILA NO EXISTE \n");
        	panel_tablas.setSelectedIndex(0); 
        	gestor_eventos.ActivarExportacionDatos();
        	gestor_eventos.ActivarBorraFilasDatos(); 
        }else{
        	//System.out.println("FILA EXISTE \n"); 
        }
        

        tablaDatos.setColumnSelectionAllowed(false);       
        tablaDatos.setRowSelectionAllowed(true);
        tablaDatos.clearSelection();
        reseteaIndices();  
        
        if(Editor.metodosElegidos != null){
            System.out.println("---TAMAÑO DE MÉTODOS ELEGIDOS = " + Editor.metodosElegidos.length);
        	if(Editor.metodosElegidos.length > 0){
        		System.out.println("Datos añadidos y métodos seleccionados -> Activo ejecuciones");
        		gestor_eventos.ActivarEjecuciones();
        	}else{
        		System.out.println("Datos añadidos pero métodos NO seleccionados -> NO Activo ejecuciones");
        	}
        }else{
        	System.out.println("Datos añadidos pero métodos NO seleccionados -> NO Activo ejecuciones");
        }
        
        //Filas añadidas marcadas como seleccionadas
        modeloDatos.ponFilasSeleccionadas(modeloDatos.getRowCount() - filas_inicio);
        
        //Diálogo con el número de filas añadidas
        JOptionPane.showMessageDialog(null, mensaje42 + ": " + (modeloDatos.getRowCount() - filas_inicio), mensaje16, JOptionPane.INFORMATION_MESSAGE);
               
    }else if (resultado == 0){
        JLabel exito = new JLabel(mensaje20);
        JOptionPane.showOptionDialog(null, exito, mensaje18,
            JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(PanelDatos.class.getResource("imagenes/datos_aleatorios.gif")),
            boton, boton[0]);
    }
}

public static void importarDatos(){
	System.out.println("importarDatos");
	int numFilas_añadidas = 0;
	int numFilas_inicio = modeloDatos.getRowCount();
	
    if (estado.dameIdioma().equals("espanol")){
    	mensaje44 = "Fichero importado correctamente";
    } else {
        mensaje44 = "File imported successfully"; 
    }
    JFileChooser fc;
    File f;
    
    //presenta un dialogo modal para que el usuario seleccione un archivo
    fc = getJFileChooserXml_import();    //se obtiene un JFileChooser
    int state = fc.showOpenDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
        f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
        setCurrentDirectoryXml(new File(carpetaDelArchivo(f)));
        
        //############ COPIA DE DATOS #####################################
        resetearTabla(5);
        if (fc.getFileFilter().getDescription().contentEquals(mensaje4)){

            XMLImporter xmlimporter = new XMLImporter(tablaDatos2, modeloDatos2, controlDatos2, f);
            Integer resultado = xmlimporter.cargarXml();
            try{ //test04
            	modeloDatos2.InsertColumn("Num.", Class.forName("java.lang.String"));
            }catch(Exception e){
            	System.err.println("Exception: " + e);
            }          

            if ((resultado == 0)&&(sonTiposIguales(modeloDatos2, modeloDatos))) {  
                //resetearTabla(1);

                //############ COPIA DE DATOS #####################################
    //            resetearTabla(4);
            	for (int fi=0; fi<modeloDatos2.getRowCount(); fi++){
                    Object[] paraFil = new Object[dameNumeroColumnas(5)];
                    for (int co=0; co<dameNumeroColumnas(5); co++){ 
                        paraFil[co] = modeloDatos2.getValueAt(fi, co);
                    }
                    Fila fil = new Fila(paraFil);
                    modeloDatos.anhadeFila (fil, modeloDatos2.getOcultoAt(fi));
                }
                //#################################################################

            	int numFilas_final = modeloDatos.getRowCount();
            	if (numFilas_inicio < numFilas_final){
            		numFilas_añadidas = numFilas_final - numFilas_inicio;
            	}
        	        		        		
        		if(modeloDatos.getRowCount() != 0){
        			gestor_eventos.ActivarExportacionDatos();
        			gestor_eventos.ActivarBorraFilasDatos();
        		}
           		              
                tablaDatos.setColumnSelectionAllowed(false);
                tablaDatos.setRowSelectionAllowed(true);
                tablaDatos.clearSelection();
                reseteaIndices();   
                
                if(Editor.metodosElegidos != null){
                    System.out.println("---TAMAÑO DE MÉTODOS ELEGIDOS = " + Editor.metodosElegidos.length);
                	if(Editor.metodosElegidos.length > 0){
                		System.out.println("Datos añadidos y métodos seleccionados -> Activo ejecuciones");
                		gestor_eventos.ActivarEjecuciones();
                	}else{
                		System.out.println("Datos añadidos pero métodos NO seleccionados -> NO Activo ejecuciones");
                	}
                }else{
                	System.out.println("Datos añadidos pero métodos NO seleccionados -> NO Activo ejecuciones");
                }
                
                //Filas añadidas marcadas como seleccionadas
                modeloDatos.ponFilasSeleccionadas(numFilas_añadidas);
                
                JOptionPane.showMessageDialog(null, mensaje44a + numFilas_añadidas, mensaje16, JOptionPane.INFORMATION_MESSAGE);  
                
            }else if (resultado == 0){
                JLabel exito = new JLabel(mensaje8);
                JOptionPane.showOptionDialog(null, exito, mensaje18,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }else{
                JLabel exito = new JLabel(mensaje9);
                JOptionPane.showOptionDialog(null, exito, mensaje18,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }
        }else if(fc.getFileFilter().getDescription().contentEquals(mensaje41)){
        	System.out.println("importar de txt");
            TXTImporter txtimporter = new TXTImporter(tablaDatos2, modeloDatos2, controlDatos2, f);
            Integer resultado = txtimporter.cargarTxt();
            if ((resultado == 0)&&(sonTiposIguales(modeloDatos2, modeloDatos))) {
                //resetearTabla(1);

                //############ COPIA DE DATOS #####################################
    //            resetearTabla(4);
                for (int fi=0; fi<modeloDatos2.getRowCount(); fi++){
                    Object[] paraFil = new Object[dameNumeroColumnas(5)];
                    for (int co=0; co<dameNumeroColumnas(5); co++){
                        paraFil[co] = modeloDatos2.getValueAt(fi, co);
                    }
                    Fila fil = new Fila(paraFil);
                    modeloDatos.anhadeFila (fil, modeloDatos2.getOcultoAt(fi));
                }
                //#################################################################
                
               	int numFilas_final = modeloDatos.getRowCount();
            	if (numFilas_inicio < numFilas_final){
            		numFilas_añadidas = numFilas_final - numFilas_inicio;
            	}
            	
                //Filas añadidas marcadas como seleccionadas
                modeloDatos.ponFilasSeleccionadas(numFilas_añadidas);
                
                JOptionPane.showMessageDialog(null, mensaje44a + numFilas_añadidas, mensaje16, JOptionPane.INFORMATION_MESSAGE);  

                gestor_eventos.ActivarExportacionDatos(); 
                gestor_eventos.ActivarBorraFilasDatos(); 

                tablaDatos.setColumnSelectionAllowed(false);
                tablaDatos.setRowSelectionAllowed(true);
                tablaDatos.clearSelection();
                reseteaIndices();
            }else if (resultado == 0){
                JLabel exito = new JLabel(mensaje8);
                JOptionPane.showOptionDialog(null, exito, mensaje18,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }else{
                JLabel exito = new JLabel(mensaje9);
                JOptionPane.showOptionDialog(null, exito, mensaje18,
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(PanelDatos.class.getResource("imagenes/DatosFichero.png")),
                    boton, boton[0]);
            }
        }
        
    }
}

public ModeloTabla quitarColumnaId(ModeloTabla modeloDatos){
	ModeloTabla nuevoModelo = new ModeloTabla();
	
	return nuevoModelo;
}

public static int inicializaTopesAleatorios(){
    //resetearTabla(4);
    aleator = new Aleatorios(recordador, panel_codigo, modeloDatos, tablaDatos, controlDatos);
    int respuesta = aleator.actualizarTopes();
    return respuesta;
}

public static void anadeFilaAleatoria(){
    aleator.anadirFilaAleatoria();
}

public static Object[] dameFilaDatos(int fila){
    return Parser.dameFila(fila, modeloDatos);
}

public static void marcarDato(int fila){
    modeloDatos.modificarOcultoAt(fila, "yaEjecutado");
}

public static String dameOcultoAt(int fila){
    return modeloDatos.getOcultoAt(fila);
}

public static int[] dameFilasSeleccionadas(boolean seleccionarVarias){
    int[] devuelto = null;
    if (seleccionarVarias){
        devuelto = seleccionadorFilas(seleccionarVarias);
        for (int j=0;j<devuelto.length;j++){
        	//System.out.println("DEVUELTO["+j+"] " + devuelto[j]);
        }
    }else{
        if (dameNumeroFilas(4)==0){             //Seleccionar una y no hay ninguna
            tecladoDatos();
            if (dameNumeroFilas(4)!=0){
                devuelto = new int[1];
                devuelto[0] = 0;
            }
        } else if (dameNumeroFilas(4)==1){      //Seleccionar una y solo hay una
            if (dameNumeroFilas(4)!=0){
                devuelto = new int[1];
                devuelto[0] = 0;
            }
        }else{                                  //Seleccionar una y hay varias                      
            devuelto = seleccionadorFilas(seleccionarVarias);
        }
    }
    return devuelto;
}

public static int[] dameFilasSeleccionadas2(boolean seleccionarVarias){
    int[] devuelto = null;
    if (seleccionarVarias){
        devuelto = seleccionadorFilas2(seleccionarVarias);
    }else{
        if (dameNumeroFilas(4)==0){             //Seleccionar una y no hay ninguna
            tecladoDatos();
            if (dameNumeroFilas(4)!=0){
                devuelto = new int[1];
                devuelto[0] = 0;
            }
        } else if (dameNumeroFilas(4)==1){      //Seleccionar una y solo hay una
            if (dameNumeroFilas(4)!=0){
                devuelto = new int[1];
                devuelto[0] = 0;
            }
        }else{                                  //Seleccionar una y hay varias                      
            devuelto = seleccionadorFilas2(seleccionarVarias);
        }
    }
    return devuelto;
}

private static int[] seleccionadorFilas(boolean seleccionarVarias){
    int[] devuelto;
    tablaDatos.setRowSelectionAllowed(true);

    if (seleccionarVarias){
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    } else {
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
     
    if(tablaDatos.getRowCount() != 0){
    	//System.out.println("TODAS SELECCIONADAS");
    	tablaDatos.setRowSelectionInterval(0, tablaDatos.getRowCount()-1);// Añadido para seleccionar todas las filas de la tabla por defecto
    }  
    //??????????????????????????????????????????????????????????????????
    widthTabla = tablaDatos.packColumns(2);
    if (widthTabla+30<width-200 && tablaDatos.heigth()+30<height-200){
        panelDatos.setPreferredSize(new Dimension(widthTabla+12, tablaDatos.heigth()+12));
    }else if (widthTabla+30<width-200){
        panelDatos.setPreferredSize(new Dimension(widthTabla+25,height-200));
    }else if (tablaDatos.heigth()+30<height-200){
        panelDatos.setPreferredSize(new Dimension(width-200,tablaDatos.heigth()+25));
    }else{
        panelDatos.setPreferredSize(new Dimension(width-200, height-200));
    }
    //??????????????????????????????????????????????????????????????????
    int respuesta = JOptionPane.showOptionDialog(null, panelDatos, mensaje24,
         JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
         new ImageIcon(PanelDatos.class.getResource("imagenes/irAlinea.gif")),
         botones, botones[0]);
    if (respuesta == JOptionPane.OK_OPTION){   	
        devuelto = tablaDatos.getSelectedRows();
        tablaDatos.clearSelection();
        panel_tablas.setSelectedIndex(3); 
        return devuelto;
    }else{
        tablaDatos.clearSelection();
        //return new int[0];
        int[] cancelar = new int[1];
        cancelar[0] = -2;
        return cancelar;
    }
}

public static int[] seleccionadorFilas2(boolean seleccionarVarias){
	System.out.println("seleccionadorFilas2");

    int[] devuelto;
    tablaDatos.setRowSelectionAllowed(true);

    if (seleccionarVarias){
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    } else {
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    if(tablaDatos.getRowCount() != 0){
    	tablaDatos.setRowSelectionInterval(0, tablaDatos.getRowCount()-1);// Añadido para seleccionar todas las filas de la tabla por defecto
    }  
    //??????????????????????????????????????????????????????????????????
    widthTabla = tablaDatos.packColumns(2);
    if (widthTabla+30<width-200 && tablaDatos.heigth()+30<height-200){
        panelDatos.setPreferredSize(new Dimension(widthTabla+12, tablaDatos.heigth()+12));
    }else if (widthTabla+30<width-200){
        panelDatos.setPreferredSize(new Dimension(widthTabla+25,height-200));
    }else if (tablaDatos.heigth()+30<height-200){
        panelDatos.setPreferredSize(new Dimension(width-200,tablaDatos.heigth()+25));
    }else{
        panelDatos.setPreferredSize(new Dimension(width-200, height-200));
    }
    //??????????????????????????????????????????????????????????????????
    
    //panelDatos.add(tablaDatos);
    int respuesta = JOptionPane.showOptionDialog(null, panelDatos, "a exportar",
         JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
         new ImageIcon(PanelDatos.class.getResource("imagenes/irAlinea.gif")),
         botones, botones[0]);
    
  /*  if (respuesta == JOptionPane.OK_OPTION){
    	System.out.println("ACEPTAR");
        devuelto = tablaDatos.getSelectedRows();

        tablaDatos.clearSelection();
        panel_tablas.setSelectedIndex(3); 
        return devuelto;
    }else{
    */	System.out.println("CANCELAR");
        tablaDatos.clearSelection();
        return new int[0];
    //}
}

private static JFileChooser getJFileChooserExcel() {
    JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
    if (getCurrentDirectoryExcel() != null) {
            fc.setCurrentDirectory(getCurrentDirectoryExcel());//recordamos el directorio
    }
    fc.setDialogTitle(mensaje3);                              //se le establece un titulo
    fc.setMultiSelectionEnabled(false);                       //desactiva la multi-seleccion
    fc.setAcceptAllFileFilterUsed(false);
    fc.setFileFilter(textFileFilterExcel);                    //aplica un filtro de extensiones
    return fc;                                                //retorna el JFileChooser
}

private static JFileChooser getJFileChooserExcelImages() {
    JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
    if (getCurrentDirectoryExcel() != null) {
            fc.setCurrentDirectory(getCurrentDirectoryExcel());//recordamos el directorio
    }
    fc.setDialogTitle(mensaje3);                              //se le establece un titulo
    fc.setMultiSelectionEnabled(false);                       //desactiva la multi-seleccion
    fc.setAcceptAllFileFilterUsed(false);
    fc.setFileFilter(textFileFilterExcel);                    //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterJpg);                     //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterPng);                     //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterGif);                     //aplica un filtro de extensiones
    return fc;                                                //retorna el JFileChooser
}

    //clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilterExcel = new FileFilter() {
    public boolean accept(File f) {
        //acepta directorios y archivos de extension .xls
        return f.isDirectory() || f.getName().toLowerCase().endsWith("xls");
    }

    public String getDescription() {
        //la descripcion del tipo de archivo aceptado
        return mensaje32;
    }
};

private static JFileChooser getJFileChooserXml_import() {
    JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
    if (getCurrentDirectoryXml() != null) {
            fc.setCurrentDirectory(getCurrentDirectoryXml()); //recordamos el path del archivo anterior
    }
    fc.setDialogTitle(mensaje3);                              //se le establece un titulo
    fc.setMultiSelectionEnabled(false);                       //desactiva la multi-seleccion
    fc.setAcceptAllFileFilterUsed(false);
    fc.setFileFilter(textFileFilterTxt);                      //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterXml);                      //aplica un filtro de extensiones
    return fc;                                                //retorna el JFileChooser
}

private static JFileChooser getJFileChooserXml_export() {
    JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
    if (getCurrentDirectoryXml() != null) {
            fc.setCurrentDirectory(getCurrentDirectoryXml()); //recordamos el path del archivo anterior
    }
    fc.setDialogTitle(mensaje3);                              //se le establece un titulo
    fc.setMultiSelectionEnabled(false);                       //desactiva la multi-seleccion
    fc.setAcceptAllFileFilterUsed(false);
    fc.setFileFilter(textFileFilterTxt);                      //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterXml);                      //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterJpg);                      //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterPng);                      //aplica un filtro de extensiones
    fc.setFileFilter(textFileFilterGif);                      //aplica un filtro de extensiones
    return fc;                                                //retorna el JFileChooser
}

    //clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilterXml = new FileFilter() {
    public boolean accept(File f) {
        //acepta directorios y archivos de extension .xml
        return f.isDirectory() || f.getName().toLowerCase().endsWith("xml");
    }

    public String getDescription() {
        //la descripcion del tipo de archivo aceptado
        return mensaje4;
    }
};

    //clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilterTxt = new FileFilter() {
    public boolean accept(File f) {
        //acepta directorios y archivos de extension .xml
        return f.isDirectory() || f.getName().toLowerCase().endsWith("txt");
    }

    public String getDescription() {
        //la descripcion del tipo de archivo aceptado
        return mensaje41;
    }
};

//clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilterJpg= new FileFilter() {
public boolean accept(File f) {
    //acepta directorios y archivos de extension .jpg
    return f.isDirectory() || f.getName().toLowerCase().endsWith("jpg");
}

public String getDescription() {
    //la descripcion del tipo de archivo aceptado
    return mensaje4a;
}
};

//clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilterPng= new FileFilter() {
public boolean accept(File f) {
  //acepta directorios y archivos de extension .jpg
  return f.isDirectory() || f.getName().toLowerCase().endsWith("png");
}

public String getDescription() {
  //la descripcion del tipo de archivo aceptado
  return mensaje4b;
}
};

//clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilterGif= new FileFilter() {
public boolean accept(File f) {
  //acepta directorios y archivos de extension .jpg
  return f.isDirectory() || f.getName().toLowerCase().endsWith("gif");
}

public String getDescription() {
  //la descripcion del tipo de archivo aceptado
  return mensaje4c;
}
};
        
private static File getCurrentDirectoryXml() {      //retorna la instancia de File (el directorio actual)
    return currentDirectoryXml;
}

private static void setCurrentDirectoryXml(File directorio) {    //actualiza (el archivo actual)
    currentDirectoryXml = directorio;
    estado.tomaDirectorioXml(directorio.getAbsolutePath());
}

private static File getCurrentDirectoryExcel() {    //retorna la instancia de File (el directorio actual)
    return currentDirectoryExcel;
}

private static void setCurrentDirectoryExcel(File directorio) {  //actualiza (el directorio actual)
    currentDirectoryExcel = directorio;
    estado.tomaDirectorioExcel(directorio.getAbsolutePath());
}

private static String carpetaDelArchivo(File archivo){
            int i;
            StringTokenizer st;
            String[] cads;
            String cadena;
            
            st = new StringTokenizer(archivo.toString(),File.separator);
            cads = new String[st.countTokens()];
            for (i=0; i<cads.length; i++){
                cads[i]=st.nextToken();
            }
            cadena = new String();
            for (i=0; i<cads.length-1; i++){
                cadena = cadena.concat(File.separator+cads[i]);
            }
            return cadena;
}

public static class devolverScroll implements Runnable {
   private JScrollPane scrollInterno;
   private Point posicionInterna;
   public devolverScroll( JScrollPane scroll, Point posicion )
   {
       scrollInterno = scroll;
       posicionInterna = posicion;
   }
   // método llamado por SwingUtilities.invokeLater para actualizar scroll
   public void run()
   {
      scrollInterno.getViewport().setViewPosition(posicionInterna);
   }
}

public static class editorCeldaCeroCero implements Runnable {
   public editorCeldaCeroCero(){}
   // método llamado por SwingUtilities.invokeLater para poner el editor de celda en 0,0
   public void run()
   {
        modeloDatos.setCellEditable();
        tablaDatos.changeSelection(0, 0, false, false );
        tablaDatos.requestFocus();
        tablaDatos.editCellAt(0, 0);
   }
}

public static class actualizarContadoresAlInsertar implements Runnable {
   public actualizarContadoresAlInsertar(){}
   // método llamado por SwingUtilities.invokeLater para actualizar contadores
   public void run()
   {
        int row =  tabla2.getRowCount () - 1;
        optimusAutomatico = -1;
        
        for (int j=1; j<modelo2.getColumnCount(); j++){//recorrido por las columnas
            if (esElOptimoMinimoMaximo(row, j, true, optimus)){
                contadorOptimosMaximos[j-1]++;
            }
            if (esElOptimoMinimoMaximo(row, j, false, optimus)){
                contadorOptimosMinimos[j-1]++;
            }
            if (esElSuboptimoMinimoMaximo(row, j, true, optimus)){
                contadorSuboptimosMaximos[j-1]++;
            }
            if (esElSuboptimoMinimoMaximo(row, j, false, optimus)){
                contadorSuboptimosMinimos[j-1]++;
            }
            if (esElSuperoptimoMinimoMaximo(row, j, true, optimus)){
                contadorSuperoptimosMaximos[j-1]++;
            }
            if (esElSuperoptimoMinimoMaximo(row, j, false, optimus)){
                contadorSuperoptimosMinimos[j-1]++;
            }
            if (contadorOptimosMaximos[j-1]==modelo2.getRowCount()&&maximizar){
                optimusAutomatico = j-1;
            }
            if (contadorOptimosMinimos[j-1]==modelo2.getRowCount()&&!maximizar){
                optimusAutomatico = j-1;
            }
        }
        for (int j=1; j<modelo2.getColumnCount(); j++){//recorrido por las columnas
            if (optimusAutomatico!=-1){
                if (Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())>0){
                         if (Parser.StringAFloat(modelo2.getValueAt(row, j).toString())>
                            Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())){
                                sumatoriosDesviacion[j-1] = sumatoriosDesviacion[j-1] + (Parser.StringAFloat(modelo2.getValueAt(row, j).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString());

                                if ((Parser.StringAFloat(modelo2.getValueAt(row, j).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())*(float)100
                                    >desviacionesMaximaSuperior[j-1]){
                                        desviacionesMaximaSuperior[j-1] = (Parser.StringAFloat(modelo2.getValueAt(row, j).toString())-
                                        Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString()))/
                                        Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())*(float)100;
                                }

                         }else{
                            sumatoriosDesviacion[j-1] = sumatoriosDesviacion[j-1] + (Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())-
                                Parser.StringAFloat(modelo2.getValueAt(row, j).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString());

                            if ((Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())-
                                Parser.StringAFloat(modelo2.getValueAt(row, j).toString()))/
                                Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())*(float)100
                                >desviacionesMaximaInferior[j-1]){
                                    desviacionesMaximaInferior[j-1] = (Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(row, j).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(row, optimusAutomatico+1).toString())*(float)100;
                            }

                         }
                 }
            }else{
                sumatoriosDesviacion[j-1] = 0;
                desviacionesMaximaSuperior[j-1] = 0;
                desviacionesMaximaInferior[j-1] = 0;
            }
        }
   }
}

public static void actualizarMaximMinim(Boolean max){
    maximizar = max;
    Fila nueva_fila;
    if (estaInicializadaTabla(2)){
        modelo2.setCellColor(maximizar, optimus);
        int numMetodos = modelo2.getColumnCount()-1;
        System.out.println("11111");
        Object[] paraTabla31 = new Object[numMetodos+1];
        Object[] paraTabla32 = new Object[numMetodos+1];
        Object[] paraTabla33 = new Object[numMetodos+1];
        Object[] paraTabla34 = new Object[numMetodos+1];
        Object[] paraTabla35 = new Object[numMetodos+1];
        Object[] paraTabla36 = new Object[numMetodos+1];
        Object[] paraTabla37 = new Object[numMetodos+1];
        //######### NUMERO DE EJECUCIONES #########
        paraTabla31[0] = mensaje33;

        for (int i=0; i<numMetodos; i++){ 
            paraTabla31[i+1] = Parser.EnteroAString(modelo2.getRowCount());
        }
        //######### % SUBOPTIMAS ##################
        paraTabla32[0] = mensaje34;
        if (maximizar){
                for (int i=0; i<numMetodos; i++){ 
                    paraTabla32[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuboptimosMaximos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
                }
        }else{
                for (int i=0; i<numMetodos; i++){ 
                    paraTabla32[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuboptimosMinimos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
                }
        }
        //############ % OPTIMAS ##################
        paraTabla33[0] = mensaje35;
        optimusAutomatico = -1;
        if (maximizar){
                for (int i=0; i<numMetodos; i++){ 
                    paraTabla33[i+1] = String.valueOf(String.format("%.2f", (float)contadorOptimosMaximos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
                    if (contadorOptimosMaximos[i]==modelo2.getRowCount()){
                        optimusAutomatico = i;
                    }
                }
        }else{
                for (int i=0; i<numMetodos; i++){ 
                    paraTabla33[i+1] = String.valueOf(String.format("%.2f", (float)contadorOptimosMinimos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
                    if (contadorOptimosMinimos[i]==modelo2.getRowCount()){
                        optimusAutomatico = i;
                    }
                }
        }
        //######### % SUPEROPTIMAS ################
        paraTabla34[0] = mensaje36;
        if (maximizar){
                for (int i=0; i<numMetodos; i++){ 
                    paraTabla34[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuperoptimosMaximos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
                }
        }else{
                for (int i=0; i<numMetodos; i++){ 
                    paraTabla34[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuperoptimosMinimos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
                }
        }
        //####### % DESVIACION MEDIA #############
        paraTabla35[0] = mensaje37;
        paraTabla36[0] = mensaje38;
        paraTabla37[0] = mensaje39;

        //###### CALCULO DE LA DESVIACION MEDIA #######
        //###### DESVIACION MAXIMA SUPEROPTIMA ########
        //###### DESVIACION MAXIMA SUBOPTIMA ##########
        for (int i=0; i<numMetodos; i++){ 
           if (optimusAutomatico!=-1){
              sumatoriosDesviacion[i] = 0;
              desviacionesMaximaInferior[i] = 0;
              desviacionesMaximaSuperior[i] = 0;
              for (int j=0; j<modelo2.getRowCount(); j++){
                 if (Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())>0){
                         if (Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())>
                            Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())){
                                sumatoriosDesviacion[i] = sumatoriosDesviacion[i] + (Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString());

                                if ((Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100
                                    >desviacionesMaximaSuperior[i]){
                                        desviacionesMaximaSuperior[i] = (Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())-
                                        Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString()))/
                                        Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100;
                                }

                         }else{
                            sumatoriosDesviacion[i] = sumatoriosDesviacion[i] + (Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())-
                                Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString());

                            if ((Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())-
                                Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString()))/
                                Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100
                                >desviacionesMaximaInferior[i]){
                                    desviacionesMaximaInferior[i] = (Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100;
                            }

                         }
                 }
              }
              
              if (sumatoriosDesviacion[i]>0&&maximizar){
                  paraTabla35[i+1] = String.valueOf(String.format("%.2f", sumatoriosDesviacion[i]/
                          ((float)modelo2.getRowCount()-contadorOptimosMaximos[i])*(float)100))+" %";
              }else if (sumatoriosDesviacion[i]>0&&!maximizar){
                  paraTabla35[i+1] = String.valueOf(String.format("%.2f", sumatoriosDesviacion[i]/
                          ((float)modelo2.getRowCount()-contadorOptimosMinimos[i])*(float)100))+" %";
              }else{
                  paraTabla35[i+1] = "0,00 %";
              }if (maximizar){
                  paraTabla36[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaSuperior[i]))+" %";
                  paraTabla37[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaInferior[i]))+" %";
              }else{
                  paraTabla36[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaInferior[i]))+" %";
                  paraTabla37[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaSuperior[i]))+" %";
              }
              System.out.println("paraTabla35 = " + paraTabla35[i+1]);
              System.out.println("paraTabla37 = " + paraTabla37[i+1]);
              System.out.println("paraTabla36 = " + paraTabla36[i+1]);
              
           }else{
              paraTabla35[i+1] = mensaje40;
              paraTabla36[i+1] = mensaje40;
              paraTabla37[i+1] = mensaje40;
           }
        }
        modelo3.reseteaTabla();
        nueva_fila = new Fila(paraTabla31);
        modelo3.anhadeFila(nueva_fila);
        nueva_fila = new Fila(paraTabla32);
        modelo3.anhadeFila(nueva_fila);
        nueva_fila = new Fila(paraTabla33);
        modelo3.anhadeFila(nueva_fila);
        nueva_fila = new Fila(paraTabla34);
        modelo3.anhadeFila(nueva_fila);
        nueva_fila = new Fila(paraTabla35);
        modelo3.anhadeFila(nueva_fila);
        nueva_fila = new Fila(paraTabla36);       
        modelo3.anhadeFila(nueva_fila);
        nueva_fila = new Fila(paraTabla37);
        modelo3.anhadeFila(nueva_fila);
        tabla3.packColumns(2);
    }
}

public static void actualizarTablas(){
        for (int j=1; j<modelo2.getColumnCount(); j++){
            contadorOptimosMaximos[j-1] = 0;
            contadorOptimosMinimos[j-1] = 0;
            contadorSuboptimosMaximos[j-1] = 0;
            contadorSuboptimosMinimos[j-1] = 0;
            contadorSuperoptimosMaximos[j-1] = 0;
            contadorSuperoptimosMinimos[j-1] = 0;
            sumatoriosDesviacion[j-1] = 0;
            desviacionesMaximaInferior[j-1] = 0;
            desviacionesMaximaSuperior[j-1] = 0;
        }
        optimusAutomatico = -1;
        for (int i=0; i<modelo2.getRowCount(); i++){
            for (int j=1; j<modelo2.getColumnCount(); j++){
                if (esElOptimoMinimoMaximo(i, j, true, optimus)){
                    contadorOptimosMaximos[j-1]++;
                }
                if (esElOptimoMinimoMaximo(i, j, false, optimus)){
                    contadorOptimosMinimos[j-1]++;
                }
                if (esElSuboptimoMinimoMaximo(i, j, true, optimus)){
                    contadorSuboptimosMaximos[j-1]++;
                }
                if (esElSuboptimoMinimoMaximo(i, j, false, optimus)){
                    contadorSuboptimosMinimos[j-1]++;
                }
                if (esElSuperoptimoMinimoMaximo(i, j, true, optimus)){
                    contadorSuperoptimosMaximos[j-1]++;
                }
                if (esElSuperoptimoMinimoMaximo(i, j, false, optimus)){
                    contadorSuperoptimosMinimos[j-1]++;
                }
                if (contadorOptimosMaximos[j-1]==modelo2.getRowCount()&&maximizar){
                    optimusAutomatico = j-1;
                }
                if (contadorOptimosMinimos[j-1]==modelo2.getRowCount()&&!maximizar){
                    optimusAutomatico = j-1;
                }
            }
        }
        for (int i=0; i<(modelo2.getColumnCount()-1); i++){ 
           if (optimusAutomatico!=-1){
              sumatoriosDesviacion[i] = 0;
              desviacionesMaximaInferior[i] = 0;
              desviacionesMaximaSuperior[i] = 0;
              for (int j=0; j<modelo2.getRowCount(); j++){
                 if (Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())>0){
                         if (Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())>
                            Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())){
                                sumatoriosDesviacion[i] = sumatoriosDesviacion[i] + (Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString());

                                if ((Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100
                                    >desviacionesMaximaSuperior[i]){
                                        desviacionesMaximaSuperior[i] = (Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString())-
                                        Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString()))/
                                        Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100;
                                }

                         }else{
                            sumatoriosDesviacion[i] = sumatoriosDesviacion[i] + (Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())-
                                Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString());

                            if ((Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())-
                                Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString()))/
                                Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100
                                >desviacionesMaximaInferior[i]){
                                    desviacionesMaximaInferior[i] = (Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())-
                                    Parser.StringAFloat(modelo2.getValueAt(j, i+1).toString()))/
                                    Parser.StringAFloat(modelo2.getValueAt(j, optimusAutomatico+1).toString())*(float)100;
                            }

                         }
                 }
              }
           }
        }
        if (modelo2.getRowCount()>0){
            actualizarTablas(modelo2.getRowCount()-1);
        }else{
            resetearTabla(1);
            resetearTabla(2);
            resetearTabla(3);
        }
}

public static void actualizarTablas(int indice){
    modelo1.reseteaTabla();
    int contador;
    Fila nueva_fila;
    int numMetodos = modelo2.getColumnCount()-1;
    for (int i=0; i<numMetodos; i++){
        Object[] paraTabla = new Object[3];
        paraTabla[0] = modelo2.getColumnName(i+1);
        paraTabla[1] = modelo2.getOcultoAt(indice);
        paraTabla[2] = modelo2.getValueAt(indice, i+1);
        nueva_fila = new Fila(paraTabla);
        modelo1.anhadeFila(nueva_fila);
    }
//    tabla1.packColumns(2); //Comentado para eliminar parpadeo

    Object[] paraTabla31 = new Object[numMetodos+1];
    Object[] paraTabla32 = new Object[numMetodos+1];
    Object[] paraTabla33 = new Object[numMetodos+1];
    Object[] paraTabla34 = new Object[numMetodos+1];
    Object[] paraTabla35 = new Object[numMetodos+1];
    Object[] paraTabla36 = new Object[numMetodos+1];
    Object[] paraTabla37 = new Object[numMetodos+1];
    //######### NUMERO DE EJECUCIONES #########
    
    paraTabla31[0] = mensaje33;

    for (int i=0; i<numMetodos; i++){
        paraTabla31[i+1] = Parser.EnteroAString(modelo2.getRowCount());
    }

    //######### % SUBOPTIMAS ##################
    paraTabla32[0] = mensaje34;
    if (maximizar){
            for (int i=0; i<numMetodos; i++){ 
                paraTabla32[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuboptimosMaximos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
            }
    }else{
            for (int i=0; i<numMetodos; i++){ 
                paraTabla32[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuboptimosMinimos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
            }
    }
    //############ % OPTIMAS ##################
    paraTabla33[0] = mensaje35;
    //optimusAutomatico = -1;
    if (maximizar){
        for (int i=0; i<numMetodos; i++){ 
            paraTabla33[i+1] = String.valueOf(String.format("%.2f", (float)contadorOptimosMaximos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
            if (contadorOptimosMaximos[i]==modelo2.getRowCount()){
                //optimusAutomatico = i;
            }
        }
    }else{
        for (int i=0; i<numMetodos; i++){ 
            paraTabla33[i+1] = String.valueOf(String.format("%.2f", (float)contadorOptimosMinimos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
            if (contadorOptimosMinimos[i]==modelo2.getRowCount()){
                //optimusAutomatico = i;
            }
        }
    }
    //######### % SUPEROPTIMAS ################
    paraTabla34[0] = mensaje36;
    if (maximizar){
            for (int i=0; i<numMetodos; i++){ 
                paraTabla34[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuperoptimosMaximos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
            }
    }else{
            for (int i=0; i<numMetodos; i++){ 
                paraTabla34[i+1] = String.valueOf(String.format("%.2f", (float)contadorSuperoptimosMinimos[i]/(float)modelo2.getRowCount()*(float)100))+" %";
            }
    }
    //####### % DESVIACION MEDIA #############
    paraTabla35[0] = mensaje37;
    paraTabla36[0] = mensaje39; 
    paraTabla37[0] = mensaje38; 
    
    for (int i=0; i<numMetodos; i++){ 
       if (optimusAutomatico!=-1){
    	   float pt36 = 0; //variable para comprobar subóptimo
    	   float pt37 = 0; //variable para comprobar sobreóptimo
    	   float prueba = (float) 100 - ( (float) sumatoriosDesviacion[i]/((float)modelo2.getRowCount()-contadorOptimosMaximos[i])*(float)100);
    	             
          if (maximizar){ //MAXIMIZAR:             
              if(desviacionesMaximaSuperior[i] > 0){
            	  paraTabla37[i+1] = String.valueOf(String.format("%.2f", 100+(100-desviacionesMaximaSuperior[i])))+" %";
            	  pt37 = 100+(100-desviacionesMaximaSuperior[i]);
              }else{
            	  paraTabla37[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaSuperior[i]))+" %";
            	  pt37 = desviacionesMaximaSuperior[i];
              }
              if(desviacionesMaximaInferior[i] > 0){ //añadida comprobación para distinguir los óptimos
            	  paraTabla36[i+1] = String.valueOf(String.format("%.2f", 100 - desviacionesMaximaInferior[i]))+" %"; 
              }else{ //0.00%
            	  paraTabla36[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaInferior[i]))+" %"; 
              }

          }else{ //MINIMIZAR:     	                        
              if(desviacionesMaximaSuperior[i] > 0){
            	  paraTabla37[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaInferior[i]))+" %"; 
            	  pt37 = desviacionesMaximaInferior[i];
              }else{ 
            	  paraTabla37[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaInferior[i]))+" %"; 
            	  pt37 = desviacionesMaximaInferior[i];
              }
              
              if(desviacionesMaximaSuperior[i] > 0){ //añadida comprobación para distinguir los óptimos
            	  paraTabla36[i+1] = String.valueOf(String.format("%.2f", 100 + desviacionesMaximaSuperior[i]))+" %"; 
            	  pt36 = 100 + desviacionesMaximaSuperior[i];
              }else{ //0.00%
            	  paraTabla36[i+1] = String.valueOf(String.format("%.2f", desviacionesMaximaSuperior[i]))+" %"; 
            	  pt36 = desviacionesMaximaSuperior[i];
              }             
          }         
   	   
   	 
   	   if (sumatoriosDesviacion[i]>0&&maximizar){ //MAXIMIZAR
   		   //Comprobación para sobreóptimos
   		   if(pt37>100){ 
   			   paraTabla35[i+1] = String.valueOf(String.format("%.2f",100+(100-prueba)))+" %";
   		   }else{
   			   paraTabla35[i+1] = String.valueOf(String.format("%.2f",prueba))+" %";
   		   }
   	   }else if (sumatoriosDesviacion[i]>0&&!maximizar){  //MINIMIZAR
   		//Comprobación para subóptimos
   		   if((pt37 == 0) && (pt36 > 100)){
   			paraTabla35[i+1] = String.valueOf(String.format("%.2f", (float) 100 + (sumatoriosDesviacion[i]/
                    ((float)modelo2.getRowCount()-contadorOptimosMinimos[i])*(float)100)))+" %";
   		   }else if((pt36 == 0) && (pt37 > 0)){
    			paraTabla35[i+1] = String.valueOf(String.format("%.2f", (float) 100 - sumatoriosDesviacion[i]/ 
                     ((float)modelo2.getRowCount()-contadorOptimosMinimos[i])*(float)100))+" %";
   		   }else{
           /*   paraTabla35[i+1] = String.valueOf(String.format("%.2f", (float) 100 + (sumatoriosDesviacion[i]/
                      ((float)modelo2.getRowCount()-contadorOptimosMinimos[i])*(float)100)))+" %";*/
   		   }
          }else{
              paraTabla35[i+1] = "0,00 %";
          }
       }else{
          paraTabla35[i+1] = mensaje40;
          paraTabla37[i+1] = mensaje40;
          paraTabla36[i+1] = mensaje40; 
       }
    }
    modelo3.reseteaTabla();

    nueva_fila = new Fila(paraTabla31);
    modelo3.anhadeFila(nueva_fila);
    nueva_fila = new Fila(paraTabla32);
    modelo3.anhadeFila(nueva_fila);
    nueva_fila = new Fila(paraTabla33);
    modelo3.anhadeFila(nueva_fila);
    nueva_fila = new Fila(paraTabla34);
    modelo3.anhadeFila(nueva_fila);
    nueva_fila = new Fila(paraTabla35);
    modelo3.anhadeFila(nueva_fila);
    nueva_fila = new Fila(paraTabla36); 
    modelo3.anhadeFila(nueva_fila);
    nueva_fila = new Fila(paraTabla37); 
    modelo3.anhadeFila(nueva_fila);
   // tabla3.packColumns(2);  //Comentado para eliminar el parpadeo en la columna de títulos de la tabla de Resumen numérico
    
    tabla2.repaint();
    
    //######################################################
    tablaDatos2.clearSelection();
    Integer[] indices = dameIndicesSeleccionados();
    int scrollInicio = 0;
    int scrollFin = 0;
        if (indices.length==0){
            if (modeloDatos2.getRowCount()>0){
                tablaDatos2.addRowSelectionInterval(modeloDatos2.getRowCount()-1, modeloDatos2.getRowCount()-1);
                scrollInicio = modeloDatos2.getRowCount()-1;
                scrollFin = modeloDatos2.getRowCount()-1;
            }
        }else{
            for (int i=0; i<indices.length; i++){
                if (indices[i]<modeloDatos2.getRowCount()&&indices[i]!=-1){
                    tablaDatos2.addRowSelectionInterval(indices[i], indices[i]);
                }
            }
            scrollInicio = indices[0];
            scrollFin = indices[indices.length-1];
        }
        Rectangle rect = tablaDatos2.getCellRect(scrollInicio, 0, true);
        tablaDatos2.scrollRectToVisible(rect);
        rect = tablaDatos2.getCellRect(scrollFin, 0, true);
        tablaDatos2.scrollRectToVisible(rect);
        tablaDatos2.repaint();
   //######################################################
}

public static void actualizarOptimo(int optim){
    if (optim != optimus){
        optimus = optim;
        actualizarTablas();
        modelo2.setOptimoCellColor(optimus);
    }
}

private static Integer[] dameIndicesSeleccionados(){
    int contador = 0;
        for(int i=0; i<modelo2.getRowCount(); i++){
            if (tabla2.isRowSelected(i)){
                contador++;
            }
        }
    Integer[] arrayIndices = new Integer[contador];
    contador = 0;
        for(int i=0; i<modelo2.getRowCount(); i++){
            if (tabla2.isRowSelected(i)){
                arrayIndices[contador] = modelo2.getOculto2At(i);
                contador++;
            }
        }
    return arrayIndices;
}

private static void reseteaIndices(){
    for(int i=0; i<modelo2.getRowCount(); i++){
        modelo2.modificarOculto2At(i, -1);
    }
}

private static boolean esElOptimoMinimoMaximo(int fila, int columna, boolean maxMin, int columnaOptima){
    boolean devuelto = true;
    if (modelo2.getValueAt(fila, columna).toString().contains("InstantiationException")
        || modelo2.getValueAt(fila, columna).toString().contains("IllegalAccessException")
        || modelo2.getValueAt(fila, columna).toString().contains("SecurityException")
        || modelo2.getValueAt(fila, columna).toString().contains("IllegalArgumentException")
     //   || modelo2.getValueAt(fila, columna).toString().contains("InvocationTargetException")){
        || modelo2.getValueAt(fila, columna).toString().contains("Tiempo excedido")){
        devuelto = false;
    } else if (maxMin&&columnaOptima==-1){   //MAXIMIZAR SIN MARCAR METODO OPTIMO
        for (int i=1; i<modelo2.getColumnCount(); i++){
            if (Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    <Parser.StringADouble(modelo2.getValueAt(fila, i).toString())
                    && !modelo2.getValueAt(fila, i).toString().contains("InstantiationException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalAccessException")
                    && !modelo2.getValueAt(fila, i).toString().contains("SecurityException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalArgumentException")
                  //  && !modelo2.getValueAt(fila, i).toString().contains("InvocationTargetException")
                    && !modelo2.getValueAt(fila, i).toString().contains("Tiempo excedido")
                    ){
                devuelto = false;
            }
        }
    } else if (!maxMin&&columnaOptima==-1){   //MINIMIZAR SIN MARCAR METODO OPTIMO
        for (int i=1; i<modelo2.getColumnCount(); i++){
            if (Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    >Parser.StringADouble(modelo2.getValueAt(fila, i).toString())
                    && !modelo2.getValueAt(fila, i).toString().contains("InstantiationException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalAccessException")
                    && !modelo2.getValueAt(fila, i).toString().contains("SecurityException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalArgumentException")
               //     && !modelo2.getValueAt(fila, i).toString().contains("InvocationTargetException")
                    && !modelo2.getValueAt(fila, i).toString().contains("Tiempo excedido")
                    ){
                devuelto = false;
            }
        }
    } else if (maxMin&&columnaOptima!=-1){   //MAXIMIZAR MARCANDO METODO OPTIMO
        devuelto = Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    == Parser.StringADouble(modelo2.getValueAt(fila, columnaOptima+1).toString());
    } else if (!maxMin&&columnaOptima!=-1){  //MINIMIZAR MARCANDO METODO OPTIMO
        devuelto = Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    == Parser.StringADouble(modelo2.getValueAt(fila, columnaOptima+1).toString());
    }
    return devuelto;
}

private static boolean esElSuboptimoMinimoMaximo(int fila, int columna, boolean maxMin, int columnaOptima){
    boolean devuelto = false;
    if (modelo2.getValueAt(fila, columna).toString().contains("InstantiationException")
        || modelo2.getValueAt(fila, columna).toString().contains("IllegalAccessException")
        || modelo2.getValueAt(fila, columna).toString().contains("SecurityException")
        || modelo2.getValueAt(fila, columna).toString().contains("IllegalArgumentException")
    //    || modelo2.getValueAt(fila, columna).toString().contains("InvocationTargetException")){
        || modelo2.getValueAt(fila, columna).toString().contains("Tiempo excedido")){
        devuelto = false;
    } else if (maxMin&&columnaOptima==-1){   //MAXIMIZAR SIN MARCAR METODO OPTIMO
        for (int i=1; i<modelo2.getColumnCount(); i++){
            if (Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    <Parser.StringADouble(modelo2.getValueAt(fila, i).toString())
                    && !modelo2.getValueAt(fila, i).toString().contains("InstantiationException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalAccessException")
                    && !modelo2.getValueAt(fila, i).toString().contains("SecurityException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalArgumentException")
                   // && !modelo2.getValueAt(fila, i).toString().contains("InvocationTargetException")
                    && !modelo2.getValueAt(fila, i).toString().contains("Tiempo excedido")
                    ){
                devuelto = true;
            }
        }
    } else if (!maxMin&&columnaOptima==-1){   //MINIMIZAR SIN MARCAR METODO OPTIMO
        for (int i=1; i<modelo2.getColumnCount(); i++){
            if (Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    >Parser.StringADouble(modelo2.getValueAt(fila, i).toString())
                    && !modelo2.getValueAt(fila, i).toString().contains("InstantiationException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalAccessException")
                    && !modelo2.getValueAt(fila, i).toString().contains("SecurityException")
                    && !modelo2.getValueAt(fila, i).toString().contains("IllegalArgumentException")
                   // && !modelo2.getValueAt(fila, i).toString().contains("InvocationTargetException")
                    && !modelo2.getValueAt(fila, i).toString().contains("Tiempo excedido")
                    ){
                devuelto = true;
            }
        }
    } else if (maxMin&&columnaOptima!=-1){   //MAXIMIZAR MARCANDO METODO OPTIMO
        devuelto = Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    <Parser.StringADouble(modelo2.getValueAt(fila, columnaOptima+1).toString());
    } else if (!maxMin&&columnaOptima!=-1){  //MINIMIZAR MARCANDO METODO OPTIMO
        devuelto = Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    >Parser.StringADouble(modelo2.getValueAt(fila, columnaOptima+1).toString());
    }
    return devuelto;
}

private static boolean esElSuperoptimoMinimoMaximo(int fila, int columna, boolean maxMin, int columnaOptima){
    boolean devuelto = false;
    if (modelo2.getValueAt(fila, columna).toString().contains("InstantiationException")
        || modelo2.getValueAt(fila, columna).toString().contains("IllegalAccessException")
        || modelo2.getValueAt(fila, columna).toString().contains("SecurityException")
        || modelo2.getValueAt(fila, columna).toString().contains("IllegalArgumentException")
      //  || modelo2.getValueAt(fila, columna).toString().contains("InvocationTargetException")){
        || modelo2.getValueAt(fila, columna).toString().contains("Tiempo excedido")){
        devuelto = false;
    } else if (maxMin&&columnaOptima!=-1){   //MAXIMIZAR MARCANDO METODO OPTIMO
        devuelto = Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    >Parser.StringADouble(modelo2.getValueAt(fila, columnaOptima+1).toString());
    } else if (!maxMin&&columnaOptima!=-1){  //MINIMIZAR MARCANDO METODO OPTIMO
        devuelto = Parser.StringADouble(modelo2.getValueAt(fila, columna).toString())
                    <Parser.StringADouble(modelo2.getValueAt(fila, columnaOptima+1).toString());
    }
    return devuelto;
}

public static void punteroEspera(){
    panel_codigo.setCursor(new Cursor(Cursor.WAIT_CURSOR));
}

public static void punteroNormal(){
    panel_codigo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
}
}

  