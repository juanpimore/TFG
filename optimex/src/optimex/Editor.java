package optimex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;
import java.io.File;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Editor extends RSyntaxTextArea{    //clase publica Editor
    
    private static Ventana ventana;
    
    private static GestorEventosRollOver gestor;
    
    private static boolean hasInit = false;
    private static boolean hasChanged = false;//el estado del documento actual, no modificado por defecto
    private static boolean hasCompiled = false;//el estado de compilacion del archivo
    private static File currentFile = null;       //el archivo actual, ninguno por defecto
    private static File currentDirectory = null;       //el archivo actual, ninguno por defecto

    private final EventHandler eventHandler;          //instancia de EventHandler (la clase que maneja eventos)
    public static AccionesEditor accionesEditor;    //instancia de ActionPerformer (la clase que ejecuta acciones)
    private static UndoManager undoManager;            //instancia de UndoManager (administrador de edicion)

    private static JTextArea texto;//texto
    private static JTextArea texto2;//texto
    private String sbFilePath;     //Ruta del fichero abierto
    private String sbFileSize;     //Tamaño del fichero abierto
    private static String sbCaretPos;     //Lineas y posicion del cursor en el texto
    
    private static String mensaje1, mensaje2, mensaje3, mensaje4, mensaje5, mensaje6, mensaje7, mensaje8,
            mensaje9, mensaje10, mensaje11, mensaje12, mensaje13, mensaje14, mensaje15, mensaje16, mensaje16a, mensaje16b, mensaje16c,
            mensaje17, mensaje18, mensaje19, mensaje20, mensaje21, mensaje22, mensaje23, mensaje24, mensaje25, mensaje26,
            mensaje27, mensaje28, mensaje29, mensaje30, mensaje31, mensaje32, mensaje33, mensaje34, mensaje35, 
            mensaje36, mensaje37, mensaje38, mensaje39a, mensaje39b, mensaje39c, mensaje40a, mensaje40b, mensaje40c,
            mensaje41a, mensaje41b, mensaje42, mensaje43, mensaje44, mensaje45, mensaje46;
    
    private ClassLoader cargador;
    
    private static String motor = "";
    
    public static Method[] metodosElegidos, metodosAnteriores;
    Method[] mcoincidentes;
    private static int[] datosElegidos;
    private static Method metodo;
    private static int dato;
    private static Object resultado;
    private static String devuelto;
    private static String tipoAComparar;
    private static String[] botones = {"Aceptar","Cancelar"};
    
    private Integer numeroEjecuciones = 0;
    private Integer ejecucionesIntensivas = 0;
    private Boolean imprimirNumEjec = false;
    private static boolean ejecutarVariosMetodos = false;
    private static boolean ejecutarVariosDatos = false;
    private static boolean seguir = false;
    private static XMLEstado estado;
    
    private static int optimus;
    private static boolean primeraVez = true;
    private static int indiceSignatura;
    private static int maximizar = 0;  //########### 0 minimizar / 1 maximizar #############
    
    tarea_buscador tb;
    inicializarCargador iC;
    tarea_dameSignaturas tS;
    tarea_ejecutar tES;
    tarea_ejecutarClase tE;
    tarea_compila tc;
    tarea_dameMetodos tM;
    tarea_dameMetodos2 tM2;
    tarea_dameMetodos3 tM3;
    
    RTextScrollPane sp;
    
    Font font = new Font("Tahoma", Font.PLAIN, 11); 
    final String[] titulo_casos1 = new String[3];
   	final String[] titulo_casos2 = new String[3];
   	
   	public static boolean parado = false;
    
public Editor(GestorEventosRollOver gestor_eventos) {   

    gestor = gestor_eventos;
    
    eventHandler = new EventHandler();              //construye una instancia de EventHandler
    accionesEditor = new AccionesEditor(this, gestor);//construye una instancia de AccionesEditor
    undoManager = new UndoManager();                //construye una instancia de UndoManager
    undoManager.setLimit(50);                       //le asigna un limite al buffer de ediciones
    motor = new String();                           //el compilador de java
    metodosAnteriores = new Method[0];
    
    estado = new XMLEstado();
    if (estado.dameIdioma().equals("espanol")){
        ponerEspanolRelajado();
    } else {
        ponerInglesRelajado();
    }
    File directorio = new File(estado.dameDirectorioClases());
    if (directorio.isDirectory()){
        currentDirectory = directorio;
    }
	
    buildTextArea();     //construye el area de edicion, es importante que esta sea la primera parte en construirse
    String so = System.getProperty("os.name").toLowerCase();
    File jvm = new File(estado.dameJvm());
    if (jvm.exists() ||  (so.indexOf("mac") >= 0 && estado.dameJvm().equals("javac"))){
        motor = estado.dameJvm();
    } else {
        tb = new tarea_buscador();   //buscamos el compilador de java
        tb.start();
    }
}

   
public void setVentana(Ventana ventana){
    this.ventana = ventana;
};

public GestorEventosRollOver dameGestor(){
    return gestor;
}

public static void ponParado(boolean parad){
	parado=parad;
}

public static boolean dameParado(){
	return parado;
}

private void buildTextArea() {
	
	//Añadida funcionalidad con la biblioteca RSyntaxTextArea para el editor:	
    this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
    this.setCodeFoldingEnabled(true);
    sp = new RTextScrollPane(this);
    this.setBackground(Color.gray); 
    this.setCurrentLineHighlightColor(Color.gray);
  //  sp.setFoldIndicatorEnabled(true);
    sp.setLineNumbersEnabled(true); 
           
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int height = pantalla.height;
    int width = pantalla.width;
    this.setMinimumSize(new Dimension(width/2,height));
    
 //   Font f = new Font ("Courier",Font.PLAIN,12);
 //   this.setFont(f);
 //   this.setCaretColor(Color.black);
 //   this.setSelectedTextColor(Color.red);
    //asigna el manejador de eventos para el cursor
 //   this.addCaretListener(eventHandler);  //trasladado a AccionesEditor para que se añada cuando el editor no es gris
    //asigna el manejador de eventos para el raton
 //   this.addMouseListener(eventHandler);
    //asigna el manejador de eventos para registrar los cambios sobre el documento (para que esté activa la funcionalidad de Deshacer y Rehacer del panel inferior cuando se crea una clase nueva por ejemplo)
      this.getDocument().addUndoableEditListener(eventHandler);

    //remueve las posibles combinaciones de teclas asociadas por defecto con el JTextArea
  //  this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK), "none");    //remueve CTRL + X ("Cortar")
  //  this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK), "none");    //remueve CTRL + C ("Copiar")
  //  this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK), "none");    //remueve CTRL + V ("Pegar")
    
    //Para evitar que JTextArea imprima ALT+Mnemonicos y 
    //bloquee el Action de los JMenus 
    this.addKeyListener(new KeyAdapter(){ 
        public void keyTyped (KeyEvent e){
            char caracter = e.getKeyChar();
            if (caracter == 'å'){
                PanelMenus.menu_Archivo.doClick();
                e.consume();
            }
            if (caracter == '€'){
                PanelMenus.menu_Ejecutar.doClick();
                e.consume();
            }
            if (caracter == '†'){
                PanelMenus.menu_Tablas.doClick();
                e.consume();
            }
            if (caracter == '©'){
                PanelMenus.menu_Configuracion.doClick();
                e.consume();
            }
            if (caracter == '¥'){
                PanelMenus.menu_Ayuda.doClick();
                e.consume();
            }
        }
    });
    
    texto = this;
 	this.setHighlightCurrentLine(false);
  //  texto.setCaretColor(Color.black);
  //  texto.setSelectedTextColor(Color.red);
}


static void updateControls() {
    //averigua si se pueden deshacer los cambios en el documento actual
    boolean canUndo = undoManager.canUndo();
    //averigua si se pueden rehacer los cambios en el documento actual
    boolean canRedo = undoManager.canRedo();

    //activa o desactiva las opciones en la barra de herramientas
    gestor.ActivarDeshacer(canUndo);
    gestor.ActivarRehacer(canRedo);
    gestor.ActivarModificado();
}

EventHandler getEventHandler() {    //retorna la instancia de EventHandler (la clase interna que maneja eventos)
    return eventHandler;
}

UndoManager getUndoManager() {    //retorna la instancia de UndoManager (administrador de edicion)
    return undoManager;
}

static boolean documentHasChanged() {    //retorna el estado del documento actual
    return hasChanged;
}

static void setDocumentChanged(boolean Changed) {    //establece el estado del documento actual
    hasChanged = Changed;
    if (hasChanged) {
        gestor.ActivarModificado();
    }else{
        gestor.DesactivarModificado();
    }
}

static void setDocumentInit(){
    hasInit = true;
}

JTextArea getJTextArea() {    //retorna la instancia de JTextArea (area de edicion)
    return this;
}

static File getCurrentFile() {    //retorna la instancia de File (el archivo actual)
    return currentFile;
}

void setCurrentFile(File currentFile) {    //establece el archivo actual
    this.currentFile = currentFile;
    setCurrentDirectory(new File(carpetaDelArchivo(currentFile)));
}

static File getCurrentDirectory() {    //retorna la instancia de File (el directorio actual)
    return currentDirectory;
}

void setCurrentDirectory(File currentDirectory) {    //establece el directorio actual
    if (!currentDirectory.getName().contentEquals("")){
        this.currentDirectory = currentDirectory;
        estado.tomaDirectorioClases(currentDirectory.getAbsolutePath());
    }
}

String getFilePath() {    //retorna la instancia de la etiqueta sbFilePath
    return sbFilePath;
}

void setFilePath(String path) {    //retorna la instancia de la etiqueta sbFilePath
    sbFilePath = path;
}

String getFileSize() {    //retorna la instancia de la etiqueta sbFileSize
    return sbFileSize;
}

void setFileSize(String size) {    //retorna la instancia de la etiqueta sbFilePath
    sbFileSize = size;
}


/* la clase EventHandler extiende e implementa las clases e interfaces necesarias para
atender y manejar los eventos sobre la GUI principal del editor */
class EventHandler extends MouseAdapter implements ActionListener,
                                                       CaretListener,
                                                       UndoableEditListener {

@Override
public void actionPerformed(ActionEvent ae) {
    String ac = ae.getActionCommand();    //se obtiene el nombre del comando ejecutado

    if (ac.equals("cmd_new") == true) {             //opcion seleccionada: "Nuevo"
        accionesEditor.actionNew(PanelDatos.estaInicializadaTabla(3));
    } else if (ac.equals("cmd_open") == true) {     //opcion seleccionada: "Abrir"
        accionesEditor.actionOpen(PanelDatos.estaInicializadaTabla(3));
    } else if (ac.equals("cmd_save") == true) {     //opcion seleccionada: "Guardar"
        accionesEditor.actionSave();
    } else if (ac.equals("cmd_saveas") == true) {   //opcion seleccionada: "Guardar como"
        accionesEditor.actionSaveAs();
    } else if (ac.equals("cmd_undo") == true) {     //opcion seleccionada: "Deshacer"
        accionesEditor.actionUndo();
    } else if (ac.equals("cmd_redo") == true) {     //opcion seleccionada: "Rehacer"
        accionesEditor.actionRedo();
    } else if (ac.equals("cmd_cut") == true) {      //opcion seleccionada: "Cortar"
        //corta el texto seleccionado en el documento
        texto.cut();
    } else if (ac.equals("cmd_copy") == true) {    //opcion seleccionada: "Copiar"
        //copia el texto seleccionado en el documento
        texto.copy();
    } else if (ac.equals("cmd_paste") == true) {    //opcion seleccionada: "Pegar"
        //pega en el documento el texto del portapapeles
        texto.paste();
    } else if (ac.equals("cmd_gotoline") == true) { //opcion seleccionada: "Ir a la linea..."
        accionesEditor.actionGoToLine();
    } else if (ac.equals("cmd_search") == true) {   //opcion seleccionada: "Buscar"
        accionesEditor.actionSearch();
    } else if (ac.equals("cmd_searchnext") == true) {//opcion seleccionada: "Buscar siguiente"
        accionesEditor.actionSearchNext();
    } else if (ac.equals("cmd_selectall") == true) {//opcion seleccionada: "Seleccionar todo"
        texto.selectAll();
    }  
}

@Override
public void caretUpdate(CaretEvent e) {
    final int caretPos;  //valor de la posicion del cursor sin inicializar
    int y = 1;           //valor de la linea inicialmente en 1
    int x = 1;           //valor de la columna inicialmente en 1

    try {
        //obtiene la posicion del cursor con respecto al inicio del JTextArea (area de edicion)
        caretPos = texto.getCaretPosition();
        //sabiendo lo anterior se obtiene el valor de la linea actual (se cuenta desde 0)
        y = texto.getLineOfOffset(caretPos);

        /** a la posicion del cursor se le resta la posicion del inicio de la linea para
           determinar el valor de la columna actual */
        x = caretPos - texto.getLineStartOffset(y);

        //al valor de la linea actual se le suma 1 porque estas comienzan contandose desde 0
        y += 1;
    } catch (BadLocationException ex) {    //en caso de que ocurra una excepcion
        System.err.println(ex);
    }

    /** muestra la informacion recolectada en la etiqueta sbCaretPos de la
       barra de estado, tambien se incluye el numero total de lineas */
    sbCaretPos = mensaje2 + texto.getLineCount() + " Cursor (Y: " + y + ", X: " + x +")";
    gestor.ActualizarEstado(sbCaretPos);
    gestor.ActualizarNumeroLineas(texto.getLineCount());
}

@Override
public void undoableEditHappened(UndoableEditEvent uee) {
    /* el cambio realizado en el area de edicion se guarda en el buffer del administrador de edicion */
    undoManager.addEdit(uee.getEdit());
    updateControls();     //actualiza el estado de las opciones "Deshacer" y "Rehacer"

    hasChanged = true;    //marca el documento como modificado
    gestor.ActivarModificado();
}
}

public static void actualizarTituloVentana(File archivo){
            int i;
            StringTokenizer st;
            String[] cads;
    
            st = new StringTokenizer(archivo.toString(),File.separator);
            cads = new String[st.countTokens()];
            for (i=0; i<cads.length; i++){
                cads[i]=st.nextToken();
            }
            ventana.setTitle(mensaje1 + " ["+cads[cads.length-1]+"]");
}

public static void actualizarTituloVentana(){
            ventana.setTitle(mensaje1);
}

public static String carpetaDelArchivo(File archivo){
    if (archivo!=null){
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
    }else{
        return "";
    }
}

/*##############################################################################
 *              COMPILACION Y EJECUCION DE METODOS DE CLASE
 #############################################################################*/

/*#######################  Buscador de motor Java  #########################*/
class tarea_buscador extends Thread {
    public void run() {buscarCompiladorJava();}
        void buscarCompiladorJava(){
            //String mot = new String();
            String motores = new String();
            final LinkedList<File> ficherosjavac = new LinkedList<File>();
            
            final XMLEstado estado = new XMLEstado();
    
            String so = System.getProperty("os.name").toLowerCase();
    /*#####################   para Windows  ################################*/  
            if(so.indexOf("win") >= 0) {
                Timer timer = new Timer (1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ventana.punteroEspera(mensaje9);
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        buscadorFicherosRecursivo("C:"+File.separator+"Archivos de programa"+File.separator+"Java",
                            dameRegex("javac.exe"), ficherosjavac);
                        /*#####################################*/
                        if (ficherosjavac.size() == 0)
                            buscadorFicherosRecursivo("C:"+File.separator+"Archivos de programa (x86)"+File.separator+"Java",
                                dameRegex("javac.exe"), ficherosjavac);
                        /*#####################################*/
                        if (ficherosjavac.size() == 0)
                            buscadorFicherosRecursivo("C:"+File.separator+"Program Files"+File.separator+"Java",
                                dameRegex("javac.exe"), ficherosjavac);
                        /*#####################################*/
                        if (ficherosjavac.size() == 0)
                            buscadorFicherosRecursivo("C:"+File.separator+"Program Files (x86)"+File.separator+"Java",
                                dameRegex("javac.exe"), ficherosjavac);
                        /*#####################################*/
                        if (ficherosjavac.size() != 0)
                            motor = ficherosjavac.get(0).getAbsolutePath();
                        ventana.punteroNormal(mensaje10);
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        motor = accionesEditor.muestraMotor (motor);
                        estado.tomaJvm(motor);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
    /*#########################   para Mac  ################################*/
            if(so.indexOf("mac") >= 0) {
                Timer timer = new Timer (1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ventana.punteroEspera(mensaje9);
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        motor = "javac";
                        ventana.punteroNormal(mensaje10);
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        motor = accionesEditor.muestraMotor (motor);
                        estado.tomaJvm(motor);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
    /*#######################   para Linux  ################################*/
            if(so.indexOf("nix") >=0 || so.indexOf("nux") >=0) {
                Timer timer = new Timer (1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ventana.punteroEspera(mensaje9);
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        buscadorFicherosRecursivo(File.separator+"usr"+File.separator+"Java",
                            dameRegex("javac"), ficherosjavac);
                        if (ficherosjavac.size() != 0)
                            motor = ficherosjavac.get(0).getAbsolutePath();
                        ventana.punteroNormal(mensaje10);
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        motor = accionesEditor.muestraMotor (motor);
                        estado.tomaJvm(motor);
                    }
                });
                timer.setRepeats(false);
                timer.start();     
            }
        }
}

private static void buscadorFicherosRecursivo(String pathInicial, String mascara,
        LinkedList<File> listaFicheros){
            File directorioInicial = new File(pathInicial);
            if (directorioInicial.isDirectory()){
                File[] ficheros = directorioInicial.listFiles();
                for (int i = 0; i < ficheros.length; i++){
                    if (ficheros[i].isDirectory())
                        buscadorFicherosRecursivo(ficheros[i].getAbsolutePath(),
                                mascara, listaFicheros);
                    else if (Pattern.matches(mascara, ficheros[i].getName()))
                        listaFicheros.add(ficheros[i]);
                }
            }
}

private static String dameRegex(String mascara){
    mascara = mascara.replace(".", "\\.");
    mascara = mascara.replace("*", ".*");
    mascara = mascara.replace("?", ".");
    return mascara;
}
/*********************************************************************************/ 


public Boolean Nuevo(){
    if (accionesEditor.actionNew(PanelDatos.estaInicializadaTabla(3)) && getCurrentFile() != null){
        updateControls();
        gestor.ActivarCopiaPega(true);
        gestor.ActivarNoCompila();
        gestor.DesactivarModificado();
        return true;
    } else {
        //gestor.ActivarCopiaPega(false);
        return false;
    }
}

public void Abrir(){
    if (accionesEditor.actionOpen(PanelDatos.estaInicializadaTabla(3)) && getCurrentFile() != null){
        updateControls();

        if(metodosElegidos != null){
        	metodosElegidos = null;     // Puesto a null para el nuevo fichero
        }
        
        PanelDatos.resetTotalTablas();  
        resetNumEjecuciones();  
    	PanelDatos.panel_tablas.setSelectedIndex(0);
    	gestor.DesactivarGraficas();
        
        gestor.ActivarCopiaPega(true);
        gestor.ActivarNoCompila();
        gestor.DesactivarModificado();
        gestor.DesactivarTablaConDatos(); 
        PanelEditor.estado.setText("");
        PanelEditor.estado2.setText("");
        tc = new tarea_compila();
        tc.start();
    } else {
        //gestor.ActivarCopiaPega(false);
    }
}

class inicializarCargador extends Thread {
    public void run() {nuevoCargador();}
        private void nuevoCargador(){
    
            StringTokenizer st;
            String[] cads;
            String cadena = new String();;
            int i;
            String so = System.getProperty("os.name").toLowerCase();
            try{
                st = new StringTokenizer(currentFile.getAbsolutePath(),File.separator);
                cads = new String[st.countTokens()];
                for (i=0; i<cads.length-1; i++){
                   cads[i]=st.nextToken();               
                }                                        
                for (i=0; i<cads.length-1; i++){
                    if((so.indexOf("win") >= 0) & i==0) {
                        cadena = cadena.concat(cads[i]);    //en windows sin "/" al principio
                    }else{
                        cadena = cadena.concat(File.separator+cads[i]); //con "/" al principio
                    }
                }
                cadena = cadena.concat(File.separator);
                //#################################################
                File file = new File(cadena);
                URL url = file.toURL();
                URL[] urls = new URL[]{url};
                cargador = new URLClassLoader(urls);
                //#################################################
                ventana.punteroNormal(cadena);
                iC.stop();
            } catch (MalformedURLException e){
                ventana.punteroNormal(e.getCause().toString());
                e.printStackTrace();
            }
        }
}


public void Compilar(){
    PanelEditor.estado.setText("");//
    PanelEditor.estado2.setText("");//
	
    if (PanelDatos.estaInicializadaTabla(3)){
    
        JLabel exito = new JLabel(mensaje15);
        int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje14,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(PanelDatos.class.getResource("imagenes/aviso.gif")),
            botones, botones[0]);
        if (respuesta == JOptionPane.OK_OPTION){
            PanelDatos.resetTotalTablas();
            resetNumEjecuciones();
            if (documentHasChanged()){
                Salvar();
            }
            if (getCurrentFile() != null){
                gestor.ActivarNoCompila();
                tc = new tarea_compila();
                tc.start();
            }
        }
        
    } else {
        PanelDatos.resetTotalTablas();
        resetNumEjecuciones();
        if (documentHasChanged()){
            Salvar();
        }
        if (getCurrentFile() != null){
            gestor.ActivarNoCompila();
            tc = new tarea_compila();
            tc.start();
        }
    }
}

class tarea_compila extends Thread {
	int contador = 0;
    public void run() {escompilable();}
        private void escompilable() {
            ventana.punteroEspera(mensaje11);
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            //construye un String[] donde se añadira el resultado
            JTextArea textArea = new JTextArea(20, 95);
            JTextArea textArea2 = new JTextArea(10, 95);
        	Font f1 = new Font ("CONSOLAS",Font.PLAIN,12);
        	textArea.setFont(f1);
            textArea.setText("");
            textArea2.setText("");//
            StringTokenizer st;
            String executable = new String();
            String path = new String();
            Boolean compilable = true;
            String error = "";
        
            String so = System.getProperty("os.name").toLowerCase();
            if(so.indexOf("win") >= 0) {
               // executable = "\""+motor+"\"";
            	executable = "\""+ estado.dameJvm() +"\""; // para que se cargue el último JDK configurado
                path = "\""+currentFile.getPath()+"\"";
            }else{
                executable = motor;
                path = currentFile.getPath();
            }
            try {
                //Process proceso = Runtime.getRuntime().exec(executable+" -g:nodebug "+path);
            	System.out.println("executable: " + executable);
            	System.out.println("path: " + path);
            	String flags = "-encoding ISO-8859-1";
                Process proceso = Runtime.getRuntime().exec(executable + " " + path + " " + flags);
                try {
                 //   ventana.punteroEspera(mensaje11);
                    proceso.waitFor();
                }catch (InterruptedException ex) {
                    accionesEditor.muestraString (mensaje18);
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                while (br.ready()){
                    String aux = br.readLine();
                    if (aux.compareTo("") != 0){
                        compilable = false;
                        textArea.append(aux+"\n");
                    }
                }
                br = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));   	
                while (br.ready()){
                    String aux = br.readLine();
                    String[] parseo = Parsear(aux, contador);           
                    
                   	if((parseo.length == 3) && (aux.compareTo("") != 0)){
                    	textArea.append("\n" + mensaje34);
                        textArea.append(parseo[0]);
                        textArea.append("\n" + mensaje35);
                        textArea.append(parseo[1]);
                        textArea.append("\n" + mensaje36);
                        textArea.append(parseo[2] + "\n");
                   	}else if((aux.indexOf("error")!=-1) && (aux.length()<9)){ //línea errores
                    	PanelEditor.estado2.setText(parseo[0]);
                    }else if(aux.indexOf("^")!=-1){ //línea 2 código
                    	textArea.append(parseo[0]);
                    }else{ //línea 1 código
                    	textArea.append(parseo[0] + "\n");
                    }
                    if (aux.compareTo("") != 0){
                    	compilable = false;
                      //  textArea.append(aux+"\n");
                    }
                    contador++;
                }
            }catch (IOException ioe){
                textArea.append(ioe.getCause().toString()+"\n");
                textArea.append("Motor: "+executable+"\n");
                textArea.append("Path: "+path+"\n");
                ioe.printStackTrace();
                compilable = false;
                error = ioe.getCause().toString();
            }

            if (compilable){
                indiceSignatura = -1;
                PanelEditor.estado2.setText("");
                iC = new inicializarCargador();
                iC.start();
                while (iC.isAlive()){}
                hasCompiled = true;             
                gestor.ActivarCompilado();
                ventana.punteroNormal(mensaje12);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }else if (error.contains("CreateProcess error")){
                hasCompiled = false;
                gestor.ActivarNoCompila();
                ventana.punteroNormal(mensaje13);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }else{
                hasCompiled = false;
                gestor.ActivarNoCompila();
                ventana.punteroNormal(textArea.getText());
                //ventana.punteroNormal(mensaje13);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            tc.stop();
        }
    }

public String[] Parsear(String aux, int auxLinea){
	String[] array = new String[1];;
	if((aux.indexOf("error")!=-1) && (aux.length()<9)){ //última línea
		array = new String[1];
		int numErrores = aux.indexOf("error");
		String numErroresString = aux.substring(0,numErrores);
		array[0] = mensaje33 + numErroresString;
		return array;
	}else{		
		if(auxLinea%3 == 0){
			System.out.println("IF 1");
			array = new String[3];
			array = ParsearTipo0(aux);		
			return array;
		}else if(auxLinea%3 == 1){
			System.out.println("IF 2");
			array = new String[1];
			array[0] = aux;
			return array;
		}else if(auxLinea%3 == 2){
			System.out.println("IF 3");
			array = new String[1];
			array[0] = aux;
			return array;
		}
	}
	return array;
}

public String[] ParsearTipo0(String aux){ //tipo líneas 0	
	int finRuta = aux.indexOf(".java") + 5;
	String fichero = aux.substring(0,finRuta);
	int finLinea = aux.indexOf(": ");
	
	String linea = aux.substring(finRuta+1,finLinea);
	String error = aux.substring(finLinea+1, aux.length());
	
	String[] array = {fichero, linea, error};
	return array;
}

public void MostrarSignaturas(){
    if (hasCompiled){
        tS = new tarea_dameSignaturas();
        tS.start();
    }
}

class tarea_dameSignaturas extends Thread {
    public void run() {dameSignaturas();}
        public void dameSignaturas() {
            int i,j;
            StringTokenizer st;
            String[] cads, cads2;
            String cadena;
            Class[][] signaturas;
            Method posibleMetodoElegido;
        
            st = new StringTokenizer(currentFile.getAbsolutePath(),File.separator);
            cads = new String[st.countTokens()];
                      
            for (i=0; i<cads.length; i++){
                cads[i]=st.nextToken();  
            }
            
            cadena = new String();
            
            //########  Se sustituye File.separator por "."  #########
            
            st = new StringTokenizer(cads[cads.length-1],".");
            cads2 = new String[st.countTokens()];
            for (i=0; i<cads2.length-1; i++){
                cads2[i]=st.nextToken();        //Se quita la extension .java
            }                               
            if (accionesEditor.actionIsPackage()) {
                cadena = cads[cads.length-2];
                cadena = cadena.concat("."+cads2[0]);//paquete.clase
            }else{
                cadena = cads2[0];//clase
            }
 
            //archivo
            Class c=null;
        
            try {
                c = cargador.loadClass(cadena);       
                Method[] mm = c.getDeclaredMethods();                
                signaturas = new Class[mm.length][];
                
                //##################guardo todas las signaturas##################
                
                for(i=0; i<mm.length; i++){
                    Class salida = mm[i].getReturnType();
                    Class[] entradas = mm[i].getParameterTypes();
                    signaturas[i] = new Class[entradas.length+1];
                    signaturas[i][0] = salida;
                    for(j=0; j<entradas.length; j++){
                        signaturas[i][j+1] = entradas[j];
                    }                 
                }
                
                String[] signat = new String[mm.length];
                
                for(i=0; i<mm.length; i++){
                    String nombre = "";
                    nombre = nombre.concat(signaturas[i][0].toString()+" (");
                    for(j=1; j<signaturas[i].length; j++){
                        if (j<signaturas[i].length-1){
                            nombre = nombre.concat(signaturas[i][j].toString()+",");
                        }else{
                            nombre = nombre.concat(signaturas[i][j].toString());
                        }
                    }
                    nombre = nombre.concat(" )");                    
                    nombre = limpiarTipos(nombre, cadena);                 
                    signat[i] = nombre;
                    
                }               
                
                //#####################################################
                int[] sig = accionesEditor.muestraSignaturas(quitarRepetidos(signat), signat, mm);

                if((sig.length != 0) && (sig[0] != AccionesEditor.NOSELECTED) && (sig[1] != AccionesEditor.NOSELECTED)){ // Validación de que se ha seleccionado signatura y objetivo para que no se habiliten botones sino
                
                if (sig.length != 0){
                   	gestor.DesactivarGraficas();
                    Boolean cambiar = false;
                    if (sig[0] != maximizar && sig[1] != indiceSignatura){
                        if (PanelDatos.estaInicializadaTabla(3)){
                            JLabel exito = new JLabel(mensaje21);
                            int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje20,
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon(PanelDatos.class.getResource("imagenes/aviso.gif")),
                                botones, botones[0]);
                            if (respuesta == JOptionPane.OK_OPTION){
                                cambiar = true;
                                maximizar = sig[0];
                                if (maximizar == 0){
                                    gestor.minimizar();
                                }else if (maximizar == 1){
                                    gestor.maximizar();
                                }
                            } 

                        } else {
                            cambiar = true;
                                                 
                            maximizar = sig[0];
                            if (maximizar == 0){
                                gestor.minimizar();
                            }else if (maximizar == 1){
                                gestor.maximizar();
                            }
                        }
                    } else if (sig[0] != maximizar){
                        if (PanelDatos.estaInicializadaTabla(3)){
                            JLabel exito = new JLabel(mensaje30);
                            int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje29,
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon(PanelDatos.class.getResource("imagenes/aviso.gif")),
                                botones, botones[0]);
                            if (respuesta == JOptionPane.OK_OPTION){
                                maximizar = sig[0];
                                if (maximizar == 0){
                                    gestor.minimizar();
                                }else if (maximizar == 1){
                                    gestor.maximizar();
                                }
                            } 

                        } else {
                            maximizar = sig[0];
                            if (maximizar == 0){
                                gestor.minimizar();
                            }else if (maximizar == 1){
                                gestor.maximizar();
                            }
                        }
                    } else if (sig[1] != indiceSignatura){
                        if (PanelDatos.estaInicializadaTabla(3)){
                            JLabel exito = new JLabel(mensaje32);
                            int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje31,
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon(PanelDatos.class.getResource("imagenes/aviso.gif")),
                                botones, botones[0]);
                            if (respuesta == JOptionPane.OK_OPTION){
                                cambiar = true;
                                maximizar = sig[0];
                                if (maximizar == 0){
                                    gestor.minimizar();
                                }else if (maximizar == 1){
                                    gestor.maximizar();
                                }
                            } 

                        } else {
                            cambiar = true;
                            maximizar = sig[0];
                            if (maximizar == 0){
                                gestor.minimizar();
                            }else if (maximizar == 1){
                                gestor.maximizar();
                            }
                        }
                    }
                    if (cambiar) {
                    	//Reseteo de la variable ID con el número de fila:
                        PanelDatos.modeloDatos.actualizaId();
                    	//XMLImporter.actualizaId();
                    	
                    	indiceSignatura = sig[1];
                 
                        //ordenar signat:
                        Arrays.sort(signat, String.CASE_INSENSITIVE_ORDER);
                                              
                        primeraVez = true;
                        st = new StringTokenizer(quitarRepetidos(signat)[sig[1]],"(");
                        cads = new String[st.countTokens()];     //Quito Salida (
                        for (i=0; i<cads.length; i++){
                            cads[i]=st.nextToken();              //Paso el tokenizado a array
                        }
                        tipoAComparar = cads[0];
                        tipoAComparar = tipoAComparar.replace(" ", "");
                        cads[1] = cads[1].replace(" )", "");     //Quito )
                        st = new StringTokenizer(cads[1],", ");
                        cads = new String[st.countTokens()];     //Tokenizo por ,
                        for (i=0; i<cads.length; i++){
                            cads[i]=st.nextToken();              //Paso el tokenizado a array
                        }
                        gestor.ActivarElegidaSignatura();
                        Class[] clases = new Class[cads.length];
                        for (i=0; i<cads.length; i++){
                            clases[i]=Class.forName("java.lang.String");//Doy clase String a las celdas
                        }
                        PanelDatos.resetTotalTablas();
                        resetNumEjecuciones();
                        
                        //Copia incluyendo la columna ID:                                    
                        String[] cadsN = new String[(cads.length)+1];
                        cadsN[0] = "Num.";                         
                        for(int u=1; u<cadsN.length; u++){
                        	cadsN[u] = cads[u-1];
                        }
                        
                        //Copia incluyendo la columna ID: 
                        Class[] clasesN = new Class[(clases.length)+1];
                        clasesN[0] = Class.forName("java.lang.String");                      
                        for(int e=1; e<clasesN.length; e++){
                        	clasesN[e] = clases[e-1];
                        }

                        gestor.inicializarTablaDatos(cadsN, clasesN);  
                        
                        PanelDatos.panel_tablas.setSelectedIndex(0);
                        gestor.DesactivarExportacionDatos();
                        gestor.DesactivarBorraFilasDatos();
                        gestor.DesactivarEjecuciones();
                    }
                }
                }
                //#####################################################  
                //#####################################################
                tS.stop();
        
            } catch (ClassNotFoundException e){
                accionesEditor.muestraString (mensaje8);
                e.printStackTrace();
            } catch (SecurityException e){
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (NoClassDefFoundError e){
                e.printStackTrace();
            }
       }
    }



class tarea_dameMetodos extends Thread {
    public void run() {dameMetodos();}
        public void dameMetodos() {
            int i;
            StringTokenizer st;
            String[] cads, cads2;
            String cadena;
        
            st = new StringTokenizer(currentFile.getAbsolutePath(),File.separator);
            cads = new String[st.countTokens()];
            for (i=0; i<cads.length; i++){
                cads[i]=st.nextToken();              
            }
            
            cadena = new String();
            
            //########  Se sustituye File.separator por "."  #########
            
            st = new StringTokenizer(cads[cads.length-1],".");
            cads2 = new String[st.countTokens()];
            for (i=0; i<cads2.length-1; i++){
                cads2[i]=st.nextToken();        //Se quita la extension .java
            }                               
            if (accionesEditor.actionIsPackage()) {
                cadena = cads[cads.length-2];
                cadena = cadena.concat("."+cads2[0]);//paquete.clase
            }else{
                cadena = cads2[0];//clase
            }
 
            //archivo
            Class c=null;
        
            try {
                c = cargador.loadClass(cadena);
                
                Method[] mm = c.getDeclaredMethods();
                
                //##################filtro de metodos##################
                int coincidentes = 0;
                
                for(i=0; i<mm.length; i++){ 
                    Class[] entradas = mm[i].getParameterTypes();
                    if (gestor.sonTiposIguales(entradas, 4)
                    		&&mm[i].toString().indexOf ("private") == -1
                            &&mm[i].toString().indexOf ("void") == -1
                            && limpiarTipos(mm[i].getReturnType().toString(), cadena).indexOf(tipoAComparar) != -1){
                        coincidentes++;
                    };                   
                }
           //    System.out.println("coincidentes= "+coincidentes);
                mcoincidentes = new Method[coincidentes];
                
                int j = -1;
                for(i=0; i<mm.length; i++){
                    Class[] entradas = mm[i].getParameterTypes();
                    if (gestor.sonTiposIguales(entradas , 4)
                    		&&mm[i].toString().indexOf ("private") == -1
                            &&mm[i].toString().indexOf ("void") == -1
                            && limpiarTipos(mm[i].getReturnType().toString(), cadena).indexOf(tipoAComparar) != -1){
                        j++;
                        mcoincidentes[j] = mm[i];                     
                    };
                }
                
                //################## ORDENACION DE METODOS #####################                
                mcoincidentes = ordenarMetodos(mcoincidentes);                
                //##############################################################
                
                String[] metodos = new String[coincidentes];
                
                for(i=0; i<coincidentes; i++){
                    String nombre = mcoincidentes[i].toString();
                    nombre = nombre.replace("public", "");
                    nombre = nombre.replace("private", "");
                    nombre = nombre.replace("static", "");
                    nombre = nombre.replace("java.", "");
                    nombre = nombre.replace("lang.", "");
                    nombre = nombre.replace(cadena+".", "");
                    metodos[i] = nombre;
                }
                //############## ELEGIR VARIOS METODOS ################
         /*       if (ejecutarVariosMetodos){*/
                	int[][] mets = accionesEditor.muestraMetodos(metodos);
                
                    if (mets.length>0){
                        metodosElegidos = new Method[mets[1].length];
                        for (int m=0; m<mets[1].length; m++){
                            metodosElegidos[m] = mcoincidentes[mets[1][m]];
                        }
                        //############## AVERIGUAR SI SE HA CAMBIADO DE OPTIMUS ##############
                        boolean cambioDeOptimus = false;
                        if (mets[0][0]!=optimus){
                            cambioDeOptimus = true;
                        }
                        //############## AVERIGUAR SI SE HA CAMBIADO DE METODOS ##############
                        boolean cambioDeMetodos = false;
                        if (metodosElegidos.length == metodosAnteriores.length){
                            for(i=0; i<metodosElegidos.length; i++){
                                if (metodosElegidos[i].getName() != metodosAnteriores[i].getName()){
                                    cambioDeMetodos = true;
                                }
                            }
                        }else{
                            cambioDeMetodos = true;
                        }
                     /*   if (!gestor.estaInicializadaTabla(3)){
                        	System.out.println("3");
                            cambioDeMetodos = true;
                        }*/
                        
                        System.out.println("cambioDeOptimus = " + cambioDeOptimus);
                        System.out.println("cambioDeMetodos = " + cambioDeMetodos);
                        
                        //####################################################################
                        /*if (cambioDeOptimus && cambioDeMetodos){
                            JLabel aviso = new JLabel(mensaje24);
                            if(PanelDatos.estaInicializadaTabla(3) == true){ //
                            	System.out.println("EXISTEN RESULTADOS1");
                            int respuesta = JOptionPane.showOptionDialog(null, aviso, mensaje23,
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
                                new ImageIcon(AccionesEditor.class.getResource("imagenes/aviso.gif")),
                                botones, botones[0]);
                            if (respuesta != JOptionPane.OK_OPTION){
                                tM.stop();
                            }else{
                                //prueba 31ago
                            		System.out.println("Desactivar iconos de resultados y gráficas");
                            		gestor.DesactivarExportaciones();
                            		gestor.DesactivarGraficas();
                            		gestor.DesactivarBorraFilas();
                            	}
                            }//
                            primeraVez = true;
                        }else*/if (cambioDeMetodos){
                            JLabel aviso = new JLabel(mensaje26);
                            if(PanelDatos.estaInicializadaTabla(3) == true){ //
                            	System.out.println("EXISTEN RESULTADOS2");
                            int respuesta = JOptionPane.showOptionDialog(null, aviso, mensaje25,
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
                                new ImageIcon(AccionesEditor.class.getResource("imagenes/aviso.gif")),
                                botones, botones[0]);

                            	if (respuesta != JOptionPane.OK_OPTION){
                            		System.out.println("Pulsado Cancelar1");                            	
                            		tM.stop();
                            	}else{
                            		System.out.println("Desactivar iconos de resultados y gráficas");
                            		gestor.DesactivarExportaciones();
                            		gestor.DesactivarGraficas();
                            		gestor.DesactivarBorraFilas();
                            	}
                            }//
                            primeraVez = true;
                        }else if (cambioDeOptimus){
                            JLabel aviso = new JLabel(mensaje28);
                            if(PanelDatos.estaInicializadaTabla(3) == true){ //
                            	System.out.println("EXISTEN RESULTADOS3");
                            int respuesta = JOptionPane.showOptionDialog(null, aviso, mensaje27,
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
                                new ImageIcon(AccionesEditor.class.getResource("imagenes/aviso.gif")),
                                botones, botones[0]);
                            if (respuesta != JOptionPane.OK_OPTION){
                            	System.out.println("Pulsado Cancelar2");
                                tM.stop();
                            }
                            }//
                        }
                        //####################################################################
                        if (cambioDeOptimus){
                            optimus = mets[0][0];                              
                        }
                        //####################################################################
                        if (cambioDeMetodos){                        	
                            metodosAnteriores = new Method[metodosElegidos.length];
                            for(i=0; i<metodosElegidos.length; i++){
                                metodosAnteriores[i] = metodosElegidos[i];
                            }
                            //######### INICIALIZO LA TABLA ###########
                            String[] paraTabla2 = new String[metodosElegidos.length+1];
                            String[] paraTabla3 = new String[metodosElegidos.length+1];
                            paraTabla2[0] = "";
                            paraTabla3[0] = mensaje22;
                            for(i=0; i<metodosElegidos.length; i++){
                                paraTabla2[i+1] = metodosElegidos[i].getName();
                                paraTabla3[i+1] = metodosElegidos[i].getName();
                            }
                            gestor.inicializarTabla1();
                            gestor.inicializarTabla2(paraTabla2, tipoAComparar);
                            gestor.inicializarTabla3(paraTabla3);
                            resetNumEjecuciones();
                        }
                        //###################################################################
                        gestor.inicializarOptimo(optimus, cambioDeOptimus); 
                        
                        // Actualización de las gráficas con los nuevos valores de los resultados obtenidos tras cambiar el método óptimo:
                        if((cambioDeOptimus == true) && (PanelDatos.estaInicializadaTabla(3) == true )){
                        	System.out.println("Cambio en método óptimo -> Actualizar gráficas");
                        	PanelDatos.actualizarGraficas();
                        }
                    } else{
                        metodosElegidos = new Method[0];
                    }
                //#####################################################
                tM.stop();        
            } catch (ClassNotFoundException e){
                accionesEditor.muestraString (mensaje8);
                e.printStackTrace();
            } catch (SecurityException e){
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (NoClassDefFoundError e){
                e.printStackTrace();
            }
       }
    }

class tarea_dameMetodos2 extends Thread {
    public void run() {dameMetodos();}
    public void dameMetodos() {
    	
    	  int i;
          StringTokenizer st;
          String[] cads, cads2;
          String cadena;
      
          st = new StringTokenizer(currentFile.getAbsolutePath(),File.separator);
          cads = new String[st.countTokens()];
          for (i=0; i<cads.length; i++){
              cads[i]=st.nextToken();              
          }
          
          cadena = new String();
          
          //########  Se sustituye File.separator por "."  #########
          
          st = new StringTokenizer(cads[cads.length-1],".");
          cads2 = new String[st.countTokens()];
          for (i=0; i<cads2.length-1; i++){
              cads2[i]=st.nextToken();        //Se quita la extension .java
          }                               
          if (accionesEditor.actionIsPackage()) {
              cadena = cads[cads.length-2];
              cadena = cadena.concat("."+cads2[0]);//paquete.clase
          }else{
              cadena = cads2[0];//clase
          }

          //archivo
          Class c=null;
      
          try {
              c = cargador.loadClass(cadena);
              
              Method[] mm = c.getDeclaredMethods();
              
              //##################filtro de metodos##################
              int coincidentes = 0;
              
              for(i=0; i<mm.length; i++){ 
                  Class[] entradas = mm[i].getParameterTypes();
                  if (gestor.sonTiposIguales(entradas, 4)
                  		&&mm[i].toString().indexOf ("private") == -1
                          &&mm[i].toString().indexOf ("void") == -1
                          && limpiarTipos(mm[i].getReturnType().toString(), cadena).indexOf(tipoAComparar) != -1){
                      coincidentes++;
                  };                   
              }
              Method[] mcoincidentes = new Method[coincidentes];
              
              int j = -1;
              for(i=0; i<mm.length; i++){
                  Class[] entradas = mm[i].getParameterTypes();
                  if (gestor.sonTiposIguales(entradas , 4)
                  		&&mm[i].toString().indexOf ("private") == -1
                          &&mm[i].toString().indexOf ("void") == -1
                          && limpiarTipos(mm[i].getReturnType().toString(), cadena).indexOf(tipoAComparar) != -1){
                      j++;
                      mcoincidentes[j] = mm[i];
                      
                  };
              }
              
              //################## ORDENACION DE METODOS #####################              
              mcoincidentes = ordenarMetodos(mcoincidentes);              
              //##############################################################
              
              String[] metodos = new String[coincidentes];
              
              for(i=0; i<coincidentes; i++){
                  String nombre = mcoincidentes[i].toString();
                  nombre = nombre.replace("public", "");
                  nombre = nombre.replace("private", "");
                  nombre = nombre.replace("static", "");
                  nombre = nombre.replace("java.", "");
                  nombre = nombre.replace("lang.", "");
                  nombre = nombre.replace(cadena+".", "");
                  metodos[i] = nombre;
              }
                                   
              int[] seleccion = PanelDatos.dameSeleccion(4);
              if(seleccion == null){
            	  System.out.println("2SELECCIONE UN DATO DE LA PESTAÑA 'DATOS'");
            	  accionesEditor.muestraString(mensaje42);
              }
              else{
            	  SeleccionarDatos();                                     	  
            	  
            	  //Filtrado del total de métodos a los elegidos en las condiciones del experimento:
            	  
            	  
            	  //################## ELEGIR UN METODO #################
            	  int met = accionesEditor.muestraMetodos2(metodos);
            	  System.out.println("	aa MET : " + met);
            	  if (met != -1){                           	 
            	  
            		 // if(datosElegidos[0] != -2){//              		  
            			  System.out.println("	Pulsado aceptar");
              
            			  metodosElegidos = new Method[1];
            			  metodosElegidos[0] = mcoincidentes[met];	
                  
            			  System.out.println("FILA DE TABLA DATOS ELEGIDA: " + datosElegidos[0]);
            			  if (PanelDatos.dameOcultoAt(datosElegidos[0]) == "yaEjecutado"){ //Comprobación de ejecución previa con el mismo dato
            				  System.out.println("ALGÚN DATO YA EJECUTADO ANTES");
            				  accionesEditor.muestraString(mensaje16a);
            				  // imprimirNumEjec = false;
            			  }else{                 
            				  if (!gestor.estaInicializadaTabla(3)){
            					//  cambioDeMetodo = true;
            				  }
                  
            			  //####################################################################
            			/*	  if (cambioDeMetodo){      
            					  System.out.println("cambio de metodo !!!");
            					  metodosAnteriores = new Method[1];
            					  metodosAnteriores[0] = metodosElegidos[0];
            					  //######### INICIALIZO LA TABLA ###########
            					  String[] paraTabla2 = new String[2];
            					  String[] paraTabla3 = new String[2];
            					  paraTabla2[0] = "";
            					  paraTabla3[0] = mensaje22;
            					  paraTabla2[1] = metodosElegidos[0].getName();
            					  paraTabla3[1] = metodosElegidos[0].getName();     
            					  gestor.inicializarTabla1();
            					  gestor.inicializarTabla2(paraTabla2, tipoAComparar);
            					  gestor.inicializarTabla3(paraTabla3);
            					  gestor.inicializarOptimo(optimus, false);    
            					  resetNumEjecuciones();                  
                      		//#########################################
            				  }*/           		  
            				  EjecutarClase(); //
            			  }  
            		  //}              
            	  } else{
            		  metodosElegidos = new Method[0];
            	  }  
              }/////
              tM2.stop();
              
          } catch (ClassNotFoundException e){
              accionesEditor.muestraString (mensaje8);
              e.printStackTrace();
          } catch (SecurityException e){
              e.printStackTrace();
          } catch (IllegalArgumentException e){
              e.printStackTrace();
          } catch (NoClassDefFoundError e){
              e.printStackTrace();
          }
    }  
}

class tarea_dameMetodos3 extends Thread {
	int opcion;
	
	public tarea_dameMetodos3(int opcion){
		this.opcion = opcion;
	}
    public void run() {
    	dameMetodos();
    	System.out.println("NOMBRE HILO: " + this.getName());
    }
        
    public void dameMetodos(){	

      int numeroColumnasHistorica = PanelDatos.dameNumeroColumnas(2); //Añadido para controlar las ejecuciones ge dato/grupo de métodos tras una de dato/método

  	  int i;
      StringTokenizer st;
      String[] cads, cads2;
      String cadena;
  
      st = new StringTokenizer(currentFile.getAbsolutePath(),File.separator);
      cads = new String[st.countTokens()];
      for (i=0; i<cads.length; i++){
          cads[i]=st.nextToken();              
      }
      
      cadena = new String();
      
      //########  Se sustituye File.separator por "."  #########     
      st = new StringTokenizer(cads[cads.length-1],".");
      cads2 = new String[st.countTokens()];
      for (i=0; i<cads2.length-1; i++){
          cads2[i]=st.nextToken();        //Se quita la extension .java
      }                               
      if (accionesEditor.actionIsPackage()) {
          cadena = cads[cads.length-2];
          cadena = cadena.concat("."+cads2[0]);//paquete.clase
      }else{
          cadena = cads2[0];//clase
      }

      //archivo
      Class c=null;

      try {
          c = cargador.loadClass(cadena);
          
          Method[] mm = c.getDeclaredMethods();
          
          //##################filtro de metodos##################
          int coincidentes = 0;
          
          for(i=0; i<mm.length; i++){ 
              Class[] entradas = mm[i].getParameterTypes();
              if (gestor.sonTiposIguales(entradas, 4)
              		&&mm[i].toString().indexOf ("private") == -1
                      &&mm[i].toString().indexOf ("void") == -1
                      && limpiarTipos(mm[i].getReturnType().toString(), cadena).indexOf(tipoAComparar) != -1){
                  coincidentes++;
              };                   
          }
          Method[] mcoincidentes = new Method[coincidentes];
          
          int j = -1;
          for(i=0; i<mm.length; i++){
              Class[] entradas = mm[i].getParameterTypes();
              if (gestor.sonTiposIguales(entradas , 4)
              		&&mm[i].toString().indexOf ("private") == -1
                      &&mm[i].toString().indexOf ("void") == -1
                      && limpiarTipos(mm[i].getReturnType().toString(), cadena).indexOf(tipoAComparar) != -1){
                  j++;
                  mcoincidentes[j] = mm[i];
                  
              };
          }
          
          //################## ORDENACION DE METODOS #####################              
          mcoincidentes = ordenarMetodos(mcoincidentes);              
          //##############################################################
          
          String[] metodos = new String[coincidentes];
          
          for(i=0; i<coincidentes; i++){
              String nombre = mcoincidentes[i].toString();
              nombre = nombre.replace("public", "");
              nombre = nombre.replace("private", "");
              nombre = nombre.replace("static", "");
              nombre = nombre.replace("java.", "");
              nombre = nombre.replace("lang.", "");
              nombre = nombre.replace(cadena+".", "");
              metodos[i] = nombre;
          }
          
    	int numeroRecordados = 0;
    	int posicion = 0;
    	for(int k=0;k<accionesEditor.mets.length;k++){
    		if(accionesEditor.mets[k] == true){
    			numeroRecordados++;
    		}
    	}
        metodosElegidos = new Method[numeroRecordados];
        int numeroElegidos = 0;
		
		for(int k=0;k<AccionesEditor.mets.length;k++){
			if(AccionesEditor.mets[k] == true){
				numeroElegidos++;			
			}
        }
			
        int[] seleccion = PanelDatos.dameSeleccion(4);
        if(seleccion == null){
        	if(opcion == 3){ //ejecución un dato/varios métodos
      	  		accionesEditor.muestraString(mensaje42);        
        	}else if(opcion == 4){ // ejecución varios datos/varios métodos
      	  		accionesEditor.muestraString(mensaje43);
        	} 
        }else if((opcion == 4) && (seleccion.length == 1)){
        	accionesEditor.muestraString(mensaje45); // se requiere seleccionar más de un dato
        }else if((opcion == 3) && (seleccion.length > 1)){
        	accionesEditor.muestraString(mensaje44); // se requiere seleccionar un único dato
        }else{
                	
      	  	SeleccionarDatos();
        	 
      	  	boolean[] datosElegidosEjecutados = new boolean[datosElegidos.length];
	      
      	  	for(int b=0;b<datosElegidos.length;b++){
      	  		if (PanelDatos.dameOcultoAt(datosElegidos[b]) == "yaEjecutado"){ //Comprobación de ejecución previa con mismos datos
      	  		//	System.out.println("DATO YA EJECUTADO ANTES");
      	  			//accionesEditor.muestraString(mensaje16);
      	  			imprimirNumEjec = false;
      	  			datosElegidosEjecutados[b] = true;
      	  		}else{
      	  		//	System.out.println("DATO NO EJECUTADO ANTES");
      	  			datosElegidosEjecutados[b] = false;
      	  		}    	  		
      	  	}

      	  	int original = datosElegidos.length;
      	  	
      	  	// De los datos seleccionados se filtra el array para mantener sólo los datos que no han sido ejecutados antes
      	  	datosElegidos = filtrarDatosElegidos(datosElegidos, datosElegidosEjecutados);
      	      	  	      	  	
      	  	if(datosElegidos.length == 0){
      	  		if(opcion == 3){
      	  			accionesEditor.muestraString(mensaje16a);
      	  		}else if(opcion == 4){
      	  			accionesEditor.muestraString(mensaje16);
      	  		}
      	  	}else if(original >= (datosElegidos.length +1)){
	  			accionesEditor.muestraString(mensaje16b);
      	  	}      	      	  	
      	  	metodosElegidos = new Method[numeroElegidos];
      	  	int p = 0;
		
      	  	for(int k=0;k<AccionesEditor.metodosRecordados.length;k++){
      	  		if(AccionesEditor.mets[k] == true){
      	  			metodosElegidos[p] = mcoincidentes[k];
      	  			p++;
      	  		}
      	  	}           
			  
      	  	metodosAnteriores = new Method[metodosElegidos.length];
      	  	for(int z=0;z<metodosElegidos.length;z++){
      	  		metodosAnteriores[z] = metodosElegidos[z];
      	  	}			 		        

            metodosAnteriores = new Method[metodosElegidos.length];
            for(i=0; i<metodosElegidos.length; i++){
                metodosAnteriores[i] = metodosElegidos[i];
            }
 //16sept
            EjecutarClase();       	  		    	     	  
            tM3.stop();   
        }
      } catch (ClassNotFoundException e){
          accionesEditor.muestraString (mensaje8);
          e.printStackTrace();
      } catch (SecurityException e){
          e.printStackTrace();
      } catch (IllegalArgumentException e){
          e.printStackTrace();
      } catch (NoClassDefFoundError e){
          e.printStackTrace();
      }
    }
}

public int[] filtrarDatosElegidos(int[] datosElegidos, boolean[] datosElegidosEjecutados){
	int[] datosElegidosFiltrados;
	int numeroNoEjecutados = 0;
	int numeroFiltrados = 0;
	
	for(int h=0;h<datosElegidosEjecutados.length;h++){
		if(datosElegidosEjecutados[h] == false){
			numeroNoEjecutados++;
		}
	}
	
	datosElegidosFiltrados = new int[numeroNoEjecutados];
	for(int g=0;g<datosElegidos.length;g++){
		if(datosElegidosEjecutados[g] == false){
			datosElegidosFiltrados[numeroFiltrados] = datosElegidos[g];
			numeroFiltrados++;
		}
	}
	
	return datosElegidosFiltrados;	
}

public void EjecutarMetodosDatos(boolean variosMetodos, boolean variosDatos){
    ejecutarVariosMetodos = variosMetodos;
    ejecutarVariosDatos = variosDatos;
    if(hasCompiled){
    	if((variosMetodos == false) && (variosDatos == true)){ //SELECTOR DE MÉTODOS
    		System.out.println("EjecutarMetodosDatos SELECTOR DE MÉTODOS");
            tM = new tarea_dameMetodos();
            tM.start();
    	}else if((variosMetodos == false) && (variosDatos == false)){ //UN METODO CON UN DATO
    		System.out.println("EjecutarMetodosDatos UN METODO CON UN DATO");
            tM2 = new tarea_dameMetodos2();
            tM2.start();
    	}else if((variosMetodos == true) && (variosDatos == false)){ //VARIOS METODOS CON UN DATO
    		System.out.println("EjecutarMetodosDatos VARIOS METODOS CON UN DATO");       	
            tM3 = new tarea_dameMetodos3(3);
            tM3.start();	
    	}else if((variosMetodos == true) && (variosDatos == true)){ //VARIOS METODOS CON VARIOS DATOS
    		System.out.println("EjecutarMetodosDatos VARIOS METODOS CON VARIOS DATOS");
            tM3 = new tarea_dameMetodos3(4);
            tM3.start();
    	}
    }
}

public void SeleccionarDatos(){
	int[] seleccion = PanelDatos.dameSeleccion(4);

    if (!ejecutarVariosMetodos && !ejecutarVariosDatos){     //EJECUTAR UN METODO CON UN DATO
    	System.out.println("EJECUTAR UN METODO CON UN DATO");
    //	if(seleccionar){
    //		datosElegidos = PanelDatos.dameFilasSeleccionadas(ejecutarVariosDatos);      
    //	}else{
    		datosElegidos = new int[1];
    		datosElegidos[0] = seleccion[0] - 1; //Para que se coja el valor de la fila en la posición correcta del array
    //	}
    	imprimirNumEjec = true;
       // EjecutarClase();
        primeraVez = false;
    }else if(ejecutarVariosMetodos && !ejecutarVariosDatos){ //EJECUTAR VARIOS METODOS CON UN DATO
    	System.out.println("EJECUTAR VARIOS METODOS CON UN DATO");
    //	if(seleccionar){
    //		datosElegidos = PanelDatos.dameFilasSeleccionadas(ejecutarVariosDatos);
    //	}else{
    		datosElegidos = new int[1];
    		datosElegidos[0] = seleccion[0] - 1;
    //	}
    	imprimirNumEjec = true;    	
       // EjecutarClase();
        primeraVez = false;
    }else if(ejecutarVariosMetodos && ejecutarVariosDatos){  //EJECUTAR VARIOS METODOS CON VARIOS DATOS
    	System.out.println("EJECUTAR VARIOS METODOS CON VARIOS DATOS");
        ejecucionesIntensivas = 0;
        int[] opciones = accionesEditor.muestraOpciones();
        if (opciones[0] == 1){
        	if(PanelDatos.dameNumeroFilas(4) == 0){
        		JOptionPane.showMessageDialog(null, mensaje37, mensaje38, JOptionPane.INFORMATION_MESSAGE); 
        	}else{
     //   		if(seleccionar){
     //   			datosElegidos = PanelDatos.dameFilasSeleccionadas(ejecutarVariosDatos);
     //   		}else{
            		datosElegidos = new int[seleccion.length];
            		for(int o=0;o<seleccion.length;o++){
            			datosElegidos[o] = seleccion[o];
            		}
     //       	}       		
        		imprimirNumEjec = true;
        		//EjecutarClase();
        	}
        } else if (opciones[0] == 2){
            imprimirNumEjec = false;
            EjecutarClaseNumero(opciones[1]);
        } else if (opciones[0] == 3){
            imprimirNumEjec = false;
            EjecutarClaseTiempo(opciones[1]);
        }
    }
}

public void EjecutarClase(){
    if (hasCompiled){
        tE = new tarea_ejecutarClase();
        tE.start();
        while(tE.isAlive()){};
    }
}

public void EjecutarClaseTiempo(int tiempo){
    //############PanelDatos.resetearTabla(4);##############
    int respuesta = PanelDatos.inicializaTopesAleatorios();
    if (respuesta != -1){
        primeraVez = false;
    }
    datosElegidos = new int[1];
    //int i = 0;
    int i = PanelDatos.dameNumeroFilas (4);
    seguir = true;
    Timer timer = new Timer (tiempo*1000, new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            seguir = false;
        }
    });
    timer.setRepeats(false);
    if (respuesta != -1){
        timer.start();
        do {
            PanelDatos.anadeFilaAleatoria();
            datosElegidos[0] = i;
            EjecutarClase();
            i++;
            ejecucionesIntensivas++;
        } while(seguir);
        accionesEditor.muestraNumEjec (ejecucionesIntensivas, false);
    }
}

public void EjecutarClaseNumero(int numero){
    //############PanelDatos.resetearTabla(4);##############
    int respuesta = PanelDatos.inicializaTopesAleatorios();
    datosElegidos = new int[1];
    if (respuesta != -1){
        primeraVez = false;
        int i=PanelDatos.dameNumeroFilas(4);
        int j=(PanelDatos.dameNumeroFilas(4)+numero);
        for (int k=i; k<j; k++){
            PanelDatos.anadeFilaAleatoria();
            datosElegidos[0] = k;
            EjecutarClase();
            ejecucionesIntensivas++;
        }
        accionesEditor.muestraNumEjec(ejecucionesIntensivas, false);
    }
}

class tarea_ejecutarClase extends Thread {
    public void run() {ejecutarClase();}
    public void ejecutarClase(){
    	parado = false;
        int ejecuciones = 0;  
        
        if (PanelDatos.dameNumeroFilas(4)>0 && (metodosElegidos.length==1)){
        	 String datosEntrada = PanelDatos.getFilaImprimible(4, datosElegidos[0]);
             Integer indiceTablaDatos = (Integer)datosElegidos[0];
             Object[] paraTabla2 = new Object[2];
             numeroEjecuciones++;
             paraTabla2[0] = "ejec " + numeroEjecuciones;
             dato = datosElegidos[0];
             metodo = metodosElegidos[0];
                 
             tES = new tarea_ejecutar();
             tES.start();

             while(tES.isAlive()){};
             paraTabla2[1] = resultado.toString();

             gestor.insertarTabla(paraTabla2, 2, datosEntrada, indiceTablaDatos);

             ejecuciones++;
             if (imprimirNumEjec) {
                 ejecucionesIntensivas++;
             } 
             PanelDatos.marcarDato(datosElegidos[0]);  //########## Marcar dato para no ejecutarlo otra vez
             
             if (ejecuciones>0) {
             	 System.out.println("	MENSAJE EJECUCIONES");
            	 gestor.ActivarPararEjecuciones(); 
                 accionesEditor.muestraNumEjec(ejecuciones, false);
                 gestor.DesactivarGraficas();
                 PanelDatos.generaGrafico1(); 
                 PanelDatos.generaGrafico2();
             }
        	 
        }else if (PanelDatos.dameNumeroFilas(4)>0 && metodosElegidos.length>0){      	 
            for(int j=0; j<datosElegidos.length; j++){
                    String datosEntrada = PanelDatos.getFilaImprimible(4, datosElegidos[j]);
                    Integer indiceTablaDatos = (Integer)datosElegidos[j];

                    Object[] paraTabla2 = new Object[metodosElegidos.length+1];
                    numeroEjecuciones++;

                    paraTabla2[0] = "ejec " + numeroEjecuciones;
                    
                    for(int i=0; i<metodosElegidos.length; i++){
                        dato = datosElegidos[j];
                        metodo = metodosElegidos[i];
                        
                        tES = new tarea_ejecutar();
                        tES.start();
                        while(tES.isAlive()){};
                        paraTabla2[i+1] = resultado.toString();
                    }
                    
                  //Añadida comprobación de parada o no en la ejecución (nueva funcionalidad con botón Stop)
                    if (parado == true){  //No seguir
                        accionesEditor.muestraNumEjec(ejecuciones, true);
                        gestor.DesactivarGraficas();
                        PanelDatos.generaGrafico1(); 
                        PanelDatos.generaGrafico2();
                    	return;
                    }else{ 	//Seguir
                    	//System.out.println("DATOSENTRADA: " + datosEntrada);
                    	//System.out.println("METODO:" + metodo);
                 
                    	gestor.insertarTabla(paraTabla2, 2, datosEntrada, indiceTablaDatos);

                    	ejecuciones++;
                    	if (imprimirNumEjec) {
                    		ejecucionesIntensivas++;
                    	} 
                    	PanelDatos.marcarDato(datosElegidos[j]);  //########## Marcar dato para no ejecutarlo otra vez
                    }
            }      
            if (ejecuciones>0) {
            	System.out.println("	MENSAJE NÚM. EJECUCIONES");    
           	 	gestor.ActivarTablaConDatos();
                accionesEditor.muestraNumEjec(ejecuciones, false);
                gestor.DesactivarGraficas();
                PanelDatos.generaGrafico1(); 
                PanelDatos.generaGrafico2();
            }
        }
        
    }
    
    private ArrayList<Double> createValueList(double d, double d1, int i) {
        ArrayList<Double> arraylist = new ArrayList<Double>();
        for (int j = 0; j < i; j++) {
            double d2 = d + Math.random() * (d1 - d);
            arraylist.add(new Double(d2));
        }
        return arraylist;
    }  
}
 

class tarea_ejecutar extends Thread {
    public void run() {ejecutar();}
    public void ejecutar(){
   
    	gestor.DesactivarGraficas(); 
        //############# PARA TERMINAR LOS BUCLES INFINITOS ##############
        Timer timer = new Timer (3000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               // ventana.punteroNormal("java.lang.ThreadDeath");
               // resultado = "InvocationTargetException";
            	resultado = "Tiempo excedido";
                tES.stop();
                System.out.println("--tES STOP--");
            }
        });
        timer.setRepeats(false);
        //###############################################################
        
        try {                
            Class cm = metodo.getDeclaringClass();
            Object[] parametros = PanelDatos.dameFilaDatos(dato);
            Object[] parametrosCopia = new Object[parametros.length-1];

            for(int g=0;g<parametrosCopia.length;g++){
            	parametrosCopia[g] = parametros[g+1];
            }
            
            Object o = cm.newInstance();
                
            timer.start();  //####### ANTI BUCLES INFINITOS #########
            
            resultado = metodo.invoke(o,parametrosCopia);
            PanelDatos.marcarDato(dato);
            timer.stop();
                
       } catch (InstantiationException e){
            timer.stop();
            ventana.punteroNormal(e.getCause().toString());
            resultado = "InstantiationException";
       } catch (IllegalAccessException e){
            timer.stop();
            ventana.punteroNormal(e.getCause().toString());
            resultado = "IllegalAccessException";
       } catch (SecurityException e){
            timer.stop();
            ventana.punteroNormal(e.getCause().toString());
            resultado = "SecurityException";
       } catch (IllegalArgumentException e){
            timer.stop();
            ventana.punteroNormal(e.getCause().toString());
            resultado = "IllegalArgumentException";
       } catch (InvocationTargetException e){
            timer.stop();
            ventana.punteroNormal(e.getCause().toString());
            //resultado = "InvocationTargetException";
            resultado = "Tiempo excedido";
       }
    }
}

public int numeroEjecuciones(){
    return numeroEjecuciones;
}

public void resetNumEjecuciones(){
    numeroEjecuciones = 0;
}

public static Boolean Salvar(){
    return accionesEditor.actionSave();
}

public static void SalvarComo(){
    accionesEditor.actionSaveAs();
}

public static void Deshacer(){
    texto.requestFocus();
    accionesEditor.actionUndo();
}

public static void Rehacer(){
    texto.requestFocus();
    accionesEditor.actionRedo();
}

public static void Cortar(){
    texto.requestFocus();
    texto.cut();
}

public static void Copiar(){
    texto.requestFocus();
    texto.copy();
}

public static void Pegar(){
    texto.requestFocus();
    texto.paste();
}

public static void IrALinea(){
    texto.requestFocus();
    accionesEditor.actionGoToLine();
}

public static void Buscar(){
    texto.requestFocus();
    accionesEditor.actionSearch();
}

public static void BuscarSiguiente(){
    texto.requestFocus();
    accionesEditor.actionSearchNext();
}

public static void SeleccionarTodo(){
    texto.requestFocus();
    String textAreaContent = texto.getText();
    texto.setCaretPosition(0);
    texto.moveCaretPosition(0 + textAreaContent.length());
}

public static void Salir(){
    accionesEditor.actionClose();
}

public static void acercaDe(){
    accionesEditor.mostrarAcercaDe();
}

public static void ayuda(){
    accionesEditor.muestraAyuda();
}

private static void ponerEspanolRelajado(){
    mensaje1 = "OptimEx, Sistema para la comparación de algoritmos de optimización";
    mensaje2 = "Líneas: ";
    mensaje3 = "Ha compilado correctamente";
 //   mensaje4 = "Tienes errores de compilación";
    mensaje5 = "Se ha encontrado esta máquina virtual de java";
    mensaje6 = "No se ha encontrado máquina virtual de java y esta es necesaria,\n"
            + "introduce el path de una máquina válida, por favor";
    mensaje7 = "No coinciden los tipos";
    mensaje8 = "No se encuentran métodos en esta clase";
    mensaje9 = "Buscando máquina virtual de Java";
    mensaje10 = "Encontrada máquina virtual de Java";
    mensaje11 = " Compilando";
    mensaje12 = " Compilación terminada sin errores";
   // mensaje13 = "Compilación terminada con errores";
    mensaje14 = "Confirmar compilación";
    mensaje15 = "Si compilas se perderán los datos de la experimentación actual";
    mensaje16 = "Los datos elegidos ya han sido utilizados en ejecuciones anteriores";
    mensaje16a = "El dato elegido ya ha sido utilizado en ejecuciones anteriores";
    mensaje16b = "Algunos datos elegidos ya han sido utilizados en ejecuciones anteriores";
   // mensaje17 = "No se ha encontrado Java Development Kit (JDK). Comprueba si existe y rearranca OptimEx";
    mensaje17 = "No se ha encontrado Java Development Kit (JDK). Selecciona la ruta en Configuración/Máquina virtual Java";
    mensaje18 = "Error en el (wait for exec) de compilar";
    mensaje19 = "Esperando a Runtime.getRuntime().exec(javac -g clase.java)";
    mensaje20 = "Confirmar cambio de problema";
    mensaje21 = "Si cambias de problema se borrarán los resultados";
    mensaje22 = "Medida";
    mensaje23 = "Confirmar cambio de métodos y de óptimo";
    mensaje24 = "Si cambias de métodos y de óptimo se borrarán los resultados";
    mensaje25 = "Confirmar cambio de métodos";
    mensaje26 = "Si cambias de métodos se borrarán los resultados";
    mensaje27 = "Confirmar cambio de óptimo";
    mensaje28 = "Si cambias de óptimo se modificarán los resultados";
    mensaje29 = "Confirmar cambio de método de comparación";
    mensaje30 = "Si cambias de método de comparación se modificarán los resultados";
    mensaje31 = "Confirmar cambio de signatura";
    mensaje32 = "Si cambias de signatura se borrarán los resultados";
    mensaje33 = "Errores: ";
    mensaje34 = " FICHERO: ";
    mensaje35 = " LÍNEA: ";
    mensaje36 = " ERROR:";
    mensaje37 = "La tabla de datos está vacía";
    mensaje38 = "Aviso";
    mensaje39a = "% Casos superóptimos";
    mensaje39b = "% Casos óptimos";
    mensaje39c = "% Casos subóptimos";
	mensaje40a = "% Media";
	mensaje40b = "% Subóptima";
	mensaje40c = "% Superóptima";
	mensaje41a = "% de resultados óptimos";
	mensaje41b = "Valores medios y extremos";
	mensaje42 = "Por favor, selecciona primero una fila de la tabla 'Datos'";
	mensaje43 = "Por favor, selecciona primero varias filas de la tabla 'Datos'";
	mensaje44 = "Por favor, selecciona una única fila de la tabla 'Datos'";
	mensaje45 = "Por favor, selecciona varias filas de la tabla 'Datos'";
    }

private static void ponerInglesRelajado(){
    mensaje1 = "OptimEx, System for comparing optimization algorithms";
    mensaje2 = "Lines: ";
    mensaje3 = "It has compiled correctly";
 //   mensaje4 = "It has mistakes of compilation";
    mensaje5 = "Has found this virtual machine of java";
    mensaje6 = "Has not found virtual machine of java and this one is necessary,\n"
            + "it introduces the path of a valid machine, please";
    mensaje7 = "The types do not coincide";
    mensaje8 = "Do not find methods in this class";
    mensaje9 = "Looking for virtual machine of Java";
    mensaje10 = "Virtual machine of Java has been found";
    mensaje11 = " Compiling";
    mensaje12 = " Compilation finished successfully";
   // mensaje13 = "Compilation finished with errors";
    mensaje14 = "Confirm compilation";
    mensaje15 = "If you compile will lose the current experimental data";
    mensaje16 = "The selected data have already been used in previous executions";
    mensaje16a = "The selected data has already been used in previous executions";
    mensaje16b = "Some selected data have already been used in previous executions";
    mensaje17 = "Not found Java Development Kit (JDK). Checks if exists and reboot OptimEx";
    mensaje18 = "Error at (wait for exec) to compile";
    mensaje19 = "Waiting for Runtime.getRuntime().exec(javac -g clase.java)";
    mensaje20 = "Confirm change of problem";
    mensaje21 = "If you change of problem the results will be deleted";
    mensaje22 = "Measure";
    mensaje23 = "Confirm change of methods and optimal";
    mensaje24 = "If you change of methods and optimal the results will be deleted";
    mensaje25 = "Confirm change of methods";
    mensaje26 = "If you change of methods the results will be deleted";
    mensaje27 = "Confirm change of optimal";
    mensaje28 = "If you change of optimal the results will be changed";
    mensaje29 = "Confirm change of comparison method";
    mensaje30 = "If you change of comparison method the results will be changed";
    mensaje31 = "Confirm change of set of types";
    mensaje32 = "If you change of set of types the results will be deleted";
    mensaje33 = "Errors: ";
    mensaje34 = " FILE: ";
    mensaje35 = " LINE: ";
    mensaje36 = " ERROR:";
    mensaje37 = "The data table is empty";
    mensaje38 = "Notice";
    mensaje39a = "% Superoptimal cases";
    mensaje39b = "% Optimal cases";
    mensaje39c = "% Suboptimal cases";
	mensaje40a = "% Mean";
	mensaje40b = "% Suboptimal";
	mensaje40c = "% Superoptimal";
	mensaje41a = "% of optimal results";
	mensaje41b = "Average and extreme values";
	mensaje42 = "Please, select first a row from the Data table";
}

public static void ponerEspanol(){
    accionesEditor.ponerEspanol();
    mensaje1 = "OptimEx, Sistema para la comparación de algoritmos de optimización";
    mensaje2 = "Líneas: ";
    mensaje3 = "Ha compilado correctamente";
 //   mensaje4 = "Tienes errores de compilación";
    mensaje5 = "Se ha encontrado esta máquina virtual de java";
    mensaje6 = "No se ha encontrado máquina virtual de java y esta es necesaria,\n"
            + "introduce el path de una máquina válida, por favor";
    mensaje7 = "No coinciden los tipos";
    mensaje8 = "No se encuentran métodos en esta clase";
    mensaje9 = "Buscando máquina virtual de Java";
    mensaje10 = "Encontrada máquina virtual de Java";
    mensaje11 = " Compilando";
    mensaje12 = " Compilación terminada sin errores";
  //  mensaje13 = "Compilación terminada con errores";
    mensaje14 = "Confirmar compilación";
    mensaje15 = "Si compilas se perderán los datos de la experimentación actual";
    mensaje16 = "Los datos elegidos ya han sido utilizados en ejecuciones anteriores";
    mensaje16a = "El dato elegido ya ha sido utilizado en ejecuciones anteriores";
    mensaje16b = "Algunos datos elegidos ya han sido utilizados en ejecuciones anteriores";
    //mensaje17 = "No se ha encontrado Java Development Kit (JDK). Comprueba si existe y rearranca OptimEx";
    mensaje17 = "No se ha encontrado Java Development Kit (JDK). Selecciona la ruta en Configuración/Máquina virtual Java";
    mensaje18 = "Error en el (wait for exec) de compilar";
    mensaje19 = "Esperando a Runtime.getRuntime().exec(javac -g clase.java)";
    mensaje20 = "Confirmar cambio de problema";
    mensaje21 = "Si cambias de problema se borrarán los resultados";
    mensaje22 = "Medida";
    mensaje23 = "Confirmar cambio de métodos y de óptimo";
    mensaje24 = "Si cambias de métodos y de óptimo se borrarán los resultados";
    mensaje25 = "Confirmar cambio de métodos";
    mensaje26 = "Si cambias de métodos se borrarán los resultados";
    mensaje27 = "Confirmar cambio de óptimo";
    mensaje28 = "Si cambias de óptimo se modificarán los datos";
    mensaje29 = "Confirmar cambio de método de comparación";
    mensaje30 = "Si cambias de método de comparación se modificarán los resultados";
    mensaje31 = "Confirmar cambio de signatura";
    mensaje32 = "Si cambias de signatura se borrarán los resultados";
    mensaje33 = "Errores: ";
    mensaje34 = " FICHERO: ";
    mensaje35 = " LÍNEA: ";
    mensaje36 = " ERROR:";
    mensaje37 = "La tabla de datos está vacía";
    mensaje38 = "Aviso";
    mensaje39a = "% Casos superóptimos";
    mensaje39b = "% Casos óptimos";
    mensaje39c = "% Casos subóptimos";
	mensaje40a = "% Media";
	mensaje40b = "% Subóptima";
	mensaje40c = "% Superóptima";
	mensaje41a = "% de resultados óptimos";
	mensaje41b = "Valores medios y extremos";
	mensaje42 = "Por favor, selecciona primero una fila de la tabla 'Datos'";
	mensaje43 = "Por favor, selecciona primero varias filas de la tabla 'Datos'";
	mensaje44 = "Por favor, selecciona una única fila de la tabla 'Datos'";
	mensaje45 = "Por favor, selecciona varias filas de la tabla 'Datos'";
    if (getCurrentFile() != null){
        actualizarTituloVentana(getCurrentFile());
    }else{
        actualizarTituloVentana();
    }
    
    sbCaretPos = gestor.DameEstado();
    sbCaretPos = sbCaretPos.replace("Lines", "Líneas");
    sbCaretPos = sbCaretPos.replace("Looking for virtual machine of Java", "Buscando máquina virtual de Java");
    sbCaretPos = sbCaretPos.replace("Virtual machine of Java has been found", "Encontrada máquina virtual de Java");
   // sbCaretPos = sbCaretPos.replace("Compiling", "Compilando");
    sbCaretPos = sbCaretPos.replace("Compilation finished without errors", "Compilación terminada sin errores");
   // sbCaretPos = sbCaretPos.replace("Compilation finished with errors", "Compilación terminada con errores");
    gestor.ActualizarEstado(sbCaretPos);
    botones[0] = "Aceptar";
    botones[1] = "Cancelar";
    
}

public static void ponerIngles(){
    accionesEditor.ponerIngles();
    mensaje1 = "OptimEx, System for comparing optimization algorithms";
    mensaje2 = "Lines: ";
    mensaje3 = "It has compiled correctly";
    //mensaje4 = "It has mistakes of compilation";
    mensaje5 = "Has found this virtual machine of java";
    mensaje6 = "Has not found virtual machine of java and this one is necessary,\n"
            + "it introduces the path of a valid machine, please";
    mensaje7 = "The types do not coincide";
    mensaje8 = "Do not find methods in this class";
    mensaje9 = "Looking for virtual machine of Java";
    mensaje10 = "Virtual machine of Java has been found";
    mensaje11 = " Compiling";
    mensaje12 = " Compilation finished successfully";
  //  mensaje13 = "Compilation finished with errors";
    mensaje14 = "Confirm compilation";
    mensaje15 = "If you compile will lose the current experimental data";
    mensaje16 = "The selected data have already been used in previous executions";
    mensaje16a = "The selected data has already been used in previous executions";
    mensaje16b = "Some selected data have already been used in previous executions";
    mensaje17 = "Not found Java Development Kit (JDK). Checks if exists and reboot OptimEx";
    mensaje18 = "Error at (wait for exec) to compile";
    mensaje19 = "Waiting for Runtime.getRuntime().exec(javac -g clase.java)";
    mensaje20 = "Confirm change of problem";
    mensaje21 = "If you change of problem the results will be deleted";
    mensaje22 = "Measure";
    mensaje23 = "Confirm change of methods and optimal";
    mensaje24 = "If you change of methods and optimal the results are deleted";
    mensaje25 = "Confirm change of methods";
    mensaje26 = "If you change of methods the results are deleted";
    mensaje27 = "Confirm change of optimal";
    mensaje28 = "If you change of optimal the results will be changed";
    mensaje29 = "Confirm change of comparison method";
    mensaje30 = "If you change of comparison method the results will be changed";
    mensaje31 = "Confirm change of set of types";
    mensaje32 = "If you change of set of types the results will be deleted";
    mensaje33 = "Errors:";
    mensaje34 = " FILE: ";
    mensaje35 = " LINE: ";
    mensaje36 = " ERROR:";
    mensaje37 = "The data table is empty";
    mensaje38 = "Notice";
    mensaje39a = "% Superoptimal cases";
    mensaje39b = "% Optimal cases";
    mensaje39c = "% Suboptimal cases";
	mensaje40a = "% Mean";
	mensaje40b = "% Suboptimal";
	mensaje40c = "% Superoptimal";
	mensaje41a = "% of optimal results";
	mensaje41b = "Average and extreme values";
	mensaje42 = "Please, select first a row from the Data table";
	mensaje43 = "Please, select first a rows from the Data table";
	mensaje44 = "Please, select a single row from the Data table";
	mensaje45 = "Please, select multiple rows from the Data table'";
    if (getCurrentFile() != null){
        actualizarTituloVentana(getCurrentFile());
    }else{
        actualizarTituloVentana();
    }
    
    sbCaretPos = gestor.DameEstado();
    sbCaretPos = sbCaretPos.replace("Líneas", "Lines");
    sbCaretPos = sbCaretPos.replace("Buscando máquina virtual de Java", "Looking for virtual machine of Java");
    sbCaretPos = sbCaretPos.replace("Encontrada máquina virtual de Java", "Virtual machine of Java has been found");
  //  sbCaretPos = sbCaretPos.replace("Compilando", "Compiling");
    sbCaretPos = sbCaretPos.replace("Compilación terminada sin errores", "Compilation finished without errors");
  //  sbCaretPos = sbCaretPos.replace("Compilación terminada con errores", "Compilation finished with errors");
    gestor.ActualizarEstado(sbCaretPos);
    botones[0] = "Accept";
    botones[1] = "Cancel";    
}

private String[] quitarRepetidos(String[] conRepetidos){
    int cantidadUnicos = 0;
    String[] devuelto;
    String[] intermedio = new String[conRepetidos.length];
    for (int i=0; i<conRepetidos.length; i++){
        intermedio[i] = null;   //reseteo el array intermedio
    }
    for (int i=0; i<conRepetidos.length; i++){
        if (noEstaEn(conRepetidos[i],intermedio)){
            intermedio[cantidadUnicos] = conRepetidos[i];
            cantidadUnicos++;
        }
    }
    devuelto = new String[cantidadUnicos];
    for (int i=0; i<cantidadUnicos; i++){
        devuelto[i] = intermedio[i];
    }
    return devuelto;
}

private boolean noEstaEn(String cadena, String[] arrayCadenas){
    boolean devuelto = true;
    for (int i=0; i<arrayCadenas.length; i++){
        if (cadena.equals(arrayCadenas[i])){
            devuelto = false;
        }
    }
    return devuelto;
}

private String limpiarTipos(String nombre, String cadena){

   nombre = nombre.replace("class", " ");
   nombre = nombre.replace("public", "");
   nombre = nombre.replace("private", "");
   nombre = nombre.replace("static", "");
   nombre = nombre.replace("java.", "");
   nombre = nombre.replace("lang.", "");
   nombre = nombre.replace(";", "");
   nombre = nombre.replace(cadena+".", "");
                    
   nombre = nombre.replace("void", " void");
                    
   nombre = nombre.replace("[[B", "byte[][]");             nombre = nombre.replace("[B", "byte[]");
   nombre = nombre.replace("byte", " byte");               nombre = nombre.replace("[[LByte", "Byte[][]");
   nombre = nombre.replace("[LByte", "Byte[]");            nombre = nombre.replace("Byte", "Byte");
                    
   nombre = nombre.replace("[[S", "short[][]");            nombre = nombre.replace("[S", "short[]");
   nombre = nombre.replace("short", " short");             nombre = nombre.replace("[[LShort", "Short[][]");
   nombre = nombre.replace("[LShort", "Short[]");          nombre = nombre.replace("Short", "Short");
                    
   nombre = nombre.replace("[[I", "int[][]");              nombre = nombre.replace("[I", "int[]");
   nombre = nombre.replace("int", " int");                 nombre = nombre.replace("[[LInteger", "Integer[][]");
   nombre = nombre.replace("[LInteger", "Integer[]");      nombre = nombre.replace("Integer", "Integer");
                    
   nombre = nombre.replace("[[J", "long[][]");             nombre = nombre.replace("[J", "long[]");
   nombre = nombre.replace("long", " long");               nombre = nombre.replace("[[LLong", "Long[][]");
   nombre = nombre.replace("[LLong", "Long[]");            nombre = nombre.replace("Long", "Long");
                    
   nombre = nombre.replace("[[F", "float[][]");            nombre = nombre.replace("[F", "float[]");
   nombre = nombre.replace("float", " float");             nombre = nombre.replace("[[LFloat", "Float[][]");
   nombre = nombre.replace("[LFloat", "Float[]");          nombre = nombre.replace("Float", "Float");
                    
   nombre = nombre.replace("[[D", "double[][]");           nombre = nombre.replace("[D", "double[]");
   nombre = nombre.replace("double", " double");           nombre = nombre.replace("[[LDouble", "Double[][]");
   nombre = nombre.replace("[LDouble", "Double[]");        nombre = nombre.replace("Double", "Double");
                    
   nombre = nombre.replace("[[Z", "boolean[][]");          nombre = nombre.replace("[Z", "boolean[]");
   nombre = nombre.replace("boolean", " boolean");         nombre = nombre.replace("[[LBoolean", "Boolean[][]");
   nombre = nombre.replace("[LBoolean", "Boolean[]");      nombre = nombre.replace("Boolean", "Boolean");
                    
   nombre = nombre.replace("[[C", "char[][]");             nombre = nombre.replace("[C", "char[]");
   nombre = nombre.replace("char", " char");               nombre = nombre.replace("[[LCharacter", "Character[][]");
   nombre = nombre.replace("[LCharacter", "Character[]");  nombre = nombre.replace("Character", "Character");
                    
   nombre = nombre.replace("[[LString", "String[][]");     nombre = nombre.replace("[LString", "String[]");
   nombre = nombre.replace("String", "String");
                    
   nombre = nombre.replace("  ", "");
   return nombre;
}

private Method[] ordenarMetodos(Method[] metodos){
    StringTokenizer st;
    String[] cads, cads2;
    String cadena;
        
    st = new StringTokenizer(currentFile.getAbsolutePath(),File.separator);
    cads = new String[st.countTokens()];
    for (int i=0; i<cads.length; i++){
        cads[i]=st.nextToken();              
    }       
    cadena = new String();
    //########  Se sustituye File.separator por "."  #########
    st = new StringTokenizer(cads[cads.length-1],".");
    cads2 = new String[st.countTokens()];
    for (int i=0; i<cads2.length-1; i++){
        cads2[i]=st.nextToken();        //Se quita la extension .java
    }                               
    if (accionesEditor.actionIsPackage()) {
        cadena = cads[cads.length-2];
        cadena = cadena.concat("."+cads2[0]);//paquete.clase
    }else{
        cadena = cads2[0];//clase
    }
    Method[] devuelto = new Method[metodos.length];
    String[] stringsOriginal = new String[metodos.length];
    String[] stringsOrdenado = new String[metodos.length];
    for(int i=0; i<metodos.length; i++){
        String nombre = metodos[i].toString();
        nombre = nombre.replace("public", "");
        nombre = nombre.replace("private", "");
        nombre = nombre.replace("static", "");
        nombre = nombre.replace("java.", "");
        nombre = nombre.replace("lang.", "");
        nombre = nombre.replace(cadena+".", "");
        stringsOriginal[i] = nombre;
        stringsOrdenado[i] = nombre;
    }
    ArrayList lista = new ArrayList();
    for(int i=0; i<metodos.length; i++){
        lista.add(stringsOrdenado[i]);
    }
    Collections.sort(lista);
    for(int i=0; i<lista.size(); i++){
        stringsOrdenado[i] = lista.get(i).toString();
    }
    for(int i=0; i<metodos.length; i++){      // stringsOriginal
        for(int j=0; j<metodos.length; j++){  // stringsOrdenado
            if (stringsOriginal[i].contentEquals(stringsOrdenado[j])){
                devuelto[j] = metodos[i];
            }
        }
    }
    return devuelto;
}
}